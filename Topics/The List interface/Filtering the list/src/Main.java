import java.util.*;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        // put your code here
        List<Integer> list = solve(scan());
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer integer : list) {
            stringBuilder.append(" ").append(integer);
        }
        System.out.println(stringBuilder);
    }

    private static List<Integer> scan() {
        final Scanner scanner = new Scanner(System.in);
        final String[] values = scanner.nextLine().split("\\s+");
        return Arrays.stream(values)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static List<Integer> solve(List<Integer> numbers) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            if (i % 2 != 0) {
                result.add(numbers.get(i));
            }
        }

        Collections.reverse(result);
        return result;
    }
}