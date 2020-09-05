public class DistinctSubsequences {
    public static void main(String[] args) {
        System.out.println(numDistinct("rabbbit", "rabbit"));
        System.out.println(numDistinct("babgbag", "bag"));
//        System.out.println(numDistinct("bccbcdcabadabddbccaddcbabbaaacdba", "bccbbdc"));

//        System.out.println(numDistinct("", "bag"));
//        System.out.println(numDistinct("abcde", ""));
//        System.out.println(numDistinct("", ""));
    }

    public static int numDistinct(String s, String t) {
        if (s.length() < t.length()) {
            return 0;
        }

//        return calculateNumDistinct(s, t, 0, 0, "");
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        initDp(dp, s, t);

        for (int i = 1; i <= t.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                if (s.charAt(j-1) == t.charAt(i-1)) { // Zero index'd
                    dp[i][j] = dp[i-1][j-1] + dp[i][j-1]; // subSeqs from (i-1) cases + subSeqs counted until now for i
                } else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }

//        debugDp(dp, t.length(), s.length());
        return dp[t.length()][s.length()];
    }

    private static void debugDp(int[][] dp, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(dp[i][j] + " ");
            }

            System.out.println();
        }
    }

    private static void initDp(int[][] dp, String s, String t) {
        for (int j = 0; j < s.length(); j++) {
            dp[0][j] = 1;
        }
    }

//    // TODO - convert to a dp solution to increase performance
//    private static int calculateNumDistinct(String s, String t, int i, int count, String subSequence) {
//        if (i >= s.length()) {
//            return count;
//        }
//
//        // Cases:
//        // 1) Skip s.charAt(i)
//        // 2) Include s.charAt(i)
//
//        // Case 1: Skip s.charAt(i)
//        count = calculateNumDistinct(s, t, i + 1, count, subSequence);
//
//        // Case 2: Include s.charAt(i)
//        subSequence += s.charAt(i);
//        if (subSequence.equals(t)) {
//            count++;
//        }
//        count = calculateNumDistinct(s, t, i + 1, count, subSequence);
//
//        return count;
//    }
}
