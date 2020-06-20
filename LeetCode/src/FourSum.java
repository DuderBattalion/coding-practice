import java.util.*;

public class FourSum {
    public static void main(String[] args) {
        int[] nums = { 1, 0, -1, 0, -2, 2 };
        var solutions = fourSum(nums, 0);

        for (List<Integer> solution: solutions) {
            solution.forEach(num -> System.out.print(num + ","));
            System.out.println();
        }
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        int start = 0;
        int end = nums.length - 1;

        return getAllSums(nums, target, start, end, 0,
                new ArrayList<Integer>(), new ArrayList<List<Integer>>(),
                new HashSet<String>());
    }

    private static List<List<Integer>> getAllSums(int[] nums, int target,
                                                  int start, int end,
                                                  int currSum,
                                                  List<Integer> currSumList,
                                                  List<List<Integer>> uniqueSums,
                                                  Set<String> cache) {
        if (start >= end) {
            return uniqueSums;
        }

        if (currSumList.size() == 4) {
            Collections.sort(currSumList);
            SumCache cacheVal = getHash(currSumList);

            if (!cache.contains(cacheVal.hash) && target == cacheVal.sum) {
                uniqueSums.add(currSumList);
                cache.add(cacheVal.hash);
            }

            return uniqueSums;
        }

        if (currSum == target) {
            startRecurse(nums, target, start+1, end, currSum,
                    currSumList, uniqueSums, cache);
            endRecurse(nums, target, start, end-1, currSum,
                    currSumList, uniqueSums, cache);

            return uniqueSums;
        } else if (currSum < target) {
            return endRecurse(nums, target, start, end-1, currSum,
                    currSumList, uniqueSums, cache);
        } else {
            return startRecurse(nums, target, start+1, end, currSum,
                    currSumList, uniqueSums, cache);
        }
    }

    private static SumCache getHash(List<Integer> nums) {
        String separator = ",";
        int sum = 0;

        StringBuilder hash = new StringBuilder();
        for (int num: nums) {
            hash.append(num);
            hash.append(separator);

            sum += num;
        };

        return new SumCache(sum, hash.toString());
    }

    private static class SumCache {
        public int sum;
        public String hash;

        public SumCache(int sum, String hash) {
            this.sum = sum;
            this.hash = hash;
        }
    }

    private static List<List<Integer>> startRecurse(int[] nums, int target,
                                                    int start, int end,
                                                    int currSum,
                                                    List<Integer> currSumList,
                                                    List<List<Integer>> uniqueSums,
                                                    Set<String> cache) {
        currSum += nums[start];
        currSumList.add(nums[start]);

        // Skip similar numbers
        while (start < end && nums[start] == nums[start+1]) {
            start++;
        }

        return getAllSums(nums, target, start+1, end, currSum,
                currSumList, uniqueSums, cache);
    }

    private static List<List<Integer>> endRecurse(int[] nums, int target,
                                                    int start, int end,
                                                    int currSum,
                                                    List<Integer> currSumList,
                                                    List<List<Integer>> uniqueSums,
                                                    Set<String> cache) {
        currSum += nums[end];
        currSumList.add(nums[end]);

        // Skip similar numbers
        while (end > start && nums[end] == nums[end-1]) {
            end--;
        }

        return getAllSums(nums, target, start, end-1, currSum,
                currSumList, uniqueSums, cache);
    }
}
