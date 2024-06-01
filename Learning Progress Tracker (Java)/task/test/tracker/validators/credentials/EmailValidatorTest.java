package tracker.validators.credentials;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EmailValidatorTest {

    private static final EmailValidator emailValidator = new EmailValidator();

    @ParameterizedTest
    @MethodSource("provideEmailsForValidation")
    void testEmailValidation(String email, boolean expected) {
        assertEquals(expected, emailValidator.validate(email));
    }

    private static Stream<Arguments> provideEmailsForValidation() {
        return Stream.of(
                // Valid email addresses
                arguments("john.doe@example.com", true),
                arguments("jane_doe@example.com", true),
                arguments("user123@example.co", true),
                arguments("user.name+tag@example.org", true),
                arguments("user-name@example.net", true),

                // Invalid email addresses
                arguments("plainaddress", false),
                arguments("@missingusername.com", false),
                arguments("username@.com", false),
                arguments("username@com", false),
                arguments("username@example,com", false),
                arguments("username@.example.com", false),
                arguments("username@-example.com", false),
                arguments("username@example..com", false),
                arguments("user name@example.com", false),
                arguments("", false)
        );
    }

}