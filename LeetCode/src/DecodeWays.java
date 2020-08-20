public class DecodeWays {
    public static void main(String[] args) {
        System.out.println(numDecodings("12"));
    }

    public static int numDecodings(String s) {
        if (s.isEmpty()) {
            return 0;
        }

        int[] dp = new int[s.length() + 1];

        dp[0] = 1; // Empty string has 1 way of decoding
        dp[1] = s.charAt(0) != '0' ? 1 : 0;

        for (int i = 2; i <= s.length(); i++) {
            int oneChar = Integer.parseInt(s.substring(i - 1, i));
            int twoChar = Integer.parseInt(s.substring(i - 2, i));

            if (oneChar >= 1 && oneChar <= 9) {
                dp[i] += dp[i - 1];
            }

            if (twoChar >= 10 && twoChar <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[dp.length - 1];
    }
}
