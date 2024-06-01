package tracker.platform;

import java.util.Map;

public record Submission(String studentId, Map<CourseName, Integer> pointsPerCourse) {
}
