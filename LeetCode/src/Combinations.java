import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public static void main(String[] args) {
        List<List<Integer>> output = combine(4, 2);

        output.forEach(row -> {
            row.forEach(num -> {
                System.out.print(num + ", ");
            });

            System.out.println();
        });
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> output = new ArrayList<>();
        generateCombinations(n, k, 1, new ArrayList<>(), output);

        return output;
    }

    private static void generateCombinations(int n, int k, int index,
                                             List<Integer> combo, List<List<Integer>> output) {
        if (k == 0) {
            output.add(new ArrayList<>(combo));
            return;
        }

        for (int i = index; i <= n; i++) {
            combo.add(i);
            generateCombinations(n, k - 1, i + 1, combo, output);

            // Backtrack
            combo.remove(combo.size() - 1);
        }
    }
}
