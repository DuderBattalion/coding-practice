import java.util.*;

public class NQueens {
    public static void main(String[] args) {

    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> output = new ArrayList<>();
        Deque<Cell> config = new LinkedList<>();
        int[][] board = new int[n][n];

        return calcConfigs(board, n, i, config, output);
    }

    private void calcConfigs(int board, int n, int i,
                             Set<Integer> coveredRows,
                             Set<Integer> coveredCols,
                             Deque<Cell> config,
                             List<List<String>> output) {
        if (config.size() == n) {
            addConfigToOutput(output, config);
            return;
        }

        if (i >= n) {
            return;
        }

        for (int j = 0; j < n; j++) {
            boolean isValid = isValidConfig(board, i, j, coveredRows, coveredCols);
            if (isValid) {
                config.push(new Cell(i, j));

                calcConfigs(board, n, i + 1, config, output);

                // Backtrack
                config.pop();
            }
        }
    }

    /**
     * Verifies if a position in the board is a marked position already covered by a queen.
     * If so, returns true. If not, returns false.
     */
    private boolean isValidConfig(int board, int i, int j,
                                  Set<Integer> coveredRows,
                                  Set<Integer> coveredCols) {
        boolean isValid = true;
        if (coveredRows.contains(i) || coveredCols.contains(j)
                || isCoveredDiagonal(board, i, j)) {
            isValid = false;
        }

        return isValid;
    }

    private boolean isCoveredDiagonal(String[][] board, int row, int col, int n) {
        // Going in clockwise direction
        // NE
        int i = row - 1;
        int j = col + 1;
        while (i >= 0 && j < n) {
            if (board[i][j] == "Q") {
                return false;
            }

            i--;
            j++;
        }

        // SE
        i = row + 1;
        j = col + 1;
        while (i < n && j < n) {
            if (board[i][j] == "Q") {
                return false;
            }

            i++;
            j++;
        }

        // SW
        i = row + 1;
        j = col - 1;
        while (i < n && j >= 0) {
            if (board[i][j] == "Q") {
                return false;
            }

            i++;
            j--;
        }

        // NW
        i = row - 1;
        j = col - 1;
        while (i >= 0 && j >= 0) {
            if (board[i][j] == "Q") {
                return false;
            }

            i--;
            j--;
        }
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
