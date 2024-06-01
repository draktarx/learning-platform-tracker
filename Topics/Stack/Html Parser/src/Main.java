import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '<') {
                if (str.charAt(i + 1) != '/') {
                    stack.push(str.indexOf('>', i) + 1);
                } else {
                    int indexOpen = stack.pop();
                    System.out.println(str.substring(indexOpen, i));
                }
            }
        }
    }
}