import com.leetcode.util.FenwickTreeSum2D;

public class RangeSumQuery2DMutable {
    FenwickTreeSum2D tree;

    public RangeSumQuery2DMutable(int[][] matrix) {
        int rows = matrix.length;
        int cols = rows > 0 ? matrix[0].length : 0;

        tree = new FenwickTreeSum2D();
        tree.initTree(matrix);
    }

    public void update(int row, int col, int val) {
        tree.update(row, col, val);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int totalArea = tree.getSum(row2, col2);
        int verticalArea = tree.getSum(row2, col1 - 1);
        int horizontalArea = tree.getSum(row1 - 1, col2);
        int commonArea = tree.getSum(row1 - 1, col1 - 1);

        return totalArea - (verticalArea + horizontalArea - commonArea);
    }
}
