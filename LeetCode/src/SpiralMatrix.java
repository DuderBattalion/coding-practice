import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public static void main(String[] args) {
//        int[][] matrix = {
//                { 1, 2, 3 },
//                { 4, 5, 6 },
//                { 7, 8, 9 }
//        };

        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9,10,11,12}
        };

//        int[][] matrix = {
//                {1, 2, 3},
//                {4, 5, 6}
//        };

//        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};

//        int[][] matrix = {
//            { 3 },
//            { 2 }
//        };

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
            processRightSpiral(matrix, rowStart + 1, rowEnd, colEnd, output);
            processBottomSpiral(matrix, rowEnd, colStart, colEnd - 1, output);
            processLeftSpiral(matrix, rowStart + 1, rowEnd - 1, colStart, output);

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

//    public static List<Integer> spiralOrder(int[][] matrix) {
//        List<Integer> output = new ArrayList<>();
//        if (matrix.length == 0) {
//            return output;
//        }
//
//        if (matrix.length == 1) {
//            for (int num: matrix[0]) {
//                output.add(num);
//            }
//
//            return output;
//        }
//
//        int rowSize = matrix.length;
//        int colSize = matrix[0].length;
//        int numInts = rowSize * colSize;
//
////        // Zero index
////        rowSize--;
////        colSize--;
//
//        Cell cell = new Cell(0, 0);
//        while (output.size() < numInts) {
//            cell = processTopSpiral(cell, colSize, matrix, output);
////            numInts -= colSize;
////            if (numInts <= 0) {
////                break;
////            }
//            if (output.size() == numInts) {
//                break;
//            }
//
////            // If only partial spiral, then print only what's needed
////            if (numInts < rowSize) {
////                rowSize = numInts;
////            }
//
//            cell = processRightSpiral(cell, rowSize, matrix, output);
////            numInts -= rowSize;
////            if (numInts <= 0) {
////                break;
////            }
////
////            if (numInts < colSize) {
////                colSize = numInts;
////            }
//
//            if (output.size() == numInts) {
//                break;
//            }
//
//            cell = processBottomSpiral(cell, colSize, matrix, output);
////            numInts -= colSize;
////            if (numInts <= 0) {
////                break;
////            }
////
////            if (numInts < rowSize) {
////                rowSize = numInts;
////            }
//
//            if (output.size() == numInts) {
//                break;
//            }
//
//            cell = processLeftSpiral(cell, rowSize, matrix, output);
////            numInts -= rowSize;
////            if (numInts <= 0) {
////                break;
////            }
//
//            if (output.size() == numInts) {
//                break;
//            }
//
//            rowSize-= 2;
//            colSize-= 2;
//        }
//
//        return output;
//    }
//
//    private static Cell processTopSpiral(Cell start, int colSize, int[][] matrix, List<Integer> output) {
//        if (colSize <= 1) {
//            output.add(matrix[start.row][start.col]);
//            return
//        } else {
//            for (int i = 0; i < colSize - 1; i++) {
//                output.add(matrix[start.row][start.col + i]);
//            }
//        }
//
//        return new Cell(start.row, start.col + (colSize - 1)); // Zero index
//    }
//
//    private static Cell processRightSpiral(Cell start, int rowSize, int[][] matrix, List<Integer> output) {
//        if (rowSize <= 1) {
//            output.add(matrix[start.row][start.col]);
//        } else {
//            for (int i = 0; i < rowSize - 1; i++) {
//                output.add(matrix[start.row + i][start.col]);
//            }
//        }
//
//        return new Cell(start.row + (rowSize - 1), start.col);
//    }
//
//    private static Cell processBottomSpiral(Cell start, int colSize, int[][] matrix, List<Integer> output) {
//        if (colSize <= 1) {
//            output.add(matrix[start.row][start.col]);
//            return new Cell(-1, -1);
//        } else {
//            for (int i = 0; i < colSize - 1; i++) {
//                output.add(matrix[start.row][start.col - i]);
//            }
//        }
//
//        return new Cell(start.row, start.col - (colSize - 1));
//    }
//
//    private static Cell processLeftSpiral(Cell start, int rowSize, int[][] matrix, List<Integer> output) {
//        if (rowSize <= 1) {
//            output.add(matrix[start.row][start.col]);
//            return new Cell(-1, -1);
//        } else {
//            for (int i = 0; i < rowSize - 1; i++) {
//                output.add(matrix[start.row - i][start.col]);
//            }
//        }
//
//        return new Cell(start.row - (rowSize - 2), start.col + 1);
//    }
//
//    private static class Cell {
//        public int row;
//        public int col;
//
//        public Cell(int row, int col) {
//            this.row = row;
//            this.col = col;
//        }
//    }
}
