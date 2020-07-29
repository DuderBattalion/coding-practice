import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class JumpGame {
    public static final int GOOD_INDEX_TOKEN = -1;
    public static final int BAD_INDEX_TOKEN = -2;

    public static void main(String[] args) {
//        int[] nums = { 2, 3, 1, 1, 4 };
//        int[] nums = { 3, 2, 1, 0, 4 };
//        int[] nums = { };
//        int[] nums = { 1 };
        int[] nums = { 0 };

        System.out.println(canJump(nums));
    }

    /**
     * Algorithm
     * Good Index: An index from which there is a path to the last index
     * Bad Index: A dead end index which cannot access the last index
     *
     * Start from the last index and mark it as a good index.
     * Now start moving backwards toward index[0] and mark each index with a good or bad token, depending on
     * if there is a path to a good index within the range of the current index.
     *
     * Once the pass is complete, the first index will have a good or bad index value. Return this value.
     */
    public static boolean canJump(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }

        int i = nums.length - 1;
        nums[i] = GOOD_INDEX_TOKEN;
        i--;

        boolean canJumpStatus = true;
        while (i >= 0) {
            canJumpStatus = isNeighborGoodIndex(i, nums);
            if (canJumpStatus) {
                nums[i] = GOOD_INDEX_TOKEN;
            } else {
                nums[i] = BAD_INDEX_TOKEN;
            }

            i--;
        }

        return nums[0] == GOOD_INDEX_TOKEN;
    }

    private static boolean isNeighborGoodIndex(int i, int[] nums) {
        boolean isGoodIndex = false;
        for (int j = nums[i]; j >= 0; j--) {
            if ((i + j) >= (nums.length - 1) || nums[i + j] == GOOD_INDEX_TOKEN) {
                isGoodIndex = true;
                break;
            }
        }

        return isGoodIndex;
    }
}
