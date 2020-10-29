import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class CountSmallerNumbers {
    public static void main(String[] args) {
        int[] nums = { 5, 2, 6, 1 };

        List<Integer> output = countSmaller(nums);
        for (int count: output) {
            System.out.print(count + ", ");
        }
    }

    public static List<Integer> countSmaller(int[] nums) {
        TreeSet<Integer> numSet = new TreeSet<>();
        for (int num: nums) {
            numSet.add(num);
        }

        List<Integer> output = new ArrayList<>();
        for (int num: nums) {
            int count = numSet.headSet(num).size();
            numSet.remove(num);

            output.add(count);
        }

        return output;
    }
}
