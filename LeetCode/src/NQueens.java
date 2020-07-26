import java.util.*;

public class NQueens {
    public static void main(String[] args) {
        List<List<String>> output = solveNQueens(4);
        output.forEach(solution -> {
            solution.forEach(System.out::println);
            System.out.println("=========");
        });
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> output = new ArrayList<>();
        findSolutions(0, n, new ArrayList<>(), output);

        return output;
    }

    private static void findSolutions(int row, int n,
                                      List<Queen> solution, // Stack?
                                      List<List<String>> output) {
        if (row == n) {
            addSolution(solution, output, n);
            return;
        }

        // For every row, find solutions for every column
        for (int j = 0; j < n; j++) {
            if (isValidPosition(row, j, solution)) {
                // Position valid, add column and recurse to next row
                solution.add(new Queen(row, j));
                findSolutions(row + 1, n, solution, output);

                // Backtrack
                solution.remove(solution.size() - 1);
            }
        }
    }

    private static void addSolution(List<Queen> solution, List<List<String>> output, int n) {
        List<String> solutionStringList = new ArrayList<>();
        for (Queen queen: solution) {
            StringBuilder queenPosition = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (queen.col == j) {
                    queenPosition.append("Q");
                } else {
                    queenPosition.append(".");
                }
            }

            solutionStringList.add(queenPosition.toString());
        }

        output.add(solutionStringList);
    }

    private static boolean isValidPosition(int row, int col, List<Queen> solution) {
        boolean isValidPosition = true;
        for (Queen queen : solution) {
            // Check rows and columns
            if (queen.row == row || queen.col == col) {
                isValidPosition = false;
                break;
            }

            // Check diagonal. Position on queen diagonal if
            // the difference in rows and cols is the same
            int diffRow = Math.abs(queen.row - row);
            int diffCol = Math.abs(queen.col - col);
            if (diffRow == diffCol) {
                isValidPosition = false;
                break;
            }
        }

        return isValidPosition;
    }

    private static class Queen {
        public int row;
        public int col;

        public Queen(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
