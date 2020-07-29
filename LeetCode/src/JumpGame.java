import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class JumpGame {
    public static void main(String[] args) {
//        int[] nums = { 2, 3, 1, 1, 4 };
//        int[] nums = { 3, 2, 1, 0, 4 };
//        int[] nums = { };
//        int[] nums = { 1 };
        int[] nums = { 0 };

        System.out.println(canJump(nums));
    }

    public static boolean canJump(int[] nums) {
        if (nums.length == 0) {
            return true;
        }

        return canJumpDfs(nums, 0, new HashSet<Integer>());
    }

    private static boolean canJumpDfs(int[] nums, int i, Set<Integer> indexCache) {
        if (indexCache.contains(i)) {
            return false;
        }

        if (i < 0 || i >= nums.length) {
            indexCache.add(i);
            return false;
        }

        if (i == (nums.length - 1)) {
            return true;
        }

        if (nums[i] == 0) {
            indexCache.add(i);
            return false;
        }

        boolean isLastIndex = false;
        for (int j = 1; j <= nums[i]; j++) {
            if (canJumpDfs(nums, i + j, indexCache)) {
                isLastIndex = true;
                break;
            }
        }

        indexCache.add(i);
        return isLastIndex;
    }
}
