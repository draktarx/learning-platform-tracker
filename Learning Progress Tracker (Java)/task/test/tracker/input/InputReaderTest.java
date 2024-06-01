package tracker.input;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class InputReaderTest {

    @Test
    void userWantsBackToMenu() {
        assertAll(
                () -> assertTrue(InputReader.userWantsBackToMenu("back")),
                () -> assertTrue(InputReader.userWantsBackToMenu("BACK")),
                () -> assertTrue(InputReader.userWantsBackToMenu("bAcK")),
                () -> assertFalse(InputReader.userWantsBackToMenu("help")),
                () -> assertFalse(InputReader.userWantsBackToMenu("-back"))
        );
    }
}