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
 * - Multiple buildings with same start point, process taller building first.
 * - Multiple buildings with same end point, process shorter building first.
 * - Multiple buildings, one starting and one ending, different height - process end first and start later.
 * - Multiple buildings, one starting and one ending, with same height - merge together.
 */
public class SkylineProblem {
    public static void main(String[] args) {
        int[][] buildings = { {2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8} };

        List<List<Integer>> output = getSkyline(buildings);
        for (List<Integer> result: output) {
            for (int token: result) {
                System.out.print(token + " ");
            }

            System.out.println();
        }
    }

    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> output = new ArrayList<>();
        PriorityQueue<Integer> heightQueue = new PriorityQueue<>(Collections.reverseOrder());
        heightQueue.add(0);

        List<Building> sortedBuildings = buildSkyline(buildings);
        int prevMaxHeight = 0;
        for (Building building: sortedBuildings) {
            if (building.type.equals("start")) {
                heightQueue.add(building.height);
            } else { // building.type == end
                heightQueue.remove(building.height);
            }

            int maxHeight = heightQueue.isEmpty() ? 0 : heightQueue.peek();
            if (prevMaxHeight != maxHeight) {
                List<Integer> outlinePoint = createOutlinePoint(building);
                output.add(outlinePoint);

                prevMaxHeight = maxHeight;
            }
        }

        return output;
    }

    private static List<Building> buildSkyline(int[][] buildings) {
        List<Building> sortedBuildings = new ArrayList<>();
        for (int[] buildingData: buildings) {
            int start = buildingData[0];
            int end = buildingData[1];
            int height = buildingData[2];

            Building buildingStart = new Building(start, height, "start");
            Building buildingEnd = new Building(end, height, "end");

            sortedBuildings.add(buildingStart);
            sortedBuildings.add(buildingEnd);
        }

        Collections.sort(sortedBuildings);

        return sortedBuildings;
    }

    private static List<Integer> createOutlinePoint(Building building) {
        List<Integer> outlinePoint = new ArrayList<>();
        outlinePoint.add(building.index);
        outlinePoint.add(building.height);

        return outlinePoint;
    }

    private static class Building implements Comparable<Building>  {
        public int index;
        public int height;
        public String type;

        public Building(int index, int height, String type) {
            this.index = index;
            this.height = height;
            this.type = type;
        }

        /**
         * If indices are not the same, then sort by earlier index.
         * If indices are same, then:
         * - If both indices are starts, then sort taller before shorter
         * - If both indices are ends, then sort shorter before taller
         * - If one start and one end, then sort start before end
         */
        @Override
        public int compareTo(Building building) {
            if (this.index != building.index) {
                return this.index - building.index;
            }

            // If same, then:
            // If type is 'start', sort taller building before shorter building
            if (this.type.equals("start") && building.type.equals("start")) {
                return -(this.height - building.height); // Taller sorted first
            } else if (this.type.equals("end") && building.type.equals("end")) {
                return this.height - building.height; // Shorter sorted first
            } else {
                // One start, one end
                return (this.type.equals("start")) ? -1 : 1;
            }
        }
    }
}
