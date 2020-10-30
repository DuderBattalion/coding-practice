import java.util.*;

public class CountSmallerNumbers {
    public static void main(String[] args) {
        int[] nums = { 5, 2, 6, 1 };
//        int[] nums = {  };
//        int[] nums = { 5 };

//        int[] nums = { 26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41 };

//        int[] nums = { 84,66,65,36,100,41 };

        List<Integer> output = countSmaller(nums);
        for (int count: output) {
            System.out.print(count + ", ");
        }
    }

//    public static List<Integer> countSmaller(int[] nums) {
//        TreeMap<Integer, Integer> numSet = new TreeMap<>();
//        for (int num: nums) {
//            if (numSet.containsKey(num)) {
//                int count = numSet.get(num);
//                numSet.put(num, count + 1);
//            } else {
//                numSet.put(num, 1);
//            }
//        }
//
//        List<Integer> output = new ArrayList<>();
//        for (int num: nums) {
//            SortedMap<Integer, Integer> sortedSet = numSet.headMap(num);
//
//            int numSmalls = 0;
//            for (Map.Entry<Integer, Integer> entry: sortedSet.entrySet()) {
//                int val = entry.getValue();
//                numSmalls += val;
//            }
//
//            output.add(numSmalls);
//
//            if (numSet.containsKey(num)) {
//                int numCount = numSet.get(num);
//                if (numCount > 0) {
//                    numSet.put(num, numCount - 1);
//                } else {
//                    numSet.remove(num);
//                }
//            }
//        }
//
//        return output;
//    }

    public static List<Integer> countSmaller(int[] nums) {
        TreeSet<MapNumber> numSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            numSet.add(new MapNumber(nums[i], i));
        }

        List<Integer> output = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            MapNumber mapNumber = new MapNumber(nums[i], i);

            int sameNumCount = 0;
            MapNumber nextNum = numSet.lower(mapNumber);
            while (nextNum != null && nextNum.value == nums[i]) {
                sameNumCount++;
                nextNum = numSet.lower(nextNum);
            }

            int numSmalls = numSet.headSet(mapNumber).size() - sameNumCount;
            output.add(numSmalls);

            numSet.remove(mapNumber);
        }

        return output;
    }

    /**
     * Helper data structure to differentiate between duplicate numbers within
     * the TreeSet.
     */
    private static class MapNumber implements Comparable<MapNumber>{
        public int value;
        public int index;

        public MapNumber(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        /**
         * If values are same, sort based on index. If not, then do sort on value.
         */
        public int compareTo(MapNumber obj) {
            if (this.value == obj.value) {
                return this.index - obj.index;
            }

            return this.value - obj.value;
        }
    }
}
