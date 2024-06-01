import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of elements
        int numElements = Integer.parseInt(scanner.nextLine().trim());

        // This deque will store the numbers
        Deque<Integer> deque = new ArrayDeque<>();

        // Iterate over each input and add to the deque based on even or odd
        for (int i = 0; i < numElements; i++) {
            int number = Integer.parseInt(scanner.nextLine().trim());

            if (number % 2 == 0) {
                deque.addFirst(number); // Add even numbers to the front
            } else {
                deque.addLast(number);  // Add odd numbers to the end
            }
        }

        // Output all elements in the deque from first to last
        for (int number : deque) {
            System.out.println(number);
        }

        scanner.close();
    }
}