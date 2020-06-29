import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuSolver {
    public static void main(String[] args) {
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};

        printBoard(board);
        System.out.println("===================================");
        System.out.println();

        solveSudoku(board);
        printBoard(board);
    }

    private static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j =0; j < board.length; j++) {
                System.out.print(board[i][j] + ", ");
            }

            System.out.println();
        }
    }

    public static void solveSudoku(char[][] board) {
        solveBoard(board, 0, 0);
    }

    private static void solveBoard(char[][] board, int i, int j) {
        if (board.length == 0) {
            return;
        }

        Board nextIndex = getNextIndex(i, j);

        // Base case - end of board
        if (nextIndex.i > 9) {
            return;
        }

        if (board[i][j] != '.') {
            solveBoard(board, nextIndex.i, nextIndex.j);
        } else {
            boolean rowValid, colValid, gridValid;
            for (int num = 0; num < 10; num++) {
                char numChar = Character.forDigit(num, 10);
                if (!isRowValid(board, numChar, i)
                        || !isColValid(board, numChar, j)
                        || !isGridValid(board, numChar, i, j)) {
                    return;
                }

                board[i][j] = Character.forDigit(num, 10);
                solveBoard(board, nextIndex.i, nextIndex.j);

                // Backtrack
                board[i][j] = '.';
            }
        }
    }

    private static class Board {
        public int i;
        public int j;

        public Board(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private static Board getNextIndex(int i, int j) {
        if (i >= 9) {
            return new Board(-1, -1);
        }

        if (j + 1 >= 9) {
            return new Board(i + 1, 0); // New line
        }

        return new Board(i, j + 1);
    }

    private static boolean isRowValid(char[][] board, char num, int i) {
        if (i < 0 || i >= board.length) {
            throw new RuntimeException(
                    String.format("Unexpected error [isRowValid]: Row out of bounds. row: %d", i));
        }

        Set<Character> processedNums = new HashSet<>();

        boolean isValid = true;
        for (int j = 0; j < board.length; j++) {
            if (processedNums.contains(num)) {
                isValid = false;
                break;
            } else {
                processedNums.add(board[i][j]);
            }
        }

        return isValid;
    }

    private static boolean isColValid(char[][] board, char num, int j) {
        if (j < 0 || j >= board.length) {
            throw new RuntimeException(
                    String.format("Unexpected error [isColValid]: Col out of bounds. col: %d", j));
        }

        Set<Character> processedNums = new HashSet<>();

        boolean isValid = true;
        for (int i = 0; i < board.length; i++) {
            if (processedNums.contains(num)) {
                isValid = false;
                break;
            } else {
                processedNums.add(board[i][j]);
            }
        }

        return isValid;
    }

    private static boolean isGridValid(char[][] board, char num, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j > board.length) {
            throw new RuntimeException(
                    String.format("Unexpected error [isRowValid]: Index out of bounds. row: %d, col: %d", i, j));
        }

        List<Character> gridVals = getGridVals(board, i, j);

        boolean isValid = true;
        for (Character gridVal: gridVals) {
            if (gridVal != '.' && gridVal == num) {
                isValid = false;
                break;
            }
        }

        return isValid;
    }

    private static List<Character> getGridVals(char[][] board, int row, int col) {
        row = row/3;
        col = col/3;

        List<Character> gridVals = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gridVals.add(board[row+i][col+j]);
            }
        }

        return gridVals;
    }
}
