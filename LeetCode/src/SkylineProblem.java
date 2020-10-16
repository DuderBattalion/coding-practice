import java.util.*;

/**
 * Algorithm:
 * Reference:
 * https://www.youtube.com/watch?v=GSBLe8cKu0s&ab_channel=TusharRoy-CodingMadeSimple
 *
 * Steps:
 * - Scan from left to right. Add height value to priority queue.
 * - If start point (x, y) changes max in priority queue on add, then add to answer and update max in priority queue.
 * - If end point (x, y), changes max in priority queue on remove, then add to answer and update max in priority queue.
 *
 * Edge cases:
 * - Two buildings with same start point, process taller building first.
 * - Two buildings with same end point, process shorter building first.
 * - Two buildings, one starting and one ending, different height - process end first and start later.
 * - Two buildings, one starting and one ending, with same height - merge together.
 */
public class SkylineProblem {
    public static void main(String[] args) {

    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> output = new ArrayList<>();

        SkylineInfo skylineInfo = buildSkylineInfo(buildings);

        for (Integer index: skylineInfo.allIndices) {
            if (skylineInfo.startIndices.containsKey(index) && skylineInfo.endIndices.containsKey(index)) {
                // Multiple buildings - starting + ending.
                PriorityQueue<Integer> starts = skylineInfo.startIndices.get(index);
                PriorityQueue<Integer> ends = skylineInfo.endIndices.get(index);

                // Process ends first, then starts
                while (!ends.isEmpty()) {

                }
            } else if (skylineInfo.startIndices.containsKey(index)) {

            } else {
                // only end indices
            }
        }
    }

    /**
     * allIndices - Stores a list of all relevant indices to process, sorted form left to right
     * startIndices - Map of all start indices, max priority queue stores all buildings with same start index sorted
     * in descending order of heights, so we process the taller buildings first.
     * endIndices - Map of all end indices, min priority queue for all buildings with same end index, sorted in
     * ascending order of heights, so we process the shortest build first.
     */
    private static class SkylineInfo {
        private Map<Integer, PriorityQueue<Integer>> startIndices;
        private Map<Integer, PriorityQueue<Integer>> endIndices;
        private List<Integer> allIndices;

        public SkylineInfo() {
            this.startIndices = new HashMap<>();
            this.endIndices = new HashMap<>();
            this.allIndices = new ArrayList<>();
        }

        public void addSkylineIndex(int index, String type) {
            allIndices.add(new SkylineIndex(index, type));
        }

        private void addIndex(int index, int height, Map<Integer, PriorityQueue<Integer>> indices) {
            if (indices.containsKey(index)) {
                PriorityQueue<Integer> startQueue = indices.get(index);
                startQueue.add(height);
            } else {
                PriorityQueue<Integer> startQueue = new PriorityQueue<>(Collections.reverseOrder()); // max queue
                startQueue.add(height);

                indices.put(index, startQueue);
            }
        }

        public void addStartIndex(int index, int height) {
            addIndex(index, height, this.startIndices);
        }

        public void addEndIndex(int index, int height) {
            addIndex(index, height, this.endIndices);
        }

        private void removeIndex(int index, int height, Map<Integer, PriorityQueue<Integer>> indices) {
            if (!indices.containsKey(index)) {
                return;
            }

            PriorityQueue<Integer> indexQueue = indices.get(index);
            indexQueue.remove(height);

            if (indexQueue.isEmpty()) {
                indices.remove(index);
            }
        }

        public void removeStartIndex(int index, int height) {
            removeIndex(index, height, this.startIndices);
        }

        public void removeEndIndex(int index, int height) {
            removeIndex(index, height, this.endIndices);
        }
    }

    private static class SkylineIndex {
        public int index;
        List<BuildingIndex> buildings;


    }

    private static class BuildingIndex {
        public int index;
        public String type;

        public BuildingIndex(int index, String type) {
            this.index = index;
            this.type = type;
        }
    }
}
