import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the alphabets
        String originalAlphabet = scanner.nextLine();
        String cipherAlphabet = scanner.nextLine();

        // Encoding and decoding maps
        Map<Character, Character> encodeMap = new HashMap<>();
        Map<Character, Character> decodeMap = new HashMap<>();

        // Build the maps
        for (int i = 0; i < originalAlphabet.length(); i++) {
            encodeMap.put(originalAlphabet.charAt(i), cipherAlphabet.charAt(i));
            decodeMap.put(cipherAlphabet.charAt(i), originalAlphabet.charAt(i));
        }

        // Read the strings to encode and decode
        String stringToEncode = scanner.nextLine();
        String stringToDecode = scanner.nextLine();

        // Encode and decode
        String encodedString = encode(stringToEncode, encodeMap);
        String decodedString = decode(stringToDecode, decodeMap);

        // Output the results
        System.out.println(encodedString);
        System.out.println(decodedString);
    }

    private static String encode(String input, Map<Character, Character> map) {
        StringBuilder encoded = new StringBuilder();
        for (char c : input.toCharArray()) {
            encoded.append(map.get(c));
        }
        return encoded.toString();
    }

    private static String decode(String input, Map<Character, Character> map) {
        StringBuilder decoded = new StringBuilder();
        for (char c : input.toCharArray()) {
            decoded.append(map.get(c));
        }
        return decoded.toString();
    }
}
