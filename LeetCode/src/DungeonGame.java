import com.leetcode.util.DebugUtil;

public class DungeonGame {
    public static void main(String[] args) {
//        int[][] dungeon = {
//                { -2, -3, 3},
//                { -5, -10, 1},
//                {10, 30, -5 }
//        };

//        int[][] dungeon = {
//                { 0 }
//        };

//        int[][] dungeon = {
//                { 0, 0 },
//        };

        int[][] dungeon = {
                { -3, 5 }
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
        int M = dungeon.length;
        int N = dungeon[0].length;

        int[][] dp = new int[M][N];
        dp[M-1][N-1] = 1 - dungeon[M-1][N-1];
        if (dp[M-1][N-1] <= 0) {
            dp[M-1][N-1] = 1; // Negative means excess health, anchor at 1.
        }

        for (int i = M - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (i == (M - 1) && j == (N - 1)) {
                    continue; // Already set outside loop
                }

                int rightVal = Integer.MAX_VALUE, downVal = Integer.MAX_VALUE;
                if (j + 1 < N) {
                    rightVal = dp[i][j+1];
                }

                if (i + 1 < M) {
                    downVal = dp[i+1][j];
                }

                dp[i][j] = Math.min(rightVal - dungeon[i][j], downVal - dungeon[i][j]);
                if (dp[i][j] <= 0) {
                    dp[i][j] = 1; // Note: Negative values show excess health which is not required. Anchor at 1.
                }
            }
        }

        DebugUtil.debugDpCache(dp, dungeon.length, dungeon[0].length);
        return dp[0][0];
    }
}
