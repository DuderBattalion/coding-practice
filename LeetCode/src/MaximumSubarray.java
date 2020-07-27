import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MaximumSubarray {
    public static void main(String[] args) {
//        int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
//        int[] nums = { -2, -3, -4, -5 };
        int[] nums = { 0 };
        System.out.println(maxSubArray(nums));

    }

    public static int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int maxSum = Integer.MIN_VALUE;

        List<Integer> numList = createNumList(nums);
        for (int num: numList) {
            if (maxSum < num) {
                maxSum = num;
            }
        }

        return maxSum;
    }

    /**
     * Step 1: Merges all positive numbers recursively as a sum, so the
     * nums array is positive sums separate by negative numbers.
     * Step 2: Tries to merge neighboring positive numbers if it makes sense.
     * Step 3: Keep merging Step 2 until no more merges are possible
     * Step 4: Return the list of integers. The max of this list is the maxSum possible.
     */
    private static List<Integer> createNumList(int[] nums) {
        // Init num list
        List<Integer> numList = new ArrayList<>();

        int sum = 0 ;
        boolean isAllNegative = true;
        for (int num: nums) {
            if (num < 0) {
                numList.add(sum);
                numList.add(num);
                sum = 0;
            } else {
                isAllNegative = false;
                sum += num;
            }
        }

        if (isAllNegative) {
            List<Integer> negativeList = new ArrayList<>();
            Arrays.stream(nums).forEach(negativeList::add);

            return negativeList;
        }

        if (sum >= 0) {
            numList.add(sum);
        }

        // Recursively do merge pass
        int prevListSize;
        do {
            prevListSize = numList.size();
            doMergePass(numList);
        } while (prevListSize != numList.size()); // List changed

        return numList;
    }

    private static void doMergePass(List<Integer> numList) {
        if (numList.size() <= 2) {
            return;
        }

        // Merge pass
        int i = 0;
        while (i < numList.size()) {
            Integer left = numList.get(i);
            Integer middle = (i + 1) < numList.size() ? numList.get(i + 1) : null;
            Integer right = (i + 2) < numList.size() ? numList.get(i + 2) : null;

            if (middle != null && right != null) {
                int sum = left + middle + right;
                if (sum > left && sum > right) {
                    // Merge
                    // Step 1: Replace right with sum
                    numList.set(i + 2, sum);

                    // Step 2: Remove left and middle
                    // Note: Removing element in linkedlist changes 'i' index
                    // No need to increment pointer after removal, 'i' will be pointing to 'right` node
                    // after removal.
                    numList.remove(i);
                    numList.remove(i);
                } else {
                    // Move pointer to 'right' node
                    i += 2;
                }
            } else {
                // Move pointer to 'right' node
                i += 2;
            }
        }
    }
}
