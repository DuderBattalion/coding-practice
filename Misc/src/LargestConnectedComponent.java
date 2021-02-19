import java.io.*;
import java.util.*;

class LargestConnectedComponent {
    /**
     * @param matrix, a 2-d array (list of lists) of integers
     * @return the size of the largest contiguous block (horizontally/vertically connected) of numbers with the same value
     **/

    // 1. Define this a a graph problem.
    // 2. Largest contiguous block can be defined as connected components.
    // 3. BFS to find the connected components
    // 4. Return the largest such group that I find.
    public int largestContiguousBlock(int [][] matrix){
        if (matrix.length == 0) {
            return 0;
        }

        int maxComponentSize = Integer.MIN_VALUE;
        Set<Cell> visitedCells = new HashSet<>();

        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isCellVisited(i, j, visitedCells)) {
                    continue;
                }

                int componentSize = calculateComponentSize(i, j, matrix, visitedCells);
                if (componentSize > maxComponentSize) {
                    maxComponentSize = componentSize;
                }
            }
        }

        return maxComponentSize;
    }

    private class Cell {
        public int row;
        public int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
       public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null) {
                return false;
            }

            if (obj.getClass() != this.getClass()) {
                return false;
            }

            Cell other = (Cell) obj;
            return this.row == other.row && this.col == other.col;
       }

       @Override
       public int hashCode() {
            return Objects.hash(row, col);
       }
    }

    private boolean isCellVisited(int row, int col, Set<Cell> visited) {
        return visited.contains(new Cell(row, col));
    }

    private int calculateComponentSize(int row, int col, int[][] matrix, Set<Cell> visitedCells) {
        Queue<Cell> frontier = new LinkedList<>();
        Cell origin = new Cell(row, col);
        frontier.add(origin);

        int currentComponentVal = matrix[row][col];
        int currentComponentSize = 0;

        while (!frontier.isEmpty()) {
            Cell currentCell = frontier.poll();
            if (visitedCells.contains(currentCell)) {
                continue;
            }

            if (isValidCell(currentCell, matrix)
                    && matrix[currentCell.row][currentCell.col] == currentComponentVal) {
                visitedCells.add(currentCell);
                currentComponentSize++;
                addNeighbors(frontier, currentCell, visitedCells);
            }
        };

        return currentComponentSize;
    }

    private void addNeighbors(Queue<Cell> frontier, Cell currentCell, Set<Cell> visitedCells) {
        int row = currentCell.row;
        int col = currentCell.col;

        // Horizontal - left or right
        // Vertical - up or down

        // Horizontal left
        Cell horizontalLeft = new Cell(row, col - 1);

        // Horizontal right
        Cell horizontalRight = new Cell(row, col + 1);

        // Vertical up
        Cell verticalUp = new Cell(row - 1, col);

        // Vertical down
        Cell verticalDown = new Cell(row + 1, col);

        addIfNotVisited(horizontalLeft, visitedCells, frontier);
        addIfNotVisited(horizontalRight, visitedCells, frontier);
        addIfNotVisited(verticalUp, visitedCells, frontier);
        addIfNotVisited(verticalDown, visitedCells, frontier);
    }

    private void addIfNotVisited(Cell cell, Set<Cell> visitedCell, Queue<Cell> frontier) {
        if (!visitedCell.contains(cell)) {
            frontier.add(cell);
        }
    }

    private boolean isValidCell(Cell currentCell, int[][] matrix) {
        int maxRow = matrix.length;
        int maxCol = matrix[0].length;

        if (currentCell.row < 0 || currentCell.row >= maxRow
                || currentCell.col < 0 || currentCell.col >= maxCol) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        LargestConnectedComponent s = new LargestConnectedComponent();

        int expected1 = 2;
        int [][] matrix1 = new int[][]{
                {1,2,3},
                {4,1,6},
                {4,5,1}
        };

        int expected2 = 4;
        int [][] matrix2 = new int[][]{
                {1,1,1,2,4},
                {5,1,5,3,1},
                {3,4,2,1,1}
        };

        int expected3 = 11;
        int [][] matrix3 = new int[][]{
                {3,3,3,3,3,1},
                {3,4,4,4,3,4},
                {2,4,3,3,3,4},
                {2,4,4,4,4,4}
        };

        int expected4 = 24;
        int [][] matrix4 = new int[][]{
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0}
        };

        s.runTestCase(matrix1,expected1);
        s.runTestCase(matrix2,expected2);
        s.runTestCase(matrix3,expected3);
        s.runTestCase(matrix4,expected4);
    }

    public void runTestCase(int [][] matrix, int expected){
        int result = largestContiguousBlock(matrix);
        if(result != expected){
            System.out.println("\n--Failed--");
            printMatrix(matrix);
            System.out.print("Expected: ");
            System.out.println(expected);
            System.out.print("Actual: ");
            System.out.println(result);
        } else {
            System.out.println("--PASSED--");
        }

    }

    private static void printMatrix(int [][] matrix){
        for (int i = 0 ; i < matrix.length ; i++){
            System.out.print('[');
            for (int j = 0 ; j < matrix[0].length ; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println(']');
        }
        System.out.println();
    }
}