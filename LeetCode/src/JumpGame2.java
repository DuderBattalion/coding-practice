import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class JumpGame2 {
    public static void main(String[] args) {
//        int[] nums = { 2, 3, 1, 1, 4 };
//        int[] nums = { 0 };
        int[] nums = { 1, 2, 1, 1, 1 };
//        int[] nums = { 1 };
//        int[] nums = { 8,2,4,4,4,9,5,2,5,8,8,0,8,6,9,1,1,6,3,5,1,2,6,6,0,4,8,6,0,3,2,8,7,6,5,1,7,0,3,4,8,3,5,9,0,4,0,1,0,5,9,2,0,7,0,2,1,0,8,2,5,1,2,3,9,7,4,7,0,0,1,8,5,6,7,5,1,9,9,3,5,0,7,5 };

        System.out.println(jump2(nums));
    }

    public static int jump2(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return 0;
        }

        int jumps = 0;

        Queue<Integer> neighborIndices = new LinkedList<>();
        Set<Integer> visitedNodes = new HashSet<>();

        // Seed with first index
        neighborIndices.add(0);

        boolean isLastIndexFound = false;
        while (!neighborIndices.isEmpty()) {
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

                for (int j = nums[neighborIndex]; j >= 1; j--) {
                    int nextIndex = neighborIndex + j;
                    if (nextIndex >= nums.length) {
                        break; // out of bounds
                    }

                    // Last index
                    if (nextIndex == (nums.length - 1)) {
                        isLastIndexFound = true;
                        break;
                    }

                    if (visitedNodes.contains(nextIndex)) {
                        continue;
                    }

                    neighborIndices.add(nextIndex);
                }

                if (isLastIndexFound) {
                    break;
                }
            }

            jumps++;
            if (isLastIndexFound) {
                break;
            }
        }

        return isLastIndexFound ? jumps : 0;
    }

    /**
     * Searches neighbors if they have last index.
     * If not, then return the next level of neighbors.
     */
    public static int jump(int[] nums) {
        int jumps = 0;

        Queue<Integer> neighbors = calcNeighbors(nums, 0);
        Set<Integer> visitedNode = new HashSet<>();

        while (!neighbors.isEmpty()) {
            jumps++;

            // Last index found
            if (neighbors.contains(nums.length - 1)) {
                break;
            }

            Queue<Integer> nextNeighbors = new LinkedList<>();
            for (int neighbor: neighbors) {
                if (!visitedNode.contains(neighbor)) {
                    visitedNode.add(neighbor);
                    nextNeighbors.addAll(calcNeighbors(nums, neighbor));
                }
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
            if (nextIndex < nums.length) {
                neighbors.add(nextIndex);
            }

            if (nextIndex == (nums.length - 1)) {
                // Last index found - stop processing
                break;
            }
        }

        return neighbors;
    }
}
