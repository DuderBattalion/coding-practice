public class DungeonGame {
    public static void main(String[] args) {

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
    public int calculateMinimumHP(int[][] dungeon) {
        int[][] dp = new int[dungeon.length+1][dungeon.length+1];


    }
}
