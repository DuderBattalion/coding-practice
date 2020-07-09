import java.util.Arrays;
import java.util.BitSet;

public class FirstMissingPositive {
    public static void main(String[] args) {
        int[] nums = { 7, 8, 9, 11, 12 };
//        int[] nums = { 3, 4, -1, 1 };
//        int[] nums = { 1, 2, 0 };
//        int[] nums = { 1, 1 };
//        int[] nums = { 0, 0 };
//        int[] nums = { -1, -2, -3 };
//        int[] nums = { 1, 1000 };
//        int[] nums = { 1, 2, 3, 10, 2147483647, 9 };
//        int[] nums = { 0 };
//        int[] nums = { 1 };
//        int[] nums = { 2, 2, 2, 2, 2 };
//        int[] nums = {};

        System.out.println(firstMissingPositive(nums));
    }

    public static int firstMissingPositive(int[] nums) {
        // Observation: Missing positive has to be between 1 .. N (N being length of nums)
        // Skip all negative numbers
        // All numbers more than N can also be made negative so they are skipped
        // Process remaining numbers, for each num[i] - make that index negative
        // The first positive index left is missing number

        if (nums.length == 0) {
            return 1;
        }

        final int NUM_PROCESSED = 0;
        final int NUM_IGNORED = -1;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // Out of range values are ignored
            if (nums[i] > nums.length || nums[i] < 1) {
                nums[i] = NUM_IGNORED;
            }
        }

        // Numbers guaranteed to be between 0 .. N, with 0 signifying processed index
        // At the end of this loop, all numbers are either:
        // a) Ignored (-1)
        // b) nums[i] index has been tagged with (0) to mark presence
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 1) {
                continue; // Skip out of range numbers
            }

            int targetIndex = nums[i] - 1; // Zero index'd (hence index - 1)

            // num between 1 .. N
            nums[targetIndex] = NUM_PROCESSED;

            if (i != targetIndex) {
                nums[i] = NUM_IGNORED; // Set processed numbers to be ignored in next pass
            }
        }

        int missingNum = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != NUM_PROCESSED) {
                missingNum = i + 1; // Zero index adjust
                break;
            }
        }

        if (missingNum == -1) {
            // All indexes were valid
            missingNum = nums.length + 1;
        }

        return missingNum;
    }
}
