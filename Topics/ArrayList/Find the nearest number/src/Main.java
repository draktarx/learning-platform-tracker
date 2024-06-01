import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);

        String[] listOfNumbersInput = scanner.nextLine().split("\\D");
        ArrayList<Integer> numbers = new ArrayList<>();
        for (String input : listOfNumbersInput) {
            numbers.add(Integer.parseInt(input));
        }
        Integer number = scanner.nextInt();

        Collections.sort(numbers);

        int diff = Math.abs(numbers.get(0) - number);
        ArrayList<Integer> closestNumbers = new ArrayList<>();

        for (Integer i : numbers) {
            int abs = Math.abs(i - number);
            if (abs < diff) {
                diff = abs;
                closestNumbers.clear();
                closestNumbers.add(i);
            } else if (abs == diff) {
                closestNumbers.add(i);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer closestNumber : closestNumbers) {
            stringBuilder.append(" ").append(closestNumber);
        }
        System.out.println(stringBuilder.toString());
    }
}