package tracker.validators.credentials;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class NameValidatorTest {

    private static final NameValidator nameValidator = new NameValidator();

    public static Stream<Arguments> provideNamesForValidation() {
        return Stream.of(
                // Valid names
                arguments("John", true),
                arguments("O'Connor", true),
                arguments("Anne-Marie", true),

                // Invalid names
                arguments("J0hn", false),
                arguments("John!", false),
                arguments("12345", false),
                arguments("", false),
                arguments("John-Doe-", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideNamesForValidation")
    void testNameValidation(String name, boolean expected) {
        assertEquals(expected, nameValidator.validate(name));
    }

}