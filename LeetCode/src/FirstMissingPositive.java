import java.util.Arrays;
import java.util.BitSet;

public class FirstMissingPositive {
    public static void main(String[] args) {
//        int[] nums = { 7, 8, 9, 11, 12 };
//        int[] nums = { 3, 4, -1, 1 };
//        int[] nums = { 1, 2, 0 };
//        int[] nums = { 1, 1 };
//        int[] nums = { 0, 0 };
//        int[] nums = { -1, -2, -3 };
//        int[] nums = { 1, 1000 };
//        int[] nums = { 1, 2, 3, 10, 2147483647, 9 };
//        int[] nums = { 0 };
        int[] nums = { 1 };

        System.out.println(firstMissingPositive(nums));
    }

    public static int firstMissingPositive(int[] nums) {
        // Observation: Missing positive has to be between 1 .. N (N being length of nums)
        // Skip all negative numbers
        // All numbers more than N can also be made negative so they are skipped
        // Process remaining numbers, for each num[i] - make that index negative
        // The first positive index left is missing number

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums.length || nums[i] < 1) {
                nums[i] = -1;
                continue;
            }

            // num between 1 .. N
            nums[nums[i] - 1] = -2; // Zero index's - so (index - 1)
        }

        int missingNum = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -2) {
                if (i + i < nums.length && nums[i+1] != -2) {
                    missingNum = (i + 1) + 1; // Adjust for zero index
                    break;
                } else if (i + 1 == nums.length){
                    missingNum = i + 1;
                    break;
                }
            }
        }

        return missingNum;
    }

//    public static int firstMissingPositive(int[] nums) {
//        int min = Integer.MAX_VALUE;
//        int max = 0;
//
//        for (int num: nums) {
//            if (num <= 0) {
//                continue;
//            }
//
//            if (num < min) {
//                min = num;
//            }
//
//            if (num > max) {
//                max = num;
//            }
//        }
//
//        if (min > 1) {
//            return 1;
//        }
//
//        // min == 0 or 1 from here
//        if (max == 1) {
//            return 2;
//        }
//
//        BitSet bitSet = new BitSet(max);
//        for (int num: nums) {
//            if (num <= 0) {
//                continue;
//            }
//
//            bitSet.set(num-1); // 0 index'd
//        }
//
//        return bitSet.nextClearBit(1) + 1; // adjust for zero index
//    }
}
