import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {
    public static void main(String[] args) {
        char[][] sudoku = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        char[][] invalidSudoku = {
                {'8','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        System.out.println(isValidSudoku(invalidSudoku));
    }

    public static boolean isValidSudoku(char[][] board) {
        if (board.length == 0) {
            return true;
        }

        int BOARD_SIZE = 9;

        boolean rowsColsValid = isRowsColsValid(board, BOARD_SIZE);
        if (!rowsColsValid) {
            return false;
        }

        return areGridsValid(board, BOARD_SIZE);
    }

    private static boolean areGridsValid(char[][] board, int size) {
        boolean isValid;
        for (int i = 0; i < size; i+=3) {
            for (int j = 0; j < size; j+=3) {
                isValid = isGridValid(board, size, i, j);
                if (!isValid) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isRowsColsValid(char[][] board, int size) {
        boolean isValid = true;
        boolean rowValid, colValid;
        for (int i = 0; i < size; i++) {
            rowValid = isRowValid(board, size, i);
            if (!rowValid) {
                isValid = false;
                break;
            }

            colValid = isColValid(board, size, i);
            if (!colValid) {
                isValid = false;
                break;
            }


        }


        return isValid;
    }

    private static boolean isRowValid(char[][] board, int size, int i) {
        if (i < 0 || i >= size) {
            return false;
        }

        boolean isValid = true;
        Set<Integer> processedNums = new HashSet<Integer>();
        int num;
        for (int j = 0; j < size; j++) {
            num = Character.getNumericValue(board[i][j]);

            if (num > 0) {
                if (processedNums.contains(num)) {
                    isValid = false;
                    break;
                } else {
                    processedNums.add(num);
                }
            }
        }

        return isValid;
    }

    private static boolean isColValid(char[][] board, int size, int j) {
        if (j < 0 || j >= size) {
            return false;
        }

        boolean isValid = true;
        Set<Integer> processedNums = new HashSet<Integer>();
        int num;

        for (int i = 0; i < size; i++) {
            num = Character.getNumericValue(board[i][j]);

            if (num > 0) {
                if (processedNums.contains(num)) {
                    isValid = false;
                    break;
                } else {
                    processedNums.add(num);
                }
            }
        }

        return isValid;
    }



    private static boolean isGridValid(char[][] board, int size, int row, int col) {
        boolean isValid = true;
        Set<Integer> processedNums = new HashSet<Integer>();
        int num;

        for (int i = row; i < 9; i+=3) {
            for (int j = col; j < 9; j+=3) {
                num = Character.getNumericValue(board[i][j]);

                if (num > 0) {
                    if (processedNums.contains(num)) {
                        isValid = false;
                        break;
                    } else {
                        processedNums.add(num);
                    }
                }
            }
        }

        return isValid;
    }

}
