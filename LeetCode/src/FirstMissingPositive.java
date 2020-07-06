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
        int[] nums = { 1, 2, 3, 10, 2147483647, 9 };

        System.out.println(firstMissingPositive(nums));
    }

    public static int firstMissingPositive(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = 0;

        for (int num: nums) {
            if (num < 0) {
                continue;
            }

            if (num < min) {
                min = num;
            }

            if (num > max) {
                max = num;
            }
        }

        if (min > 1) {
            return 1;
        }

        // min == 0 or 1 from here
        if (max == 1) {
            return 2;
        }

        BitSet bitSet = new BitSet(max);
        for (int num: nums) {
            if (num <= 0) {
                continue;
            }

            bitSet.set(num-1); // 0 index'd
        }

        return bitSet.nextClearBit(1) + 1; // adjust for zero index
    }
}
