package tracker.validators.credentials;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import tracker.input.CredentialsState;
import tracker.platform.Credentials;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class StudentCredentialsValidatorTest {



    @Test
    void shouldReturnValid() {
        String validInput = "Jane Doe jane.doe@yahoo.com";
        CredentialsState state = StudentCredentialsValidator.validateBeforeBeingParsed(validInput);
        assertEquals(CredentialsState.VALID, state);
    }

    @Test
    void shouldBackToMenu() {
        CredentialsState state = StudentCredentialsValidator.validateBeforeBeingParsed("back");
        assertEquals(CredentialsState.BACK, state);
    }

    @ParameterizedTest
    @ValueSource(strings = {"John Doe", "exit", ""})
    void shouldReturnNotValidState(String input) {
        CredentialsState state = StudentCredentialsValidator.validateBeforeBeingParsed(input);
        assertEquals(CredentialsState.NOT_VALID, state);
    }

    @Test
    void shouldValidateStudentCredentials() {
        String validName = "Jane";
        String validLastName = "Doe";
        String validEmail = "jane.doe@yahoo.com";
        assertTrue(StudentCredentialsValidator.validateStudentCredentials(validName, validLastName, validEmail));
    }

    @ParameterizedTest
    @MethodSource("provideCredentialsForValidation")
    void testEmailValidation(String name, String lastName, String email, boolean expected) {
        assertEquals(expected,
                StudentCredentialsValidator.validateStudentCredentials(name, lastName, email));
    }

    private static Stream<Arguments> provideCredentialsForValidation() {
        return Stream.of(
                // Valid combinations
                arguments("John", "Doe", "john.doe@example.com", true),
                arguments("Jane", "O'Connor", "jane_oconnor@example.com", true),
                arguments("Anne", "Marie Smith", "anne.marie.smith@example.co", true),
                arguments("Chris", "Johnson-Smith", "chris.johnson-smith@example.org", true),
                arguments("Mike", "Smith Jones", "mike.smith.jones@example.net", true),

                // Invalid name combinations
                arguments("J0hn", "Doe", "john.doe@example.com", false),
                arguments("John", "D0e", "john.doe@example.com", false),
                arguments("Jane", "O'Conn0r", "jane_oconnor@example.com", false),

                // Invalid email combinations
                arguments("John", "Doe", "plainaddress", false),
                arguments("Jane", "O'Connor", "@missingusername.com", false),
                arguments("Anne", "Marie Smith", "username@.com", false),
                arguments("Chris", "Johnson-Smith", "username@com", false),
                arguments("Mike", "Smith Jones", "username@example,com", false),

                // Invalid last name combinations
                arguments("John", "@Doe", "john.doe@example.com", false),
                arguments("Jane", "Doe!", "jane.doe@example.com", false),
                arguments("Anne", "", "anne.doe@example.com", false),

                // Completely invalid combinations
                arguments("J0hn", "D0e", "plainaddress", false),
                arguments("John", "O'Conn0r", "username@.com", false),
                arguments("Jane", "Doe!", "username@missingusername.com", false)
        );
    }

    @Test
    void shouldReturnCredentials() {
        Credentials credentials = StudentCredentialsValidator.extractCredentials("John Doe jdoe@mail.net");
        assertNotNull(credentials);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "John",
            "John Doe",
            "back"})
    void shouldReturnNullCredentials(String input) {
        Credentials credentials = StudentCredentialsValidator.extractCredentials(input);
        assertNull(credentials);
    }

}