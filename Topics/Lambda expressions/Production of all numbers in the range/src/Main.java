import java.util.function.*;


class Operator {

    public static LongBinaryOperator binaryOperator = (a, b) -> {
        long result = 1L;
        for (long i = a ; i <= b; i++) {
            result *= i;
        }
        return result;
    };
}