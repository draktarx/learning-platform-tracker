package tracker.platform.stats;

import tracker.platform.CourseName;
import tracker.platform.LearningPlatform;
import tracker.platform.Student;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class CourseStatsProvider implements ICourseStatsProvider {
    private Map<CourseName, List<Student>> enrolled; // curso-numero de estudiantes
    private Map<CourseName, Integer> submissions;                  // curso-tareas presentadas
    private Map<CourseName, BigDecimal> courseMapDifficulty;              // Curso-puntuacion promedio

    private final LearningPlatform platform;

    public CourseStatsProvider(LearningPlatform platform) {
        this.platform = platform;
    }

    @Override
    public CourseStats calculateStats() {
        enrolled = new LinkedHashMap<>();
        submissions = new LinkedHashMap<>();
        courseMapDifficulty = new LinkedHashMap<>();

        for (CourseName courseName : CourseName.values()) {
            List<Student> enrolledStudents = platform.getStudentIdMapStudent().values().stream()
                    .filter(student -> student.isEnrolled(courseName))
                    .collect(Collectors.toList());

            int totalActivities = 0;
            int numberOfActivities = 0;

            for (Student enrolledStudent : enrolledStudents) {
                totalActivities += enrolledStudent.getPoints(courseName);
                numberOfActivities++;
            }

            if (numberOfActivities != 0) {
                BigDecimal average = new BigDecimal(totalActivities / numberOfActivities);
                average.setScale(2, RoundingMode.HALF_UP);
                courseMapDifficulty.put(courseName, average);
            } else {
                courseMapDifficulty.put(courseName, new BigDecimal("0"));
            }

            enrolled.put(courseName, enrolledStudents);
            submissions.put(courseName, totalActivities);

        }

        return generateStatistics();
    }

    private CourseStats generateStatistics() {
        CourseStats stats = new CourseStats();

        stats.setMostPopularCourse(getMostPopularCourse());
        stats.setLeastPopularCourse(getLeastPopularCourse(stats.getMostPopularCourse()));
        //stats.setHighestActivityCourse(getHighestActivityCourse());
        stats.setHighestActivityCourse(getMostPopularCourse());
        //stats.setLowestActivityCourse(getLowestActivityCourse());
        stats.setLowestActivityCourse(getLeastPopularCourse(stats.getMostPopularCourse()));
        stats.setEasiestCourse(getEasiestCourse());
        stats.setHardestCourse(getHardestCourse(stats.getEasiestCourse()));

        return stats;
    }

    private String getMostPopularCourse() {
        if (enrolled.values().stream().anyMatch(List::isEmpty))
            return "n/a";
        else {
            int maxEnrollment = enrolled.values().stream()
                    .mapToInt(List::size)
                    .max()
                    .orElse(0);

            return enrolled.entrySet().stream()
                    .filter(entry -> entry.getValue().size() == maxEnrollment)
                    .map(entry -> entry.getKey().getDisplayName())
                    .collect(Collectors.joining(", "));
        }
    }

    private String getLeastPopularCourse(String mostPopular) {
        if (enrolled.values().stream().anyMatch(List::isEmpty))
            return "n/a";
        else {
            Set<String> mostPopularCourses = Arrays.stream(mostPopular.split(", "))
                    .collect(Collectors.toSet());

            if (mostPopularCourses.size() == enrolled.keySet().size())
                return "n/a";

            int minEnrollment = enrolled.entrySet().stream()
                    .filter(entry -> !mostPopularCourses.contains(entry.getKey().name()))
                    .mapToInt(entry -> entry.getValue().size())
                    .min()
                    .orElse(0);

            return enrolled.entrySet().stream()
                    .filter(entry -> !mostPopularCourses.contains(entry.getKey().name()) && entry.getValue().size() == minEnrollment)
                    .map(entry -> entry.getKey().getDisplayName())
                    .collect(Collectors.joining(", "));
        }
    }

    private String getHighestActivityCourse() {
        if (submissions.values().stream().noneMatch(points -> points > 0))
            return "n/a";
        else {
            Integer max = submissions.values().stream()
                    .max(Integer::compareTo)
                    .get();

            return submissions.entrySet().stream()
                    .filter(entry -> Objects.equals(entry.getValue(), max))
                    .map(entry -> entry.getKey().getDisplayName())
                    .collect(Collectors.joining(", "));
        }
    }

    private String getLowestActivityCourse() {
        if (submissions.values().stream().noneMatch(points -> points > 0))
            return "n/a";
        else {
            Integer min = submissions.values().stream()
                    .min(Integer::compareTo)
                    .get();

            return submissions.entrySet().stream()
                    .filter(entry -> Objects.equals(entry.getValue(), min))
                    .map(entry -> entry.getKey().getDisplayName())
                    .collect(Collectors.joining(", "));
        }
    }

    private String getEasiestCourse() {
        if (!courseMapDifficulty.entrySet().stream().anyMatch(entry -> entry.getValue().compareTo(BigDecimal.ZERO) > 0))
            return "n/a";
        else {
            double maxAverage = courseMapDifficulty.values().stream()
                    .max(BigDecimal::compareTo)
                    .get()
                    .doubleValue();

            return courseMapDifficulty.entrySet().stream()
                    .filter(courseNameBigDecimalEntry -> courseNameBigDecimalEntry.getValue().doubleValue() == maxAverage)
                    .map(entry -> entry.getKey().getDisplayName())
                    .collect(Collectors.joining(", "));
        }
    }

    private String getHardestCourse(String easiestCourse) {
        if (!courseMapDifficulty.entrySet().stream().anyMatch(entry -> entry.getValue().compareTo(BigDecimal.ZERO) > 0))
            return "n/a";
        else {

            BigDecimal min = courseMapDifficulty.entrySet().stream()
                    .filter(entry -> !entry.getKey().getDisplayName().equalsIgnoreCase(easiestCourse))
                    .map(entry -> entry.getValue())
                    .min(BigDecimal::compareTo)
                    .get();

            return courseMapDifficulty.entrySet().stream()
                    .filter(courseNameBigDecimalEntry -> courseNameBigDecimalEntry.getValue() == min)
                    .map(entry -> entry.getKey().getDisplayName())
                    .collect(Collectors.joining(", "));
        }
    }
}
