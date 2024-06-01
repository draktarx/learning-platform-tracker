import java.util.Scanner;

public class Main {

    // Create a method named 'process' to handle integers
    // Hint: When dealing with integers, you should return the square of the number
    static void process(Integer number) {
        double pow = Math.pow(number, 2);
        System.out.println((int) pow);
    }

    // Create a method named 'process' to handle floats
    // Hint: When dealing with floats, you should return the number rounded to the nearest whole number
    private static void process(Float floatInput) {
        double ceil = Math.round(floatInput);
        System.out.println((int) ceil);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read a line of input from the console
        String input = scanner.nextLine();

        try {
            Integer integerInput = Integer.parseInt(input);
            // Call the 'process' method with the integer input and print the result
            process(integerInput);
        } catch (NumberFormatException e) {
            Float floatInput = Float.parseFloat(input);
            // Call the 'process' method with the float input and print the result
            process(floatInput);
        }
    }


}