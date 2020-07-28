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

        List<Integer> output = spiralOrder(matrix);
        output.forEach(num -> System.out.print(num + ", "));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> output = new ArrayList<>();
        if (matrix.length == 0) {
            return output;
        }

        if (matrix.length == 1) {
            for (int num: matrix[0]) {
                output.add(num);
            }

            return output;
        }

        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        int numInts = rowSize * colSize;

//        // Zero index
//        rowSize--;
//        colSize--;

        Cell cell = new Cell(0, 0);
        while (numInts > 0) {
            cell = processTopSpiral(cell, colSize, matrix, output);
//            numInts -= colSize;
//            if (numInts <= 0) {
//                break;
//            }
            if (output.size() == numInts) {
                break;
            }

//            // If only partial spiral, then print only what's needed
//            if (numInts < rowSize) {
//                rowSize = numInts;
//            }

            cell = processRightSpiral(cell, rowSize, matrix, output);
//            numInts -= rowSize;
//            if (numInts <= 0) {
//                break;
//            }
//
//            if (numInts < colSize) {
//                colSize = numInts;
//            }

            if (output.size() == numInts) {
                break;
            }

            cell = processBottomSpiral(cell, colSize, matrix, output);
//            numInts -= colSize;
//            if (numInts <= 0) {
//                break;
//            }
//
//            if (numInts < rowSize) {
//                rowSize = numInts;
//            }

            if (output.size() == numInts) {
                break;
            }

            cell = processLeftSpiral(cell, rowSize, matrix, output);
//            numInts -= rowSize;
//            if (numInts <= 0) {
//                break;
//            }

            if (output.size() == numInts) {
                break;
            }

            rowSize-= 2;
            colSize-= 2;
        }

        return output;
    }

    private static Cell processTopSpiral(Cell start, int colSize, int[][] matrix, List<Integer> output) {
        if (colSize <= 1) {
            output.add(matrix[start.row][start.col]);
            return new Cell(-1, -1);
        }

        for (int i = 0; i < colSize - 1; i++) {
            output.add(matrix[start.row][start.col + i]);
        }

        return new Cell(start.row, start.col + (colSize - 1)); // Zero index
    }

    private static Cell processRightSpiral(Cell start, int rowSize, int[][] matrix, List<Integer> output) {
        if (rowSize <= 1) {
            output.add(matrix[start.row][start.col]);
            return new Cell(-1, -1);

        }

        for (int i = 0; i < rowSize - 1; i++) {
            output.add(matrix[start.row + i][start.col]);
        }

        return new Cell(start.row + (rowSize - 1), start.col);
    }

    private static Cell processBottomSpiral(Cell start, int colSize, int[][] matrix, List<Integer> output) {
        if (colSize <= 1) {
            output.add(matrix[start.row][start.col]);
            return new Cell(-1, -1);
        }

        for (int i = 0; i < colSize - 1; i++) {
            output.add(matrix[start.row][start.col - i]);
        }

        return new Cell(start.row, start.col - (colSize - 1));
    }

    private static Cell processLeftSpiral(Cell start, int rowSize, int[][] matrix, List<Integer> output) {
        if (rowSize <= 1) {
            output.add(matrix[start.row][start.col]);
            return new Cell(-1, -1);
        }

        for (int i = 0; i < rowSize - 1; i++) {
            output.add(matrix[start.row - i][start.col]);
        }

        return new Cell(start.row - (rowSize - 2), start.col + 1);
    }

    private static class Cell {
        public int row;
        public int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
