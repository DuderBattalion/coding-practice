import java.util.*;

public class CountSmallerNumbers {
    public static void main(String[] args) {
//        int[] nums = { 5, 2, 6, 1 };
//        int[] nums = {  };
//        int[] nums = { 5 };

//        int[] nums = { 26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41 };

        int[] nums = { 84,66,65,36,100,41 };

        List<Integer> output = countSmaller(nums);
        for (int count: output) {
            System.out.print(count + ", ");
        }
    }

    public static List<Integer> countSmaller(int[] nums) {
        TreeMap<Integer, Integer> numSet = new TreeMap<>();
        for (int num: nums) {
            if (numSet.containsKey(num)) {
                int count = numSet.get(num);
                numSet.put(num, count + 1);
            } else {
                numSet.put(num, 1);
            }
        }

        List<Integer> output = new ArrayList<>();
        for (int num: nums) {
            SortedMap<Integer, Integer> sortedSet = numSet.headMap(num);

            int count = 0;
            for (Map.Entry<Integer, Integer> entry: sortedSet.entrySet()) {
                int val = entry.getValue();
                count += val;
            }

            output.add(count);
            numSet.remove(num);
        }

        return output;
    }
}
