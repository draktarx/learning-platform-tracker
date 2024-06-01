package tracker.platform;

import tracker.input.CredentialsState;
import tracker.input.InputReader;
import tracker.input.MenuAction;
import tracker.platform.stats.CourseStats;
import tracker.platform.stats.CourseStatsProvider;
import tracker.platform.stats.ICourseStatsProvider;
import tracker.ui.UI;
import tracker.validators.AddPointsValidator;
import tracker.validators.UUIDValidator;
import tracker.validators.credentials.StudentCredentialsValidator;

import java.util.*;
import java.util.stream.Collectors;

public class LearningPlatform {

    private Map<String, Student> studentIdMapStudent;
    private Map<String, String> emailMapId;

    private final ICourseStatsProvider statsProvider;

    public LearningPlatform() {
        this.studentIdMapStudent = new LinkedHashMap<>();
        this.emailMapId = new LinkedHashMap<>();
        statsProvider = new CourseStatsProvider(this);
        UI.welcomeMessage();
    }

    public void run() {
        MenuAction menuAction;
        do {
            menuAction = UI.show();
            performAction(menuAction);
        } while (menuAction != MenuAction.EXIT_ACTION);
    }

    public void performAction(MenuAction menuAction) {
        switch (menuAction) {
            case NOTIFY_ACTION:
                notifyStudent();
                break;
            case STATS_ACTION:
                showStats();
                break;
            case ADD_POINTS_ACTION:
                addPointsToStudent();
                break;
            case SHOW_STUDENT_POINTS_ACTION:
                showStudentPoints();
                break;
            case ADD_STUDENTS_ACTION:
                addStudents();
                break;
            case lIST_STUDENTS_ACTION:
                listStudents();
                break;
            case EXIT_ACTION:
                System.exit(0);
            case NO_ACTION:
            default:
                break;
        }
    }

    /**
     * To: %EMAIL_ADDRESS%
     * Re: Your Learning Progress
     * Hello, %FULL_USER_NAME%! You have accomplished our %COURSE_NAME% course!
     */
    private void notifyStudent() {
        int numberOfStudentsNotified = 0;

        for (Student student : studentIdMapStudent.values()) {
            Boolean studentNotified = false;
            for (Map.Entry<CourseName, Completion> course : student.getCompletedCourses().entrySet()) {
                if (course.getValue().completed() && !course.getValue().notified()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("To: ").append(student.getCredentials().email()).append("\n");
                    stringBuilder.append("Re: Your Learning Progress\n");
                    stringBuilder.append("Hello, ")
                            .append(student.fullname())
                            .append("! You have accomplished our ")
                            .append(course.getKey())
                            .append(" course!")
                            .append("\n");
                    System.out.print(stringBuilder);
                    course.setValue(new Completion(true, true));
                    if (!studentNotified) {
                        numberOfStudentsNotified++;
                        studentNotified = true;
                    }
                }
            }
        }
        System.out.printf("Total %d students have been notified.\n", numberOfStudentsNotified);
    }

    private void showStats() {
        CourseStats stats = statsProvider.calculateStats();
        UI.printStats(stats);

        boolean backToMainMenu = false;
        do {
            String input = InputReader.readLine();
            if (InputReader.userWantsBackToMenu(input)) {
                backToMainMenu = true;
            } else {
                try {
                    CourseName courseName = CourseName.valueOf(input.toUpperCase());
                    UI.printCourseDetails(courseName, getStudentsForCourse(courseName));
                } catch (IllegalArgumentException e) {
                    System.out.println("Unknown course.");
                }
            }
        } while (!backToMainMenu);
    }

    private Map<String, Student> getStudentsForCourse(CourseName courseName) {
        return studentIdMapStudent.entrySet().stream()
                .filter(entry -> entry.getValue().isEnrolled(courseName))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private void showStudentPoints() {
        System.out.println("Enter an id or 'back' to return");

        boolean backToMainMenu = false;
        do {
            String idToFind = InputReader.readLine();
            if (InputReader.userWantsBackToMenu(idToFind)) {
                backToMainMenu = true;
            } else {
                Student student = findStudentById(idToFind);
                student.showPoints();
            }
        } while (!backToMainMenu);
    }

    private Student findStudentById(String idToFind) {
        UUIDValidator uuidValidator = new UUIDValidator();
        Student student = null;
        if (!uuidValidator.validate(idToFind)) {
            System.out.println("Not valid id.");
        } else {
            student = getStudentIdMapStudent().getOrDefault(idToFind, null);
            if (Objects.isNull(student))
                System.out.printf("No student is found for id=%s\n", idToFind);
        }
        return student;
    }

    private void addPointsToStudent() {
        System.out.println("Enter an id and points or 'back' to return");
        while (true) {
            String input = InputReader.scanner.nextLine();

            if (InputReader.userWantsBackToMenu(input))
                break;

            AddPointsValidator addPointsValidator = new AddPointsValidator();
            String studentId = addPointsValidator.extractStudentId(input);

            if (!existStudentId(studentId)) {
                String[] tokens = input.split(" ");
                System.out.printf("No student is found for id=%s\n", tokens[0]);
            } else {
                if (addPointsValidator.validate(input)) {
                    Submission submission = new Submission(studentId, addPointsValidator.extractPointsToAdd(input));

                    Student student = studentIdMapStudent.get(studentId);
                    submission.pointsPerCourse().forEach(student::addPoints);
                    studentIdMapStudent.put(studentId, student);

                    System.out.println("Points updated.");
                }
            }

        }
    }

    private boolean existStudentId(String studentId) {
        return studentIdMapStudent.containsKey(studentId);
    }

    public void addStudents() {
        System.out.println("Enter student credentials or 'back' to return");

        boolean backToMainMenu = false;
        int studentsAdded = 0;
        do {
            String newStudentCredentials = InputReader.readLine();
            CredentialsState credentialsStatus = StudentCredentialsValidator.validateBeforeBeingParsed(newStudentCredentials);
            switch (credentialsStatus) {
                case VALID:
                    if (registerNewStudent(newStudentCredentials))
                        studentsAdded++;
                    break;
                case BACK:
                    System.out.printf("Total %d students have been added.\n", studentsAdded);
                    backToMainMenu = true;
                    break;
                case NOT_VALID:
                    System.out.println("Incorrect credentials.");
                    break;
            }
        } while (!backToMainMenu);
    }

    private boolean registerNewStudent(String userCredentialsInput) {
        boolean added = false;
        Credentials credentials = StudentCredentialsValidator.extractCredentials(userCredentialsInput);
        if (Objects.nonNull(credentials)) {
            if (!emailIsTaken(credentials.email()))
                added = addStudent(new Student(UUID.randomUUID().toString(), credentials));
        }
        return added;
    }

    public boolean emailIsTaken(String email) {
        boolean isTaken = Objects.nonNull(getEmailMapId().get(email));
        if (isTaken)
            System.out.println("This email is already taken.");
        return isTaken;
    }

    public boolean addStudent(Student student) {
        getStudentIdMapStudent().put(student.getId(), student);
        getEmailMapId().put(student.getCredentials().email(), student.getId());
        boolean isAdded = studentIdMapStudent.containsKey(student.getId()) &&
                getEmailMapId().containsKey(student.getCredentials().email());
        System.out.println("The student has been added.");
        return isAdded;
    }

    public void listStudents() {
        if (!platformHasStudents()) {
            System.out.println("No students found");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Students:\n");
            studentIdMapStudent.forEach((id, student) -> stringBuilder.append(id).append("\n"));
            System.out.print(stringBuilder);
        }
    }

    private boolean platformHasStudents() {
        return !getStudentIdMapStudent().isEmpty();
    }

    public Map<String, Student> getStudentIdMapStudent() {
        return studentIdMapStudent;
    }

    public Map<String, String> getEmailMapId() {
        return emailMapId;
    }
}
