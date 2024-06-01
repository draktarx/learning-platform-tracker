package tracker.input;

import java.util.Objects;

public enum MenuOption {
    NOTIFY_OPTION,
    STATS_OPTION,
    SHOW_STUDENT_POINTS_OPTION,
    ADD_POINTS_OPTION,
    LIST_STUDENTS_OPTION,
    ADD_STUDENTS_OPTION,
    BACK_OPTION,
    EMPTY_OPTION,
    EXIT_OPTION,
    INVALID_OPTION;

    public static MenuOption parse(String userInput) {
        if (Objects.isNull(userInput) || userInput.isEmpty()) {
            return EMPTY_OPTION;
        }

        String normalizedInput = userInput.toLowerCase();
        return switch (normalizedInput) {
            case "notify" -> NOTIFY_OPTION;
            case "statistics" -> STATS_OPTION;
            case "exit" -> EXIT_OPTION;
            case "back" -> BACK_OPTION;
            case "add students" -> ADD_STUDENTS_OPTION;
            case "list" -> LIST_STUDENTS_OPTION;
            case "add points" -> ADD_POINTS_OPTION;
            case "find" -> SHOW_STUDENT_POINTS_OPTION;
            default -> INVALID_OPTION;
        };
    }
}
