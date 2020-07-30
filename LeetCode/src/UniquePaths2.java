import java.util.HashMap;
import java.util.Map;

public class UniquePaths2 {
    public static void main(String[] args) {
//        int[][] obstacleGrid = {
//                {0,0,0},
//                {0,1,0},
//                {0,0,0}
//        };

//        int[][] obstacleGrid = {};

        int[][] obstacleGrid = {
                {0,1,0}
        };

        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0) {
            return 1;
        }

        int n = obstacleGrid.length; // rows
        int m = obstacleGrid[0].length; // cols

        if (m == 1 && n == 1) {
            return 1;
        }

        return calcNumPaths(0, 0, n - 1, m - 1,
                new HashMap<>(), obstacleGrid); // Zero index'd
    }

    private static int calcNumPaths(int i, int j, int row, int col,
                                    Map<String, Integer> cache, int[][] obstacleGrid) {
        // Base case - target found
        if (i == row && j == col) {
            return 1;
        }

        // Invalid index - return
        if (i < 0 || i > row || j < 0 || j > col || obstacleGrid[i][j] == 1) {
            return 0;
        }

        String key = i + "," + j;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int numPaths = calcNumPaths(i + 1, j, row, col, cache, obstacleGrid) +  // Down
                calcNumPaths(i, j + 1, row, col, cache, obstacleGrid); // Right

        cache.put(key, numPaths);
        return numPaths;
    }
}
