import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MaximumSubarray {
    public static void main(String[] args) {
//        int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
//        int[] nums = { -2, -3, -4, -5 };
        int[] nums = { 0 };
//        int[] nums = { -9, -2, 1, 8, 7, -6, 4, 9, -9, -5, 0, 5, -2, 5, 9, 7 };

        System.out.println(maxSubArray(nums));

    }

    public static int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int num: nums) {
            if (sum < 0) {
                sum = num;
            } else {
                sum += num;
            }

            if (sum > maxSum) {
                maxSum = sum;
            }
        }

        return maxSum;
    }
}
