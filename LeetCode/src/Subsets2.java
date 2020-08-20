import java.util.ArrayList;
import java.util.List;

public class Subsets2 {
    public static void main(String[] args) {
        int[] nums = { 1, 1, 2, 2 };
        List<List<Integer>> output = subsetsWithDup(nums);
        output.forEach(row -> {
            row.forEach(num -> System.out.print(num + ", "));
            System.out.println();
        });
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();

        int bitRep = 1 << nums.length;
        for (int i = 0; i < Math.pow(2, nums.length); i++) {
            String binaryRep = Integer.toBinaryString(i | bitRep);
            binaryRep = binaryRep.substring(1);

            List<List<Integer>> rows = new ArrayList<>();

            int numsIndex = 0;
            for (int j = 0; j < binaryRep.length(); j++) {
                char token = binaryRep.charAt(j);
                int count = findDuplicateNumCount(nums, numsIndex);

                if (token == '1') {
                    int num = nums[numsIndex];
                    for (int k = 1; k < count; k++) {
                        List<Integer> duplicateNums = new ArrayList<>();
                        for (int l = 1; l < k; l++) {
                            duplicateNums.add(num);
                        }

                        if (rows.isEmpty()) {
                            rows.add(duplicateNums);
                        } else {
                            for (List<Integer> row: rows) {
                                row.addAll(duplicateNums);
                            }
                        }
                    }
                }

                numsIndex += count;
            }

            output.addAll(rows);
        }

        return output;
    }

    private static int findDuplicateNumCount(int[] nums, int index) {
        int count = 1;
        int num = nums[index];
        index++;

        while (index < nums.length && nums[index] == nums[index - 1]) {
            count++;
            index++;
        }

        return count;
    }

//    private static DistinctNum findDistinctNum(int[] nums, int index) {
//        int count = 1;
//        int num = nums[index];
//        int i = index + 1;
//        while (i < nums.length && nums[i] == nums[i - 1]) {
//            i++;
//            count++;
//        }
//
//        return new DistinctNum(index, count);
//    }

//    private static class DistinctNum {
//        public int index;
//        public int count;
//
//        public DistinctNum(int index, int count) {
//            this.index = index;
//            this.count = count;
//        }
//    }


}
