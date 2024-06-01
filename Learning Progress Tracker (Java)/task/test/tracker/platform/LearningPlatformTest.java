/*
package tracker.platform;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LearningPlatformTest {

    // Compila la expresión regular
    Pattern studentIdPattern = Pattern.compile("Students:\n((\\b[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4" +
            "}-\\b[0-9a" +
            "-f]{12" +
            "}\\b)\n)*");

    private LearningPlatform learningPlatform;

    @BeforeEach
    void setUp() {
        learningPlatform = new LearningPlatform();
    }

    */
/**
     * Check if the provided email has been already used when adding information about students.
     * If so, respond with the following message: This email is already taken.
     *//*

    @Test
    void shouldReturnEmailIsAlreadyTaken() {
        String email = "test@example.com";
        String expectedMessage = "This email is already taken.";
        learningPlatform.addStudent("ID", new Student(new Credentials("Name", "Lastname", email)));
        String actualMessage = learningPlatform.emailIsTaken(email);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void addStudent() {
        assertTrue(learningPlatform.addStudent("ID", new Student(new Credentials("Name", "Lastname", "test@example.com"))), "Student should be added");
    }


    */
/**
     * Recognize the new list command to print the Students: a header followed by the student IDs.
     * The students must be listed in the order they were added. Remember, each ID must be unique.
     * If there are no students to list, print No students found.
     *//*

    @ParameterizedTest
    @MethodSource("provideStudentsForValidation")
    void listStudents(List<Student> students) {
        for (Student student : students) {
            learningPlatform.addStudent(UUID.randomUUID().toString(), student);
        }

        String result = learningPlatform.listStudents();
        System.out.println(result);

        Pattern pattern = Pattern.compile("Students:\n((\\b[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-\\b[0-9a-f]{12}\\b)\n)*");
        Matcher matcher = pattern.matcher(result);

        assertTrue(matcher.matches());
    }

    @Test
    void listEmptyStudents() {
        assertEquals("No students found", learningPlatform.listStudents());
    }

    private static Stream<List<Student>> provideStudentsForValidation() {
        return Stream.of(
                Arrays.asList(
                        new Student(new Credentials("name", "lastname", "user1@domain.com")),
                        new Student(new Credentials("name", "lastname", "user2@domain.com")),
                        new Student(new Credentials("name", "lastname", "user3@domain.com"))
                )
        );
    }

}*/
