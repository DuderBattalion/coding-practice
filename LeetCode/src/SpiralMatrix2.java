import java.util.Arrays;

public class SpiralMatrix2 {
    public static int count = 1;

    public static void main(String[] args) {
        int[][] output = generateMatrix(1);

        for (int[] row: output) {
            Arrays.stream(row).forEach(num -> System.out.print(num + ","));
            System.out.println();
        }
    }

    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        int rowSize = n;
        int colSize = n;
        int numInts = rowSize * colSize;

        int rowStart = 0;
        int rowEnd = rowSize - 1;
        int colStart = 0;
        int colEnd = colSize - 1;

        while(count <= numInts) {
            processTopSpiral(matrix, rowStart, colStart, colEnd);
            if (count > numInts) {
                break;
            }

            processRightSpiral(matrix, rowStart + 1, rowEnd, colEnd);
            if (count > numInts) {
                break;
            }

            processBottomSpiral(matrix, rowEnd, colStart, colEnd - 1);
            if (count > numInts) {
                break;
            }

            processLeftSpiral(matrix, rowStart + 1, rowEnd - 1, colStart);
            if (count > numInts) {
                break;
            }

            rowStart += 1;
            colStart += 1;
            rowEnd -= 1;
            colEnd -= 1;
        }

        return matrix;
    }

    private static void processTopSpiral(int[][] matrix, int row, int colStart,
                                         int colEnd) {
        int col = colStart;
        while (col <= colEnd) {
            matrix[row][col] = count;
            count++;
            col++;
        }
    }

    private static void processRightSpiral(int[][] matrix, int rowStart, int rowEnd,
                                           int col) {
        int row = rowStart;
        while (row <= rowEnd) {
            matrix[row][col] = count;
            count++;
            row++;
        }
    }

    private static void processBottomSpiral(int[][] matrix, int row, int colStart,
                                            int colEnd) {
        int col = Math.max(colEnd, 0);
        while (col >= colStart) {
            matrix[row][col] = count;
            count++;
            col--;
        }
    }

    private static void processLeftSpiral(int[][] matrix, int rowStart, int rowEnd,
                                          int col) {
        int row = Math.max(rowEnd, 0);
        while (row >= rowStart) {
            matrix[row][col] = count;
            count++;
            row--;
        }
    }
}
