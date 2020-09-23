import com.leetcode.util.DebugUtil;

public class BestTimeToBuyAndSellStock3 {
    public static void main(String[] args) {
//        int[] prices = { 3, 3, 5, 0, 0, 3, 1, 4 };
//        System.out.println(maxProfit(prices));

//        int[] prices = { 1, 2, 3, 4, 5 };
//        System.out.println(maxProfit(prices));

        int[] prices = { 5, 4, 3, 2, 1 };
        System.out.println(maxProfit(prices));

//        int[] prices = { 1 };
//        System.out.println(maxProfit(prices));
    }

    /**
     * DP algorithm:
     * t = transactions, dp rows
     * d = transaction day index, dp cols
     * DP Choices
     * 1) No transaction - copy over previous day profit
     * dp[t][d] = dp[t][d-1]
     * 2) Transact on day d:
     * max(price when selling stock on day d + max(-buying price on day x + max profit on previous transaction before day d)
     * max(price(d) + max(-price(x) + dp(t-1)(x))); x = 0 ...d
     */
    public static int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }

        int[][] dp = new int[3][prices.length];

        for (int t = 1; t <= 2; t++) {
            int maxPreviousProfitCombo = -prices[0] + dp[t-1][0];
            for (int d = 1; d < prices.length; d++) {
                int profitCombo = -prices[d] + dp[t-1][d];
                if (maxPreviousProfitCombo < profitCombo) {
                    maxPreviousProfitCombo = profitCombo;
                }

                dp[t][d] = Math.max(dp[t][d-1], prices[d] + maxPreviousProfitCombo);
            }
        }

        return dp[2][prices.length - 1];
    }
}
