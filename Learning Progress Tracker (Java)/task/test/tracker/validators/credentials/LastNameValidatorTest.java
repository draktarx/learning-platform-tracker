package tracker.validators.credentials;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class LastNameValidatorTest {

    private static final LastNameValidator lastNameValidator = new LastNameValidator();

    @ParameterizedTest
    @MethodSource("provideLastNamesForValidation")
    void testLastNameValidation(String lastName, boolean expected) {
        assertEquals(expected, lastNameValidator.validate(lastName));
    }

    private static Stream<Arguments> provideLastNamesForValidation() {
        return Stream.of(
                // Valid last names
                arguments("Doe", true),
                arguments("O'Connor", true),
                arguments("Anne-Marie", true),
                arguments("Smith-Jones", true),
                arguments("Smith Jones", true),

                // Invalid last names
                arguments("D0e", false),
                arguments("O'Connell$", false),
                arguments("Anne-Marie-", false),
                arguments("Smith-J0nes", false),
                arguments("Smith@", false),
                arguments("", false),
                arguments("Smith--Jones", false)
        );
    }
}