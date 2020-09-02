public class ScrambleString {
    public static void main(String[] args) {
        System.out.println(isScramble("great", "rgeat"));
    }

    public static boolean isScramble(String s1, String s2) {
        if (!matchCharCounts(s1, s2)) {
            return false;
        }

        return isRecursiveScramble(s1, s2, 1);
    }

    private static boolean matchCharCounts(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        String s1Sorted = new StringBuilder(s1).reverse().toString();
        String s2Sorted = new StringBuilder(s2).reverse().toString();

        return s1Sorted.equals(s2Sorted);
    }

    private static boolean isRecursiveScramble(String s1, String s2, int splitIndex) {
        if (s1.equals(s2)) {
            return true;
        }

        if (splitIndex >= s1.length()) {
            return false;
        }

        String leftSubstring = s1.substring(0, splitIndex);
        String rightSubstring = s1.substring(splitIndex);
        String scrambledString = rightSubstring + leftSubstring;

        return isRecursiveScramble(s1, s2, splitIndex + 1)
                || isRecursiveScramble(scrambledString, s2, splitIndex + 1);

//        // Split s1 at each index
//        // Two paths:
//        // 1) Swap substring
//        // 2) Don't swap substring
//        // return (1) OR (2) result
//        for (int i = 1; i <= s1.length(); i++) {
//            String leftSubstring = s1.substring(0, i);
//            String rightSubstring = s1.substring(i, s1.length());
//            String scrambledString = rightSubstring + leftSubstring;
//
//            boolean isScrambled = isRecursiveScramble(l)
//        }
    }
}
