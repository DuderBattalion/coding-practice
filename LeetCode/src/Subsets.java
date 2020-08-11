import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Subsets {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        List<List<Integer>> subsets = subsets(nums);

        subsets.forEach(subset -> {
            subset.forEach(num -> {
                System.out.print(num + ", ");
            });

            System.out.println();
        });
    }

    public static  List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        int n = nums.length;

        int nthBit = 1 << n;
        for (int i = 0; i < (int)Math.pow(2, n); i++) {
            List<Integer> subset = new ArrayList<>();

            String bitSignature = Integer.toBinaryString(i | nthBit).substring(1);
            for (int j = 0; j < bitSignature.length(); j++) {
                if (bitSignature.charAt(j) == '1') {
                    subset.add(nums[j]);
                }
            }

            subsets.add(subset);

        }

        return subsets;
    }
}
