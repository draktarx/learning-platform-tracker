class Seven {
    public static SeptenaryStringFunction fun = (String... tokens) -> String.join("", tokens).toUpperCase();
}

@FunctionalInterface
interface SeptenaryStringFunction {
    String apply(String... tokens);
}