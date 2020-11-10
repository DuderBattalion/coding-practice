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
//        int[][] nums = { { } };

        int[][] nums = {{0,1,2,3,4,5,6,7,8,9},{19,18,17,16,15,14,13,12,11,10},{20,21,22,23,24,25,26,27,28,29},{39,38,37,36,35,34,33,32,31,30},{40,41,42,43,44,45,46,47,48,49},{59,58,57,56,55,54,53,52,51,50},{60,61,62,63,64,65,66,67,68,69},{79,78,77,76,75,74,73,72,71,70},{80,81,82,83,84,85,86,87,88,89},{99,98,97,96,95,94,93,92,91,90},{100,101,102,103,104,105,106,107,108,109},{119,118,117,116,115,114,113,112,111,110},{120,121,122,123,124,125,126,127,128,129},{139,138,137,136,135,134,133,132,131,130},{0,0,0,0,0,0,0,0,0,0}};

        System.out.println(longestIncreasingPath(nums));
    }

    /**
     * Algorithm
     * Normal DFS search with memoized cache.
     * Each cell is count till now + cache (i,j) cell, if one exists.
     */
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
                        Integer.MIN_VALUE, cache);
                if (count > maxCount) {
                    maxCount = count;
                }
            }
        }

        return maxCount;
    }

    private static String getSignature(int i, int j) {
        return i + "," + j;
    }

    private static int calculateMaxIncreasingPath(int[][] matrix, int i, int j,
                                                  int rows, int cols,
                                                  int prevNum, Map<String, Integer> cache) {
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[i][j] <= prevNum) {
            return 0;
        }

        String key = getSignature(i , j);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int count = getMaxCount(matrix, i, j, rows, cols, cache);

        cache.put(key, count);
        return count;
    }

    private static int getMaxCount(int[][] matrix, int i, int j, int rows, int cols, Map<String, Integer> cache) {
        Integer[] counts = new Integer[4]; // left, right, up, down

        counts[0] = calculateMaxIncreasingPath(matrix, i, j -1, rows, cols,
                matrix[i][j], cache);
        counts[1] = calculateMaxIncreasingPath(matrix, i, j +1, rows, cols,
                matrix[i][j], cache);
        counts[2] = calculateMaxIncreasingPath(matrix, i -1, j, rows, cols,
                matrix[i][j], cache);
        counts[3] = calculateMaxIncreasingPath(matrix, i +1, j, rows, cols,
                matrix[i][j], cache);

        Arrays.sort(counts, Collections.reverseOrder());

        return 1 + counts[0];
    }
}
