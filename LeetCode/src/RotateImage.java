import java.util.LinkedList;
import java.util.Queue;

public class RotateImage {
    public static void main(String[] args) {
        int[][] matrix = {
            {1,2,3},
            {4,5,6},
            {7,8,9}
        };

        rotate(matrix);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        int i = 0;
        int j = 0;

        for (int k = 0; k < (n/2); k++) {
            doSwapSpiral(matrix, i, j, n);

            n--;
            i++;
            j++;
        }
    }

    private static void doSwapSpiral(int[][] matrix, int i, int j, int n) {
        if (n <= 0) {
            return;
        }

        for (int k = 0; k < (n - 1); k++) {
            // Consider swapQ as a circular Queue that stores
            // indices to be swapped around the 2D array
            // Indices lie in the same layer, and we go swapping
            // layers inward - like an onion
            Queue<Integer> swapQ = new LinkedList<>();
            swapQ.add(matrix[(n-1)-k][j]); // Spiral arm LEFT
            swapQ.add(matrix[i][j+k]); // arm TOP
            swapQ.add(matrix[i+k][n-1]); // arm RIGHT
            swapQ.add(matrix[n-1][(n-1)-k]); // arm BOTTOM

            // Now that's everything been swapped right by
            // one swap position, reassign queue values to indices
            matrix[i][j+k] = swapQ.remove();
            matrix[i+k][n-1] = swapQ.remove();
            matrix[n-1][(n-1)-k] = swapQ.remove();
            matrix[(n-1)-k][j] = swapQ.remove();
        }
    }
}
