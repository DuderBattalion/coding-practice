import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] nums = { 100, 4, 200, 1, 3, 2 };
        System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] nums) {
        Map<Integer, Integer> sequenceCount = new HashMap<>();
        for (int num: nums) {
            sequenceCount.put(num, 1);
        }

        int maxSequenceLength = Integer.MIN_VALUE;
        for (int num: nums) {
            int count = 0;
            while (sequenceCount.containsKey(num)) {
                count += sequenceCount.get(num);
                sequenceCount.remove(num);

                num--;
            }

            if (count > maxSequenceLength) {
                maxSequenceLength = count;
            }
        }

        return maxSequenceLength;
    }
}
