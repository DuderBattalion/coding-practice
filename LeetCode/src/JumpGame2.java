import java.util.LinkedList;
import java.util.Queue;

public class JumpGame2 {
    public static void main(String[] args) {
//        int[] nums = { 2, 3, 1, 1, 4 };
        int[] nums = { 2 };

        System.out.println(jump(nums));
    }

    public static int jump(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Queue<Integer> frontier = new LinkedList<>();
        frontier.offer(0);

        // Modified BFS
        int jumpCount = 0;
        boolean isLastIndex = false;
        while (!frontier.isEmpty()) {
            int index = frontier.poll();
            for (int jump = 1; jump <= nums[index]; jump++) {
                int nextIndex = index + jump;
                if (nextIndex == (nums.length - 1)) {
                    isLastIndex = true;
                    jumpCount++;
                    break;
                } else if (nextIndex >= nums.length) {
                    continue; // Out of bounds
                }

                frontier.offer(nextIndex);
            }

            if (isLastIndex) {
                break;
            }

            jumpCount++;
        }

        return jumpCount;
    }
}
