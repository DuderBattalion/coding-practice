public class DungeonGame {
    public static void main(String[] args) {
        int[][] dungeon = {
                { -2, -3, 3},
                { -5, -10, 1},
                {10, 30, -5 }
        };

        System.out.println(calculateMinimumHP(dungeon));
    }

    /**
     * Algorithm: Dynamic programming
     * Go in reverse direction - from princess to knight
     * Health of knight at princess cell should be 1 or more.
     *
     * Recurrence formula:
     * dp[i][j] = Math.min(dp[i][j+1] - dungeon[i][j]. dp[i+1][j] - dungeon[i][j]);
     * Note that if value falls below zero, make it 1. Negative value shows excess health which is not needed.
     * @param dungeon
     * @return
     */
    public static int calculateMinimumHP(int[][] dungeon) {
        int[][] dp = new int[dungeon.length+1][dungeon.length+1];

        for (int i = dungeon.length - 1; i >= 0; i--) {
            for (int j = dungeon.length - 1; j >= 0; j--) {
                int rightVal = 0, downVal = 0;
                if (j + 1 < dungeon.length) {
                    rightVal = dp[i][j+1];
                }

                if (i + 1 < dungeon.length) {
                    downVal = dp[i+1][j];
                }

                dp[i][j] = Math.min(rightVal - dungeon[i][j], downVal - dungeon[i][j]);
                if (dp[i][j] <= 0) {
                    dp[i][j] = 1; // Note: Negative values show excess health which is not required. Anchor at 1.
                }
            }
        }

        return dp[0][0] + 1;
    }
}
