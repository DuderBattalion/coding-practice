import java.util.Arrays;

public class SortColors {
    public static void main(String[] args) {
//        int[] nums = { 2,0,2,1,1,0 };
        int[] nums = { 1, 2, 0 };

        sortColors(nums);

        Arrays.stream(nums).forEach(System.out::println);
    }

    /**
     * Algorithm: Dutch partitioning problem
     * Reference:
     * See discussion on LeetCode - https://leetcode.com/problems/sort-colors/discuss/26481/Python-O(n)-1-pass-in-place-solution-with-explanation
     * @param nums
     */
    public static void sortColors(int[] nums) {
        if (nums.length <= 1) {
            return;
        }

        int i = 0, j = 0;
        int k = nums.length - 1;
        while (j <= k) {
            if (nums[j] == 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                i++;
                j++;
            } else if (nums[j] == 2) {
                int temp = nums[j];
                nums[j] = nums[k];
                nums[k] = temp;

                k--;
            } else {
                j++;
            }
        }
    }
}
