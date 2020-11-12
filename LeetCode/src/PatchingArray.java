public class PatchingArray {
    public static void main(String[] args) {
        //        int[] nums = { 1, 3 };
//        System.out.println(minPatches(nums, 6));

//        int[] nums = { 1, 5, 10 };
//        System.out.println(minPatches(nums, 20));

//        int[] nums = { 1, 2, 2 };
//        System.out.println(minPatches(nums, 5));
//
//        int[] nums = {  };
//        System.out.println(minPatches(nums, 7));

        int[] nums = { 1, 2, 31, 33 };
        System.out.println(minPatches(nums, 2147483647));
    }

    /**
     * Tricky number theory
     * See:
     * https://leetcode.com/problems/patching-array/discuss/78488/Solution-%2B-explanation
     */
    public static int minPatches(int[] nums, int n) {
        long range = 1;
        int i = 0, patchCount = 0;
        while (range <= n) {
            if (i < nums.length && nums[i] <= range) {
                range += nums[i];
                i++;
            } else {
                range += range;
                patchCount++;
            }
        }

        return patchCount;
    }
}
