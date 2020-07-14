import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class JumpGame2 {
    public static void main(String[] args) {
//        int[] nums = { 2, 3, 1, 1, 4 };
//        int[] nums = { 0 };
//        int[] nums = { 1, 2, 1, 1, 1 };
//        int[] nums = { 1 };
        int[] nums = { 8,2,4,4,4,9,5,2,5,8,8,0,8,6,9,1,1,6,3,5,1,2,6,6,0,4,8,6,0,3,2,8,7,6,5,1,7,0,3,4,8,3,5,9,0,4,0,1,0,5,9,2,0,7,0,2,1,0,8,2,5,1,2,3,9,7,4,7,0,0,1,8,5,6,7,5,1,9,9,3,5,0,7,5 };

        System.out.println(jump(nums));
    }

    public static int jump(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return 0;
        }

        int jumps = 0;

        Queue<Integer> neighborIndices = new LinkedList<>();
        Set<Integer> visitedNodes = new HashSet<>();

        // Seed with first index
        neighborIndices.add(0);

        // Modified BFS
        // Process each level of neighboring indices that are in jump range.
        // If last index found in BFS level, then break and return jump count.
        // If not, then increment jump by 1 AFTER the entire level has been processed and continue searching.
        boolean isLastIndexFound = false;
        while (!neighborIndices.isEmpty()) {
            isLastIndexFound = processBFSLevel(nums, neighborIndices, visitedNodes);
            jumps++;

            if (isLastIndexFound) {
                break;
            }
        }

        return isLastIndexFound ? jumps : 0;
    }

    /**
     * Takes a level in the BFS tree for nums as a queue and checks if the last index exists on this level.
     * If so, returns true. If not, then fills the queue with the next level candidates and returns false.
     */
    private static boolean processBFSLevel(int[] nums, Queue<Integer> neighborIndices,
                                           Set<Integer> visitedNodes) {
        boolean isLastIndexFound = false;
        int levelSize = neighborIndices.size();

        for (int i = 0; i < levelSize; i++) {
            // Should never happen, but IDE complains if omitted
            if (neighborIndices.isEmpty()) {
                break;
            }

            int neighborIndex = neighborIndices.poll();

            // If last index lies in jump range, break
            if (nums[neighborIndex] >= (nums.length - neighborIndex)) {
                isLastIndexFound = true;
                break;
            }

            if (visitedNodes.contains(neighborIndex)) {
                continue;
            }

            visitedNodes.add(neighborIndex);
            isLastIndexFound = addNextNeighbors(nums, neighborIndices, visitedNodes, neighborIndex);

            if (isLastIndexFound) {
                break;
            }
        }

        return isLastIndexFound;
    }

    /**
     * Given an index in nums, calculates neihgbors based on jump range. If last index is detected,
     * stops processing immediately and returns to save on CPU cycles.
     */
    private static boolean addNextNeighbors(int[] nums, Queue<Integer> neighborIndices,
                                            Set<Integer> visitedNodes, int neighborIndex) {
        boolean isLastIndexFound = false;
        for (int j = nums[neighborIndex]; j >= 1; j--) {
            int nextIndex = neighborIndex + j;
            if (nextIndex >= nums.length) {
                break; // out of bounds
            }

            // If last index found, no more processing required
            if (nextIndex == (nums.length - 1)) {
                isLastIndexFound = true;
                break;
            }

            if (visitedNodes.contains(nextIndex)) {
                continue;
            }

            neighborIndices.add(nextIndex);
        }

        return isLastIndexFound;
    }
}
