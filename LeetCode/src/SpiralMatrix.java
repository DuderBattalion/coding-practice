import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public static void main(String[] args) {
//        int[][] matrix = {
//                { 1, 2, 3 },
//                { 4, 5, 6 },
//                { 7, 8, 9 }
//        };

//        int[][] matrix = {
//                {1, 2, 3, 4},
//                {5, 6, 7, 8},
//                {9,10,11,12}
//        };

//        int[][] matrix = {
//                {1, 2, 3},
//                {4, 5, 6}
//        };

//        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};

        int[][] matrix = {
            { 3 },
            { 2 }
        };

        List<Integer> output = spiralOrder(matrix);
        output.forEach(num -> System.out.print(num + ", "));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> output = new ArrayList<>();
        if (matrix.length == 0) {
            return output;
        }

        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        int numInts = rowSize * colSize;

        int rowStart = 0;
        int rowEnd = rowSize - 1;
        int colStart = 0;
        int colEnd = colSize - 1;

        while(output.size() < numInts) {
            processTopSpiral(matrix, rowStart, colStart, colEnd, output);
            if (output.size() >= numInts) {
                break;
            }

            processRightSpiral(matrix, rowStart + 1, rowEnd, colEnd, output);
            if (output.size() >= numInts) {
                break;
            }

            processBottomSpiral(matrix, rowEnd, colStart, colEnd - 1, output);
            if (output.size() >= numInts) {
                break;
            }

            processLeftSpiral(matrix, rowStart + 1, rowEnd - 1, colStart, output);
            if (output.size() >= numInts) {
                break;
            }

            rowStart += 1;
            colStart += 1;
            rowEnd -= 1;
            colEnd -= 1;
        }

        return output;
    }

    private static void processTopSpiral(int[][] matrix, int row, int colStart,
                                         int colEnd, List<Integer> output) {
        int col = colStart;
        while (col <= colEnd) {
            output.add(matrix[row][col]);
            col++;
        }
    }

    private static void processRightSpiral(int[][] matrix, int rowStart, int rowEnd,
                                           int col, List<Integer> output) {
        int row = rowStart;
        while (row <= rowEnd) {
            output.add(matrix[row][col]);
            row++;
        }
    }

    private static void processBottomSpiral(int[][] matrix, int row, int colStart,
                                            int colEnd, List<Integer> output) {
        int col = Math.max(colEnd, 0);
        while (col >= colStart) {
            output.add(matrix[row][col]);
            col--;
        }
    }

    private static void processLeftSpiral(int[][] matrix, int rowStart, int rowEnd,
                                          int col, List<Integer> output) {
        int row = Math.max(rowEnd, 0);
        while (row >= rowStart) {
            output.add(matrix[row][col]);
            row--;
        }
    }
}
