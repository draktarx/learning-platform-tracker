package tracker.validators;

import org.junit.jupiter.api.Test;
import tracker.platform.CourseName;

import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AddPointsValidatorTest {

    @Test
    void validate() {
        AddPointsValidator validator = new AddPointsValidator();
        assertAll(
                () -> assertTrue(validator.validate(UUID.randomUUID() + "\s2 8 22 0")),
                () -> assertFalse(validator.validate(UUID.randomUUID() + "\s2 22 0")),
                () -> assertFalse(validator.validate(UUID.randomUUID() + "\s2 f 22 0")),
                () -> assertFalse(validator.validate(UUID.randomUUID() + "\s1 1 1 1 1")),
                () -> assertFalse(validator.validate("2 9 22 0"))
        );
    }

    @Test
    void validateMoreDigitsThanCourses() {
        AddPointsValidator validator = new AddPointsValidator();
        assertFalse(validator.validate(UUID.randomUUID() + "\s1 1 1 1 1"));
    }

    @Test
    void extractStudentId() {
        AddPointsValidator validator = new AddPointsValidator();
        String studentId = UUID.randomUUID().toString();
        assertAll(
                () -> assertEquals(studentId, validator.extractStudentId(studentId + "\s2 8 22 0")),
                () -> assertNull(validator.extractStudentId("2 8 22 0"))
        );
    }

    @Test
    void extractPointsToAdd() {
        AddPointsValidator validator = new AddPointsValidator();
        int[] points = {2, 8, 22, 0};
        String input = UUID.randomUUID() + "\s2 8 22 0";
        HashMap<CourseName, Integer> courseMapPoints = validator.extractPointsToAdd(input);
        HashMap<CourseName, Integer> expectedCourseMapPoints = new HashMap<>();
        CourseName[] values = CourseName.values();
        for (int i = 0; i < values.length; i++) {
            CourseName courseName = values[i];
            expectedCourseMapPoints.put(courseName, points[i]);
        }
        assertEquals(expectedCourseMapPoints, courseMapPoints);
    }
}