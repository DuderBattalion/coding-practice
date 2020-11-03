import java.util.ArrayDeque;
import java.util.Deque;

public class CreateMaximumNumber {
    public static void main(String[] args) {
        int[] nums1 = { 9, 1, 2, 5, 8, 3 };

        int[] maxNums1 = getMaximumNumber(nums1, 3);
        for (int num: maxNums1) {
            System.out.print(num + " ");
        }
    }

//    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
//        int[] maxNums1 = getMaximumNumber(nums1);
//        int[] maxNums2 = getMaximumNumber(nums2);
//
//        return mergeMaximumNumbers(maxNums1, maxNums2);
//    }

    private static int[] getMaximumNumber(int[] nums, int k) {
        Deque<Integer> numStack = new ArrayDeque<>(k);

        for (int i = 0; i < nums.length; i++) {
             while (!numStack.isEmpty()
                     && (numStack.size() + (nums.length - i)) >= k
                     && nums[i] > numStack.peek()) {
                 numStack.pop();
             }

             if (numStack.size() < k) {
                 numStack.push(nums[i]);
             }
        }

        return convertStackToArray(numStack);
    }

    private static int[] convertStackToArray(Deque<Integer> numStack) {
        int[] numArray = new int[numStack.size()];
        for (int i = numStack.size() - 1; i >= 0; i--) {
            numArray[i] = numStack.pop();
        }

        return numArray;
    }
}
