import java.util.Arrays;

public class Search2DMatrix {
    public static void main(String[] args) {
//        int[][] matrix = {
//                {1,   3,  5,  7},
//                {10, 11, 16, 20},
//                {23, 30, 34, 50}
//        };

        int[][] matrix = {{}};

        System.out.println(searchMatrix(matrix, 51));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[rows - 1][cols - 1]) {
            return false;
        }

        boolean isTargetFound = false;
        for (int i = 0; i < matrix.length; i++) {
            if (target >= matrix[i][0] && target <= matrix[i][cols - 1]) {
                int index = Arrays.binarySearch(matrix[i], target);
                isTargetFound = (index >= 0);
                break;
            }
        }

        return isTargetFound;
    }
}
