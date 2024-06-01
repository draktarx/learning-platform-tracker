package tracker.ui;

import tracker.input.InputReader;
import tracker.input.MenuAction;
import tracker.input.MenuOption;
import tracker.platform.CourseName;
import tracker.platform.Student;
import tracker.platform.stats.CourseStats;

import java.util.Map;

import static tracker.input.MenuAction.*;

public class UI {

    public static MenuAction show() {
        MenuAction menuAction;
        do {
            MenuOption input = InputReader.readMenuOption();
            menuAction = mainMenu(input);
        } while (menuAction == NO_ACTION);
        return menuAction;
    }

    public static MenuAction mainMenu(MenuOption option) {
        return switch (option) {
            case NOTIFY_OPTION -> NOTIFY_ACTION;
            case STATS_OPTION -> STATS_ACTION;
            case SHOW_STUDENT_POINTS_OPTION -> SHOW_STUDENT_POINTS_ACTION;
            case ADD_POINTS_OPTION -> ADD_POINTS_ACTION;
            case LIST_STUDENTS_OPTION -> lIST_STUDENTS_ACTION;
            case ADD_STUDENTS_OPTION -> ADD_STUDENTS_ACTION;
            case EXIT_OPTION -> {
                System.out.println("Bye!");
                yield EXIT_ACTION;
            }
            case BACK_OPTION -> {
                System.out.println("Enter 'exit' to exit the program");
                yield NO_ACTION;
            }
            case EMPTY_OPTION -> {
                System.out.println("No input.");
                yield NO_ACTION;
            }
            case INVALID_OPTION -> {
                System.out.println("Error: unknown command!");
                yield NO_ACTION;
            }
        }
                ;
    }

    public static void welcomeMessage() {
        System.out.println("Learning Progress Tracker");
    }

    public static void printStats(CourseStats stats) {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        System.out.printf("Most popular: %s\n", stats.getMostPopularCourse());
        System.out.printf("Least popular: %s\n", stats.getLeastPopularCourse());
        System.out.printf("Highest activity: %s\n", stats.getHighestActivityCourse());
        System.out.printf("Lowest activity: %s\n", stats.getLowestActivityCourse());
        System.out.printf("Easiest course: %s\n", stats.getEasiestCourse());
        System.out.printf("Hardest course: %s\n", stats.getHardestCourse());
    }

    public static void printCourseDetails(CourseName courseName, Map<String, Student> students) {
        System.out.println(courseName);
        System.out.println("id points completed");
        students.values().stream()
                .sorted((s1, s2) -> {
                    int cmp = Integer.compare(s2.getPoints(courseName), s1.getPoints(courseName));
                    if (cmp != 0) {
                        return cmp;
                    } else {
                        return s1.getId().compareTo(s2.getId());
                    }
                })
                .forEach(student -> System.out.printf("%s %d %.1f%%\n",
                        student.getId(),
                        student.getPoints(courseName),
                        student.getCompletionPercentage(courseName)));
    }
}
