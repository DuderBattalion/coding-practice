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

//        int[] nums1 = { 3, 4, 6, 5 };
//        int[] nums2 = { 9, 1, 2, 5, 8, 3 };

//        int[] nums1 = { 6, 7 };
//        int[] nums2 = { 6, 0, 4 };

        int[] nums1 = { 3, 9};
        int[] nums2 = { 8, 9 };

        int[] maxNum = maxNumber(nums1, nums2, 3);
        for (int num: maxNum) {
            System.out.print(num + ", ");
        }
    }

    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        long maxValue = Long.MIN_VALUE;
        int[] maxNumber = null;

        for (int i = 0; i <= k; i++) {
            int[] maxNums1 = getMaximumNumber(nums1, i);
            int[] maxNums2 = getMaximumNumber(nums2, k-i);

            int[] mergeNum = mergeMaximumNumbers(maxNums1, maxNums2);
            long mergeNumVal = getNumericValue(mergeNum);
            if (mergeNumVal > maxValue) {
                maxValue = mergeNumVal;
                maxNumber = mergeNum;
            }
        }

        return maxNumber;
    }

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
                boolean isFirst = isFirstNumBetter(nums1, index1, nums2, index2);
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

    private static boolean isFirstNumBetter(int[] nums1, int index1,
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

    private static long getNumericValue(int[] numArray) {
        long value = 0, powerIndex = 0;
        for (int i = numArray.length - 1; i >= 0; i--) {
            value += numArray[i] * Math.pow(10, powerIndex);
            powerIndex++;
        }

        return value;
    }
}
