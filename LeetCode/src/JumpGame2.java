import java.util.LinkedList;
import java.util.Queue;

public class JumpGame2 {
    public static void main(String[] args) {
        int[] nums = { 2, 3, 1, 1, 4 };
//        int[] nums = { 0 };
//        int[] nums = { 1, 2, 1, 1, 1 };

        System.out.println(jump(nums));
    }

//    public static int jump(int[] nums) {
//        if (nums.length == 0) {
//            return 0;
//        }
//
//        Queue<Integer> frontier = new LinkedList<>();
//        frontier.offer(0);
//
//        boolean isLastIndex = false;
//        while (!frontier.isEmpty()) {
//            int index = frontier.poll();
//
//            // Check neighbors
//            for (int jump = 1; jump <= nums[index]; jump++) {
//                int nextIndex = index + jump;
//                if (nextIndex == (nums.length - 1)) {
//                    isLastIndex = true;
//                    break;
//                }
//
//
//            }
//        }
//    }

    /**
     * Searches neighbors if they have last index.
     * If not, then return the next level of neighbors.
     */
    public static int jump(int[] nums) {
        int jumps = 0;

        Queue<Integer> neighbors = calcNeighbors(nums, 0);
        while (!neighbors.isEmpty()) {
            jumps++;

            // Last index found
            if (neighbors.contains(nums.length - 1)) {
                break;
            }

            Queue<Integer> nextNeighbors = new LinkedList<>();
            for (int neighbor: neighbors) {
                nextNeighbors.addAll(calcNeighbors(nums, neighbor));
            }

            neighbors = nextNeighbors;
        }

        return jumps;
    }

    private static Queue<Integer> calcNeighbors(int[] nums, int index) {
        Queue<Integer> neighbors = new LinkedList<>();

        if (nums.length == 0 || index >= nums.length) {
            return neighbors;
        }

        int maxJumps = nums[index];
        for (int i = 1; i <= maxJumps; i++) {
            int nextIndex = index + i;
            neighbors.add(nextIndex);

            if (nextIndex == (nums.length - 1)) {
                // Last index found - stop processing
                break;
            }
        }

        return neighbors;
    }
}
