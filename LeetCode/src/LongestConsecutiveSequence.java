import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
//        int[] nums = { 100, 4, 200, 1, 3, 2 };
        int[] nums = { 100, 2, 200, 1, 4, 3 };

        System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] nums) {
        Map<Integer, Integer> numCount = new HashMap<>();
        for (int num: nums) {
            numCount.put(num, 1);
        }

        int maxSequenceLength = Integer.MIN_VALUE;
        for (int num: nums) {
            int count = 0;
            if (numCount.containsKey(num)) {
                count += numCount.get(num);
                count += accumulateLowerNumCounts(num - 1, numCount);
            }

            numCount.put(num, count);

            if (count > maxSequenceLength) {
                maxSequenceLength = count;
            }
        }

        return maxSequenceLength;
    }

    private static int accumulateLowerNumCounts(int num, Map<Integer, Integer> numCount) {
        int count = 0;
        while (numCount.containsKey(num)) {
            count += numCount.get(num);
            numCount.remove(num);

            num--;
        }

        return count;
    }
}
