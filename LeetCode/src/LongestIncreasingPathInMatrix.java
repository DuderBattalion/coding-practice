import java.util.*;

public class LongestIncreasingPathInMatrix {
    public static void main(String[] args) {
//        int[][] nums = {
//                { 9, 9, 4 },
//                { 6, 6, 8 },
//                { 2, 1, 1 }
//        };

//        int[][] nums = {
//                { 3, 4, 5 },
//                { 3, 2, 6 },
//                { 2, 2, 1 }
//        };

//        int[][] nums = { };
        int[][] nums = { { } };

        System.out.println(longestIncreasingPath(nums));
    }

    public static int longestIncreasingPath(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return 0;
        }

        int cols = matrix[0].length;
        if (cols == 0) {
            return 0;
        }

        int maxCount = Integer.MIN_VALUE;
        Map<String, Integer> cache = new HashMap<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int count = calculateMaxIncreasingPath(matrix, i, j, rows, cols,
                        0, Integer.MIN_VALUE, cache);
                if (count > maxCount) {
                    maxCount = count;
                }

                String key = getSignature(i, j);
                cache.put(key, count);
            }
        }

        return maxCount;
    }

    private static String getSignature(int i, int j) {
        return i + "," + j;
    }

    private static int calculateMaxIncreasingPath(int[][] matrix, int i, int j,
                                                  int rows, int cols, int count,
                                                  int prevNum, Map<String, Integer> cache) {
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[i][j] <= prevNum) {
            return count;
        }

        String key = getSignature(i , j);
        if (cache.containsKey(key)) {
            return count + cache.get(key);
        }


        Integer[] counts = new Integer[4]; // left, right, up, down

        counts[0] = calculateMaxIncreasingPath(matrix, i, j-1, rows, cols,
                count + 1, matrix[i][j], cache);
        counts[1] = calculateMaxIncreasingPath(matrix, i, j+1, rows, cols,
                count + 1, matrix[i][j], cache);
        counts[2] = calculateMaxIncreasingPath(matrix, i-1, j, rows, cols,
                count + 1, matrix[i][j], cache);
        counts[3] = calculateMaxIncreasingPath(matrix, i+1, j, rows, cols,
                count + 1, matrix[i][j], cache);

        Arrays.sort(counts, Collections.reverseOrder());

        if (count == 0) {
            cache.put(key, counts[0]);
        }

        return counts[0];
    }
}
