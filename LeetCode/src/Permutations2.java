import java.util.*;

public class Permutations2 {
    public static void main(String[] args) {
//        int[] nums = { 1, 1, 2 };
        int[] nums = { 1, 1, 3, 3 };

        List<List<Integer>> output = permuteUnique(nums);

        for (List<Integer> row: output) {
            row.forEach(num -> System.out.print(num + ","));
            System.out.println();
        }
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        Set<String> cache = new HashSet<>();

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
            calcPermutations(nums[i], perms, cache);
        }

        output.addAll(perms);
        return output;
    }

    private static void calcPermutations(int num, Queue<List<Integer>> perms, Set<String> cache) {
        int permSize = perms.size();
        for (int i = 0; i < permSize; i++) {
            List<Integer> candidate = perms.remove();
//            perms.addAll(calcPermutation(num, candidate, cache));
            Queue<List<Integer>> newPerms = calcPermutation(num, candidate, cache);
            if (!newPerms.isEmpty()) {
                perms.addAll(newPerms);
            }
        }
    }

    /**
     * Insert num at every position of candidate.
     * Return a list with each such permutation.
     */
    private static Queue<List<Integer>> calcPermutation(int num, List<Integer> candidate, Set<String> cache) {
        Queue<List<Integer>> perms = new LinkedList<>();

        for (int i = 0; i < candidate.size(); i++) {
            List<Integer> perm = new ArrayList<>();
            for (int j = 0; j < candidate.size(); j++) {
                if (j == i) {
                    perm.add(num);
                }

                perm.add(candidate.get(j));
            }

            String cacheKey = generateCacheKey(perm);
            if (!cache.contains(cacheKey)) {
                cache.add(cacheKey);
                perms.add(perm);
            }
        }

        List<Integer> tailPerm = new ArrayList<>(candidate);
        tailPerm.add(num);

        String cacheKey = generateCacheKey(tailPerm);
        if (!cache.contains(cacheKey)) {
            cache.add(cacheKey);
            perms.add(tailPerm);
        }

        return perms;
    }

    private static String generateCacheKey(List<Integer> perm) {
        StringBuilder cacheKeyBuf = new StringBuilder();
        perm.forEach(token -> {
            cacheKeyBuf.append(token);
            cacheKeyBuf.append(",");
        });

        return cacheKeyBuf.toString();
    }
}
