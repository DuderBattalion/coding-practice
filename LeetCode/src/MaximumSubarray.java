import java.util.ArrayList;
import java.util.List;

public class MaximumSubarray {
    public static void main(String[] args) {

    }

    public int maxSubArray(int[] nums) {
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
    private List<Integer> createNumList(int[] nums) {
        // Init num list
        List<Integer> numList = new ArrayList<>();
        numList.add(0); // Dummy node

        int sum = 0 ;
        for (int num: nums) {
            if (num < 0) {
                numList.add(sum);
                numList.add(num);
                sum = 0;
            } else
            {
                sum += num;
            }
        }

        if (sum > 0) {
            numList.add(sum);
        }

        // Recursively do merge pass
        int prevListSize = numList.size();
        do {
            numList = doMergePass(numList);
        } while (prevListSize != numList.size()); // List changed

        return numList;
    }

    private List<Integer> doMergePass(List<Integer> numList) {
        List<Integer> newList = new ArrayList<>();
        if (numList.size() == 1) {
            return numList;
        }

        if (numList.size() == 2) {
            int sum =  numList.get(0) + numList.get(1);
            newList.add(numList.get(0));
            if (sum > newList.get(0)) {
                newList.add(numList.get(1));
            }

            return newList;
        }

        // Merge pass
        int i = 0;
        while (i < numList.size()) {
            int a = numList.get(i);
            int b = (i + 1) < numList.size() ? numList.get(i + 1) : 0;
            int c = (i + 2) < numList.size() ? numList.get(i + 2) : 0;

            if (a + b + c > a) {
                newList.add(a + b + c);
            } else {
                newList.add(a);

                if (b > 0) {
                    newList.add(b);
                }

                if (c > 0) {
                    newList.add(c);
                }
            }

            i += 3;
        }

        return newList;
    }
}
