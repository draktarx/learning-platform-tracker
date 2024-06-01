import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of strings in the sequence
        int numStrings = Integer.parseInt(scanner.nextLine());

        // TreeSet to store strings with natural ordering and no duplicates
        Set<String> sortedStrings = new TreeSet<>();

        // Reading each string and adding to the TreeSet to ensure no duplicates
        for (int i = 0; i < numStrings; i++) {
            sortedStrings.add(scanner.nextLine());
        }

        // Printing out strings in lexicographical order without duplicates
        for (String str : sortedStrings) {
            System.out.println(str);
        }

        scanner.close();
    }
}