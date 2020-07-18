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

        // Spiral arm 1
        // First going right, Second going down
        for (int k = 0; k < (n-1); k++) {
            int first = matrix[i][j+k];
            int second = matrix[i+k][n-1];

            // Swap first and second
            int temp = first;
            first = second;
            second = temp;

            matrix[i][j+k] = first;
            matrix[i+k][n-1] = second;
        }

        // Spiral arm 2
        // First going down, Second going left
        for (int k = 0; k < (n-1); k++) {
            int first = matrix[i+k][n-1];
            int second = matrix[n-1][(n-1)-k];

            // Swap first and second
            int temp = first;
            first = second;
            second = temp;

            matrix[i+k][n-1] = first;
            matrix[n-1][(n-1)-k] = second;
        }

        // Spiral arm 3
        // First going left, second going up
        for (int k = 0; k < (n-1); k++) {
            int first = matrix[n-1][(n-1)-k];
            int second = matrix[(n-1)-k][j];

            // Swap first and second
            int temp = first;
            first = second;
            second = temp;

            matrix[n-1][(n-1)-k] = first;
            matrix[(n-1)-k][j] = second;
        }
    }
}
