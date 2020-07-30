import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UniquePaths {
    public static void main(String[] args) {
//        System.out.println(uniquePaths(3, 2));
//        System.out.println(uniquePaths(7, 3));
//        System.out.println(uniquePaths(1, 1));

        System.out.println(uniquePaths(19, 13));

    }

    public static int uniquePaths(int m, int n) {
        if (m == 1 && n == 1) {
            return 1;
        }

        return 1 + calcNumPaths(0, 0, n - 1, m - 1, new HashMap<>()); // Zero index'd
    }

    private static int calcNumPaths(int i, int j, int row, int col, Map<String, Integer> cache) {
        // Base case - target found
        if (i == row && j == col) {
            return 1;
        }

        // Invalid index - return
        if (i < 0 || i >= row || j < 0 || j >= col) {
            return 0;
        }

        String key = i + "," + j;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int numPaths = 1 + calcNumPaths(i + 1, j, row, col, cache) // Down
                + calcNumPaths(i, j + 1, row, col, cache); // Right

        cache.put(key, numPaths);
        return numPaths;
    }
}
