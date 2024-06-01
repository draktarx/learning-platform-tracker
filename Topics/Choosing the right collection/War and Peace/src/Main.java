import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the input line
        String line = scanner.nextLine();

        // Convert the line to lowercase to ensure case insensitivity
        line = line.toLowerCase();

        // Split the line into words using spaces as delimiters
        String[] words = line.split("\\s+");

        // HashMap to store the count of each unique word
        Map<String, Integer> wordCount = new HashMap<>();

        // Count each word's occurrences
        for (String word : words) {
            if (word.isEmpty()) continue; // Skip empty words which might result from consecutive spaces
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // Print each word and its count
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        scanner.close();
    }
}
