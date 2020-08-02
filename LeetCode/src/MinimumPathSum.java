public class MinimumPathSum {
    public static void main(String[] args) {
//        int[][] grid = {
//                {1,3,1},
//                {1,5,1},
//                {4,2,1}
//        };

//        int[][] grid = {};

//        int[][] grid = { {1, 2, 3} };
        int[][] grid = {
                {1},
                {2},
                {3}
        };

        System.out.println(minPathSum(grid));
    }

    public static int minPathSum(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int downVal = (i > 0) ? dp[i - 1][j] : 0;
                int rightVal = (j > 0) ? dp[i][j - 1] : 0;

                dp[i][j] = grid[i][j] + getMinNeighbor(downVal, rightVal);
            }
        }

        return dp[rows - 1][cols - 1];
    }

    private static int getMinNeighbor(int downVal, int rightVal) {
        int minVal;
        if (downVal != 0 && rightVal != 0) {
            minVal = Math.min(downVal, rightVal);
        } else if (downVal == 0) {
            minVal = rightVal;
        } else {
            minVal = downVal;
        }

        return minVal;
    }
}
