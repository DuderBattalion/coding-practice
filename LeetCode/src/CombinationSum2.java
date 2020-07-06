import java.util.*;

public class CombinationSum2 {
    public static void main(String[] args) {
        int[] candidates = { 10, 1, 2, 7, 6, 1, 5 };
        int target = 8;

        List<List<Integer>> output = combinationSum2(candidates, target);
        output.forEach(result -> {
            result.forEach(num -> {
                System.out.print(num + ",");
            });

            System.out.println();
        });
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> output = new ArrayList<>();
        return calcCombSum(candidates, target, output, 0,
                new ArrayList<Integer>(), 0);
    }

    public static List<List<Integer>> calcCombSum(int[] candidates, int target,
                                           List<List<Integer>> output,
                                           int currCandidateIndex,
                                           List<Integer> currList, int currSum) {
        if (currSum == target) {
            output.add(new ArrayList<>(currList));
            return output;
        } else if (currSum > target) {
            return output;
        }

        if (currCandidateIndex >= candidates.length) {
            return output;
        }

        for (int i = currCandidateIndex; i < candidates.length; i++) {
            int candidate = candidates[i];
            currList.add(candidate);
            currSum += candidate;

            calcCombSum(candidates, target, output,
                    i + 1, currList, currSum);

            // Backtrack
            currList.remove(currList.size() - 1);
            currSum -= candidate;
        }

        return output;
    }
}
