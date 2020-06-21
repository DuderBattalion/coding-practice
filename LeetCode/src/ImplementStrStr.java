public class ImplementStrStr {
    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "ll";

        System.out.println(strStr(haystack, needle));
    }

    public static int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }

        if (haystack.length() < needle.length()) {
            return -1;
        }

        String token = haystack.substring(0, needle.length());
        if (token.equals(needle)) {
            return 0;
        }

        int index = -1;

        StringBuilder tokenBuf = new StringBuilder(token);
        for (int i = needle.length(); i < haystack.length(); i++) {
            // Sliding window: Remove 1st char, append next char
            token = token.substring(1, token.length()) + haystack.charAt(i);

            if (token.equals(needle)) {
                index = i - needle.length() + 1;
                break;
            }
        }

        return index;
    }
}
