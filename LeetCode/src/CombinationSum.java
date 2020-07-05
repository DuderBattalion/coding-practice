import java.util.*;

public class CombinationSum {
    public static void main(String[] args) {
        int[] candidates = { 2, 3, 6, 7 };
        int target = 7;

        List<List<Integer>> output = combinationSum(candidates, target);
        output.forEach(results -> {
            results.forEach(result -> {
                System.out.print(result + ",");
            });

            System.out.println();
        });
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Deque<Integer> currList = new LinkedList<>();
        List<List<Integer>> output = new ArrayList<>();

        return generateCombSum(candidates, target, 0, currList, output);
    }

    public static List<List<Integer>> generateCombSum(int[] candidates, int target,
                                               int currSum, Deque<Integer> currList,
                                               List<List<Integer>> output) {
        if (currSum == target) {
            output.add(new ArrayList<>(currList)); // Copy list result
        } else if (currSum > target) {
            return output;
        }

        for (int candidate : candidates) {
            // Try each candidate
            currList.push(candidate);
            currSum += candidate;

            output = generateCombSum(candidates, target, currSum, currList, output);

            // Then backtrack
            currList.pop();
            currSum -= candidate;
        }

        return output;
    }
}
