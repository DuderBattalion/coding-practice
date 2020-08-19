import java.util.ArrayList;
import java.util.List;

public class Subsets2 {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4 };
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

            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < binaryRep.length(); j++) {
                char token = binaryRep.charAt(j);
                if (token == '1') {
                    row.add(nums[j]);
                }
            }

            output.add(row);
        }

        return output;
    }
}
