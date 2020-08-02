public class MinimumPathSum {
    public static void main(String[] args) {
        int[][] grid = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };

//        int[][] grid = {};

//        int[][] grid = { {1, 2, 3} };
//        int[][] grid = {
//                {1},
//                {2},
//                {3}
//        };

//        int[][] grid = {
//                {0,2,2,6,4,1,6,2,9,9,5,8,4,4},
//                {0,3,6,4,5,5,9,7,8,3,9,9,5,4},
//                {6,9,0,7,2,2,5,6,3,1,0,4,2,5},
//                {3,8,2,3,2,8,8,7,5,9,6,3,4,5},
//                {4,0,1,3,9,2,0,1,6,7,9,2,8,9},
//                {6,2,7,9,0,9,5,2,7,5,1,4,4,7},
//                {9,8,3,3,0,6,8,0,8,8,3,5,7,3},
//                {7,7,4,5,9,1,5,0,2,2,2,1,7,4},
//                {5,1,3,4,1,6,0,4,3,8,4,3,9,9},
//                {0,6,4,9,4,1,5,5,4,2,5,7,4,0},
//                {0,1,6,6,4,9,2,7,8,2,1,3,3,7},
//                {8,4,9,9,2,3,8,6,6,5,4,1,7,9}
//        };

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
                int downVal = (i > 0) ? dp[i - 1][j] : -1;
                int rightVal = (j > 0) ? dp[i][j - 1] : -1;

                dp[i][j] = grid[i][j] + getMinNeighbor(downVal, rightVal);
            }
        }

        return dp[rows - 1][cols - 1];
    }

    private static int getMinNeighbor(int downVal, int rightVal) {
        int minVal;
        if (downVal != -1 && rightVal != -1) {
            minVal = Math.min(downVal, rightVal);
        } else if (downVal == -1 && rightVal == -1) {
            minVal = 0;
        } else if (downVal == -1){
            minVal = rightVal;
        } else {
            minVal = downVal;
        }

        return minVal;
    }
}
