package tracker.input;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuOptionTest {

    @Test
    void parse() {
        assertAll(
                () -> assertEquals(MenuOption.STATS_OPTION, MenuOption.parse("statistics")),
                () -> assertEquals(MenuOption.BACK_OPTION, MenuOption.parse("back")),
                () -> assertEquals(MenuOption.ADD_STUDENTS_OPTION, MenuOption.parse("add students")),
                () -> assertEquals(MenuOption.LIST_STUDENTS_OPTION, MenuOption.parse("list")),
                () -> assertEquals(MenuOption.ADD_POINTS_OPTION, MenuOption.parse("add points")),
                () -> assertEquals(MenuOption.SHOW_STUDENT_POINTS_OPTION, MenuOption.parse("find")),
                () -> assertEquals(MenuOption.INVALID_OPTION, MenuOption.parse("help")),
                () -> assertEquals(MenuOption.EMPTY_OPTION, MenuOption.parse(""))
        );
    }
}