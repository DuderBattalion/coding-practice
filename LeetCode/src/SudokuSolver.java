import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuSolver {
    public static void main(String[] args) {
//        char[][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        char[][] board = {{'.','.','9','7','4','8','.','.','.'},{'7','.','.','.','.','.','.','.','.'},{'.','2','.','1','.','9','.','.','.'},{'.','.','7','.','.','.','2','4','.'},{'.','6','4','.','1','.','5','9','.'},{'.','9','8','.','.','.','3','.','.'},{'.','.','.','8','.','3','.','2','.'},{'.','.','.','.','.','.','.','.','6'},{'.','.','.','2','7','5','9','.','.'}};

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
        solveBoard(board);
    }

    private static boolean solveBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != '.') {
                    continue;
                }

                for (int k = 1; k <= 9; k++) {
                    char numChar = Character.forDigit(k, 10);
                    if (isRowValid(board, numChar, i)
                            && isColValid(board, numChar, j)
                            && isGridValid(board, numChar, i, j)) {
                        board[i][j] = numChar;

                        if (solveBoard(board)) {
                            return true;
                        } else {
                            board[i][j] = '.'; // Backtrack
                        }
                    }
                }

                return false;
            }
        }

        return true;
    }

    private static boolean isRowValid(char[][] board, char num, int i) {
        if (i < 0 || i >= board.length) {
            throw new RuntimeException(
                    String.format("Unexpected error [isRowValid]: Row out of bounds. row: %d", i));
        }

        boolean isValid = true;
        for (int j = 0; j < board.length; j++) {
            if (board[i][j] == num) {
                isValid = false;
                break;
            }
        }

        return isValid;
    }

    private static boolean isColValid(char[][] board, char num, int j) {
        if (j < 0 || j >= board.length) {
            throw new RuntimeException(
                    String.format("Unexpected error [isColValid]: Col out of bounds. col: %d", j));
        }

        boolean isValid = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i][j] == num) {
                isValid = false;
                break;
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
        row = 3 * (row/3);
        col = 3 * (col/3);

        List<Character> gridVals = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gridVals.add(board[row+i][col+j]);
            }
        }

        return gridVals;
    }
}
