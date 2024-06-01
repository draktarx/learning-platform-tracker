import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Deque<Integer> stack = new ArrayDeque<>();
        Scanner scanner = new Scanner(System.in);
        int numberOfCommands = scanner.nextInt();
        while (numberOfCommands-- > 0) {
            switch (scanner.next()) {
                case "push": int i = scanner.nextInt();
                    stack.push(stack.isEmpty() || i >= stack.peek() ? i : stack.peek());
                    break;
                case "pop": stack.pop();
                    break;
                default: System.out.println(stack.peek());
            }
        }
    }
}