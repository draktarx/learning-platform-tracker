package tracker.validators;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UUIDValidatorTest {

    @Test
    void validate() {
        UUIDValidator uuidValidator = new UUIDValidator();
        assertAll(
                () -> assertFalse(uuidValidator.validate("back")),
                () -> assertFalse(uuidValidator.validate("123")),
                () -> assertTrue(uuidValidator.validate(UUID.randomUUID().toString()))
        );
    }
}