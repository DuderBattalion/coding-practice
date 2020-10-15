public class ShortestPalindrome {
    public static void main(String[] args) {
        System.out.println(shortestPalindrome("aacecaaa"));
        System.out.println(shortestPalindrome("abcd"));
        System.out.println(shortestPalindrome("aaaa"));
    }

    /**
     * Algorithm:
     * Find the longest palindrome from the start. Reverse remaining characters and
     * append to the start.
     * @param s
     * @return
     */
    public static String shortestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }

        String reverse = new StringBuilder(s).reverse().toString();
        String shortestPalindrome = "";

        for (int i = 0; i < s.length(); i++) {
            if (s.substring(0, s.length() - i).equals(reverse.substring(i))) {
                shortestPalindrome = reverse.substring(0, i) + s;
                break;
            }
        }

        return shortestPalindrome;
    }
}
