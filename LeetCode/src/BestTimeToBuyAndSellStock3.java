import com.leetcode.util.DebugUtil;

public class BestTimeToBuyAndSellStock3 {
    public static void main(String[] args) {
        int[] prices = { 3, 3, 5, 0, 0, 3, 1, 4 };
        System.out.println(maxProfit(prices));
    }

    /**
     * DP algorithm:
     * Let dp(i, j) = i is transaction number, and j is sell date,
     * then,
     * dp(i, j) = two choices
     * a) Don't sell stock today - copy over value from previous day dp(i, j-1)
     * b) Sell stock today - today's profit/loss (price(j) - price(k) + previous transaction dp(i-1, j-1)), where
     * k = the day stock was bought (where k = 0 ... j-1)
     *
     * which gives us the recurrence formula:
     * dp(i, j) = max(dp(i, j-1), price(j) - price(k) + dp(i-1, j-1)); k = 0...j-1
     */
    public static int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }

        int[][] dp = new int[3][prices.length];

//        for (int i = 1; i <= prices.length; i++) {
//            for (int j = 1; j <= i-1; j++) {
//                dp[i][j] = Math.max(dp[i][j-1], prices[j-1] - prices[i-1] + dp[i-1][j-1]); // price is zero index'd
//            }
//        }

        for (int i = 1; i <= 2; i++) { // Num transactions
            for (int j = 1; j < prices.length; j++) {
                for (int k = 1; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j-1], prices[j] - prices[k] + dp[i-1][j-1]);
                }
            }
        }

        DebugUtil.debugDpCache(dp, 3, prices.length);

        return dp[2][prices.length - 1];
    }
}
