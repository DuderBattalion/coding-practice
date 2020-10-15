public class BuyAndSellStocks4 {
    public static void main(String[] args) {
        int[] prices = { 2, 4, 1 };
        int k = 2;

        System.out.println(maxProfit(k, prices));
    }

    /**
     * Algorithm: Dynamic programming
     * Let dp[t][i] - where t = transaction number, i = current day
     * Recurrence formula:
     * dp[t][i] = Math.max(dp[t][i-1], prices[i] - prices[j] + dp[t-1][j-1]);
     * j = 0 ... i
     *
     * Intuition:
     * At any day i, we have two choices:
     * 1) Don't see, so just copy over previous days profits
     * 2) Sell, total profit is today's profit plus profit across previous transactions.
     */
    public static int maxProfit(int k, int[] prices) {
        int[][] dp = new int[k+1][prices.length + 1];

        for (int t = 1; t <= k; t++) {
            for (int i = 1; i <= prices.length; i++) {
                int max = Integer.MIN_VALUE;
                for (int j = 1; j <= i; j++) {
                    int profit = prices[i-1] - prices[j-1] + dp[t-1][j-1]; //prices [i-1] because loop is 1 index'd
                    if (profit > max) {
                        max = profit;
                    }
                }

                dp[t][i] = Math.max(dp[t][i-1], max);
            }
        }

        return dp[k-1][prices.length];
    }
}
