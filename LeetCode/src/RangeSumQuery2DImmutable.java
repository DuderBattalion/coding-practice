import com.leetcode.util.DebugUtil;

public class RangeSumQuery2DImmutable {
    int[][] matrix;
    int[][] prefixSumArray;

    /*
     * 1. Generate 2D prefix sum array. Start always (0,0).
     * 2. sumRegion (sx, sy), (ex, ey) = prefix sum of region (0, 0), (ex, ey) - (prefixSum of (0, 0), (ex, sy) + prefixSum of (0,0), (sx, ey) - prefixSum of (0,0), (sx, sy))
     */
    public RangeSumQuery2DImmutable(int[][] matrix) {
        this.matrix = matrix;

        int rows = matrix.length;
        int cols = rows > 0 ? matrix[0].length: 0;

        if (rows == 0 || cols == 0) {
            return; // throw error?
        }
    }

    /*
     * Prefix calculation is basically calling sumRegion,
     * but always starting with (0,0)
     */
    private int[][] calculatePrefixSum() {
        int rows = matrix.length;
        int cols = rows > 0 ? matrix[0].length: 0;

        prefixSumArray = new int[rows+1][cols+1];
        prefixSumArray[1][1] = matrix[0][0];


        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                prefixSumArray[i][j] = matrix[i-1][j-1] + prefixSumArray[i][j-1]
                        + prefixSumArray[i-1][j] - prefixSumArray[i-1][j-1];
            }
        }

        return prefixSumArray;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (prefixSumArray == null) {
            calculatePrefixSum();
        }

        // prefixSumArray is 1-index'd. Bump indexes by 1.
        row1++;
        col1++;
        row2++;
        col2++;

        int totalArea = getPrefixSum(row2, col2);
        int verticalArea = getPrefixSum(row2, col1 - 1);
        int horizontalArea = getPrefixSum(row1 - 1, col2);
        int commonArea = getPrefixSum(row1 - 1, col1 - 1);

        return totalArea - (verticalArea + horizontalArea - commonArea);
    }

    private int getPrefixSum(int row, int col) {
        if (row < 0 || col < 0) {
            return 0;
        }

        if (row >= prefixSumArray.length || col >= prefixSumArray[0].length) {
            throw new RuntimeException("[Error]: Range out of bound." + row + ", " + col);
        }

        return prefixSumArray[row][col];
    }
}
