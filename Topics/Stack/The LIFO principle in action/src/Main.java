import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        // Read the number of elements
        int n = Integer.parseInt(scanner.nextLine());

        // Use Deque as a stack to store the elements
        Deque<String> stack = new ArrayDeque<>();

        // Read each element and push it onto the stack
        for (int i = 0; i < n; i++) {
            stack.push(scanner.nextLine());
        }
        scanner.close();

        // Pop elements from the stack and print them
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}