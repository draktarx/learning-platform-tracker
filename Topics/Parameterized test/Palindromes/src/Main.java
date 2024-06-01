class StringUtils {
    public static boolean isPalindrome(String str) {
        if (str == null || str.isBlank() || str.length() < 2) {
            return false;
        }

        String tmp = str.replaceAll("\\s+|'", "");
        return new StringBuilder(tmp).reverse().toString()
                .equalsIgnoreCase(tmp);
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("Ill will"));
    }
}