import java.util.*;

public class CombinationSum {
    public static void main(String[] args) {
//        int[] candidates = { 2, 3, 6, 7 };
//        int target = 7;

        int[] candidates = { 2, 3, 5 };
        int target = 8;

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
        Set<String> processedResultHashes = new HashSet<>();

        return generateCombSum(candidates, target, 0, currList, 0, output);
    }

    public static List<List<Integer>> generateCombSum(int[] candidates, int target,
                                               int currSum, Deque<Integer> currList,
                                               int candidateStartIndex,
                                               List<List<Integer>> output) {
        if (currSum == target) {
//            List<Integer> results = new ArrayList<>(currList);
//            String resultHash = getResultHash(results);
//
//            if (!processedResultHashes.contains(resultHash)) {
//                output.add(results);
//                processedResultHashes.add(resultHash);
//            }

            output.add(new ArrayList<>(currList));
            return output;
        } else if (currSum > target) {
            return output;
        }

        for (int i = candidateStartIndex; i < candidates.length; i++) {
            // Try each candidate
            currList.push(candidates[i]);
            currSum += candidates[i];

            output = generateCombSum(candidates, target, currSum, currList, i, output);

            // Then backtrack
            currList.pop();
            currSum -= candidates[i];
        }

        return output;
    }

    private static String getResultHash(List<Integer> results) {
        Collections.sort(results);

        StringBuilder resultHashBuf = new StringBuilder();
        results.forEach(num -> {
            resultHashBuf.append(num);
            resultHashBuf.append(",");
        });

        return resultHashBuf.toString();
    }
}
