import java.util.Arrays;

public class ThreeSumClosest {
    public static void main(String[] args) {
//        int[] nums = { 0,2,1,-3 };
        int[] nums = { -1,2,1,-4 };
        System.out.println(threeSumClosest(nums, 1));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int closestSum = 0;
        int minDiff = Integer.MAX_VALUE;
        int i, j, k, nextI, nextJ, total, diff;

        i = 0;
        while (i != -1 && i < nums.length - 2) {
            j = getNextUniqueNumIndex(i, nums);
            nextI = j;

            while (j != -1 && j < nums.length - 1) {
                k = getNextUniqueNumIndex(j, nums);
                nextJ = k;

                while (k != -1) {
                    System.out.println(
                            String.format("a: %d, b: %d, c: %d",
                                    nums[i], nums[j], nums[k]));

                    total = nums[i] + nums[j] + nums[k];
                    diff = Math.abs(target - total);

                    if (diff < minDiff) {
                        minDiff = diff;
                        closestSum = total;
                    }

                    k = getNextUniqueNumIndex(k, nums);
                }

                j = nextJ;
            }

            i = nextI;
        }

        return closestSum;
    }

    private static int getNextUniqueNumIndex(int i, int[] nums) {
        int index = -1;

        int currNum = nums[i];
        i++;

        while (i < nums.length) {
            if (nums[i] == currNum) {
                i++;
            } else {
                index = i;
                break;
            }
        }

        return index;
    }
}
