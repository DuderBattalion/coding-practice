public class DistinctSubsequences {
    public static void main(String[] args) {
        System.out.println(numDistinct("rabbbit", "rabbit"));
        System.out.println(numDistinct("babgbag", "bag"));
        System.out.println(numDistinct("bccbcdcabadabddbccaddcbabbaaacdba", "bccbbdc"));

        System.out.println(numDistinct("", "bag"));
        System.out.println(numDistinct("abcde", ""));
        System.out.println(numDistinct("", ""));
    }

    /**
     * Algorithm: DP solution
     * Rows are target subsequence characters, columns are source string
     * Recurrence formula:
     * dp[i][j] = All distinct subsequences prior to current target subsequence character (dp[i-1][j-1])
     *      + all subsequences calculated so far for this particular target subsequence character.
     * Disclaimer - some intuition taken from discussion:
     * https://leetcode.com/problems/distinct-subsequences/discuss/37327/Easy-to-understand-DP-in-Java
     */
    public static int numDistinct(String s, String t) {
        if (s.length() < t.length()) {
            return 0;
        }

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

        return dp[t.length()][s.length()];
    }

    private static void initDp(int[][] dp, String s, String t) {
        for (int j = 0; j < s.length(); j++) {
            dp[0][j] = 1;
        }
    }
}
