package tracker.input;

import java.util.Scanner;

public class InputReader {

    public static Scanner scanner = new Scanner(System.in);

    public static String readLine() {
        return scanner.nextLine();
    }

    public static MenuOption readMenuOption() {
        String userOptionInput = readLine().strip();
        return MenuOption.parse(userOptionInput);
    }

    public static boolean userWantsBackToMenu(String input) {
        return MenuOption.parse(input) == MenuOption.BACK_OPTION;
    }

}