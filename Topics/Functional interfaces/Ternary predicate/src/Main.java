class Predicate {

    public static final TernaryIntPredicate ALL_DIFFERENT = Predicate::test;

    @FunctionalInterface
    public interface TernaryIntPredicate {
        boolean test(int a, int b, int c);
    }

    public static boolean test(int a, int b, int c) {
        return a != b && a != c && b != c;
    }
}