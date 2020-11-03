import java.util.ArrayDeque;
import java.util.Deque;

public class CreateMaximumNumber {
    public static void main(String[] args) {
//        int[] nums1 = { 9, 1, 2, 5, 8, 3 };
//
//        int[] maxNums1 = getMaximumNumber(nums1, 3);
//        for (int num: maxNums1) {
//            System.out.print(num + " ");
//        }

//        int[] nums1 = { 6, 7, 8 };
//        int[] nums2 = { 9, 7, 2, 9 };

//        int[] mergeNum = mergeMaximumNumbers(nums1, nums2);
//        for (int num: mergeNum) {
//            System.out.print(num + ", ");
//        }

//        System.out.println(getNumericValue(nums2));

//        int[] nums1 = { 6, 7, 8 };
//        int[] nums2 = { 9, 7, 2, 9 };

        int[] nums1 = { 3, 4, 6, 5 };
        int[] nums2 = { 9, 1, 2, 5, 8, 3 };

//        int[] nums1 = { 6, 7 };
//        int[] nums2 = { 6, 0, 4 };

//        int[] nums1 = { 3, 9};
//        int[] nums2 = { 8, 9 };

//        int[] nums1 = {  };
//        int[] nums2 = {  };

//        int[] nums1 = { 1,6,5,4,7,3,9,5,3,7,8,4,1,1,4 };
//        int[] nums2 = { 4,3,1,3,5,9 };

        int[] maxNum = maxNumber(nums1, nums2, 5);
        for (int num: maxNum) {
            System.out.print(num + ", ");
        }

//        int[] first = { 9, 8, 6, 5, 3 };
//        int[] second = { 9, 6, 5, 8, 3 };
//        System.out.println(isFirstNumBigger(first, second));
    }

    /**
     * Disclaimer
     * Excellent discussion at
     * https://web.archive.org/web/20160120093629/http://algobox.org/create-maximum-number/
     *
     * Algorithm
     * This is a tricky problem which requires solving two smaller sub problems.
     *
     * ## Subproblem 1 - Create maximum number from one array
     * Given an int[] array and a value k, return the maximum number that can be
     * generated using k digits. Note that digit order has to be maintained.
     *
     * ## Subproblem 2 - Merge two maximum number arrays
     * Given two int[] arrays - nums1 and nums2, using all the digits, merge the
     * two num arrays to return the maximum number. Again, necessary to maintain
     * digit order.
     *
     * ## Final solution
     * Using solutions to subproblems 1 and 2, we can try to get maximum numbers from
     * both array with i and k - i numbers, and then merge these results to get the final
     * maximum number. The maximum of these final max numbers will be the solution.
     */
    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] maxNumber = new int[k];

        for (int i = 0; i <= k; i++) {
            int[] maxNums1 = getMaximumNumber(nums1, i);
            int[] maxNums2 = getMaximumNumber(nums2, k-i);

            int[] mergeNum = mergeMaximumNumbers(maxNums1, maxNums2);
            if (isFirstNumBigger(mergeNum, maxNumber)) {
                maxNumber = mergeNum;
            }
        }

        return maxNumber;
    }

    /**
     * Subproblem 1
     * Create maximum number from one array
     */
    private static int[] getMaximumNumber(int[] nums, int k) {
        Deque<Integer> numStack = new ArrayDeque<>(k);

        for (int i = 0; i < nums.length; i++) {
             while (!numStack.isEmpty()
                     && (numStack.size() + (nums.length - i)) > k
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

    /**
     * Subproblem 2
     * Merge two maximum number arrays
     */
    private static int[] mergeMaximumNumbers(int[] nums1, int[] nums2) {
        int[] mergedNum = new int[nums1.length + nums2.length];
        int index1 = 0, index2 = 0, mergeIndex = 0;

        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] > nums2[index2]) {
                mergedNum[mergeIndex] = nums1[index1];
                index1++;
            } else if (nums2[index2] > nums1[index1]) {
                mergedNum[mergeIndex] = nums2[index2];
                index2++;
            } else {
                // Equal values - Look ahead to pick value with greater
                // upcoming number
                // Ex: { 6, 7 } and { 6, 0, 4 } - pick 6 from { 6, 7 }
                boolean isFirst = isFirstNumArrayBetter(nums1, index1, nums2, index2);
                if (isFirst) {
                    mergedNum[mergeIndex] = nums1[index1];
                    index1++;
                } else {
                    mergedNum[mergeIndex] = nums2[index2];
                    index2++;
                }
            }

            mergeIndex++;
        }

        while (index1 < nums1.length) {
            mergedNum[mergeIndex] = nums1[index1];
            index1++;
            mergeIndex++;
        }

        while (index2 < nums2.length) {
            mergedNum[mergeIndex] = nums2[index2];
            index2++;
            mergeIndex++;
        }

        return mergedNum;
    }

    /**
     * Part of subproblem 2 - merging.
     * Helps the greedy merge algorithm decide which num array to
     * go with in case both num array 1 and 2 currently have equal values.
     *
     * This picks the array with the bigger number on the right.
     */
    private static boolean isFirstNumArrayBetter(int[] nums1, int index1,
                                                 int[] nums2, int index2) {
        Boolean isFirstBetter = null;
        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] > nums2[index2]) {
                isFirstBetter = true;
                break;
            } else if (nums2[index2] > nums1[index1]) {
                isFirstBetter = false;
                break;
            }

            index1++;
            index2++;
        }

        // If ran out of digits on one of the numbers, with previous
        // digits equal (ex: 3 3 and 3 3 4), stick with the one with
        // longer digits
        if (isFirstBetter == null) {
            isFirstBetter = index1 < nums1.length;
        }

        return isFirstBetter;
    }

    private static boolean isFirstNumBigger(int[] first, int[] second) {
        if (first.length != second.length) {
            return first.length > second.length;
        }

        boolean isFirstBigger = true;
        for (int i = 0; i < first.length; i++) {
            if (first[i] == second[i]) {
                continue;
            }

            isFirstBigger = first[i] > second[i];
            break;
        }

        return isFirstBigger;
    }
}
