public class MinimumPathSum {
    public static void main(String[] args) {
        int[][] grid = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };

        System.out.println(minPathSum(grid));
    }

    public static int minPathSum(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        int[][] dp = new int[rows + 1][cols + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                dp[i][j] = grid[i-1][j-1] + Math.min(dp[i][j-1], dp[i-1][j]);
            }
        }

        return dp[rows][cols];
    }
}
