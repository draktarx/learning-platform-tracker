package tracker.ui;

import org.junit.jupiter.api.Test;
import tracker.input.MenuAction;
import tracker.input.MenuOption;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UITest {

    @Test
    void testShowStudentPoints() {
        MenuAction expectedAction = MenuAction.SHOW_STUDENT_POINTS_ACTION;
        assertEquals(expectedAction, UI.mainMenu(MenuOption.SHOW_STUDENT_POINTS_OPTION));
    }

    @Test
    void testAddPointsToStudent() {
        MenuAction expectedAction = MenuAction.ADD_POINTS_ACTION;
        assertEquals(expectedAction, UI.mainMenu(MenuOption.ADD_POINTS_OPTION));
    }

    @Test
    void testListStudents() {
        MenuAction expectedAction = MenuAction.lIST_STUDENTS_ACTION;
        assertEquals(expectedAction, UI.mainMenu(MenuOption.LIST_STUDENTS_OPTION));
    }

    @Test
    void testAddStudent() {
        MenuAction expectedAction = MenuAction.ADD_STUDENTS_ACTION;
        assertEquals(expectedAction, UI.mainMenu(MenuOption.ADD_STUDENTS_OPTION));
    }

    @Test
    void testExitOption() {
        MenuAction expectedAction = MenuAction.EXIT_ACTION;
        assertEquals(expectedAction, UI.mainMenu(MenuOption.EXIT_OPTION));
    }

    @Test
    void testNoActionsOptions() {
        MenuAction expectedAction = MenuAction.NO_ACTION;
        assertAll(
                () -> assertEquals(expectedAction, UI.mainMenu(MenuOption.BACK_OPTION)),
                () -> assertEquals(expectedAction, UI.mainMenu(MenuOption.EMPTY_OPTION)),
                () -> assertEquals(expectedAction, UI.mainMenu(MenuOption.INVALID_OPTION))
        );

    }
}