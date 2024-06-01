import java.util.*;


class MapFunctions {

    public static void printWithSameLetter(Map<String, String> map) {
        // write your code here
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String country = entry.getKey();
            String capital = entry.getValue();

            if (Character.toLowerCase((country.charAt(0))) == Character.toLowerCase(capital.charAt(0))) {
                System.out.println(country + " " + capital);
            }
        }

    }
}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> map = new HashMap<>();

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] pair = s.split(" ");
            map.put(pair[0], pair[1]);
        }

        MapFunctions.printWithSameLetter(map);
    }
}