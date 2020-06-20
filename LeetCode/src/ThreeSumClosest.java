import java.util.Arrays;

public class ThreeSumClosest {
    public static void main(String[] args) {
        int[] nums = { 0,2,1,-3 };
//        int[] nums = { -1,2,1,-4 };
//        int[] nums = { 1,1,-1 };
        System.out.println(threeSumClosest(nums, 1));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int closestSum = 0;
        int minDiff = Integer.MAX_VALUE;

        int total, diff;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    total = nums[i] + nums[j] + nums[k];
                    diff = Math.abs(target - total);

                    if (diff < minDiff) {
                        minDiff = diff;
                        closestSum = total;
                    }

                    // Skip similar numbers
                    while (k + 1 < nums.length && nums[k + 1] == nums[k]) {
                        k++;
                    }
                }

                while (j + 1 < nums.length - 1 && nums[j + 1] == nums[j]) {
                    j++;
                }
            }
        }

        return closestSum;
    }
}
