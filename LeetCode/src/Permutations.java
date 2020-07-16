import java.util.*;

public class Permutations {
    public static void main(String[] args) {
//        int[] nums = { 1, 2, 3 };
        int[] nums = { 1, 2, 3, 4 };

        List<List<Integer>> output = permute(nums);

        for (List<Integer> row: output) {
            row.forEach(num -> System.out.print(num + ","));
            System.out.println();
        }
    }

    /**
     *  Permutations are based on recurrence relation:
     *  p[i] = num[i] permuted across p[i-1]
     *  so if num[] = 1, 2, 3, then p[0] = { 1 },
     *  and p[1] = 2 permuted with p[0]
     *  i.e p[1] = 2 permuted with { 1 }
     *  i.e p[1] = { 2, 1 }, { 1, 2 }
     *  Permutations are just num[i]'s being inserted at each position of p[i-1]
     * @param nums
     * @return
     */

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();

        if (nums.length == 0) {
            return output;
        }

        List<Integer> result = new ArrayList<>();
        result.add(nums[0]);

        if (nums.length == 1) {
            output.add(result);
            return output;
        }

        Queue<List<Integer>> perms = new LinkedList<>();
        perms.add(result);

        for (int i = 1; i < nums.length; i++) {
            calcPermutations(nums[i], perms);
        }

        output.addAll(perms);
        return output;
    }

    private static void calcPermutations(int num, Queue<List<Integer>> perms) {
        int permSize = perms.size();
        for (int i = 0; i < permSize; i++) {
                List<Integer> candidate = perms.remove();
                perms.addAll(calcPermutation(num, candidate));
        }
    }

    /**
     * Insert num at every position of candidate.
     * Return a list with each such permutation.
     */
    private static Queue<List<Integer>> calcPermutation(int num, List<Integer> candidate) {
        Queue<List<Integer>> perms = new LinkedList<>();

        for (int i = 0; i < candidate.size(); i++) {
            List<Integer> perm = new ArrayList<>();
            for (int j = 0; j < candidate.size(); j++) {
                if (j == i) {
                    perm.add(num);
                }

                perm.add(candidate.get(j));
            }

            perms.add(perm);
        }

        List<Integer> tailPerm = new ArrayList<>(candidate);
        tailPerm.add(num);

        perms.add(tailPerm);

        return perms;
    }
}
