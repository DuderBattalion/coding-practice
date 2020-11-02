import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountSmallerNumbersWithMergeSort {
    public static void main(String[] args) {
//        int[] nums = { 5, 2, 6, 1 };
//        int[] nums = { 5, 2, 2, 6, 1 };
//        int[] nums = {  };
//        int[] nums = { 5 };

        int[] nums = { 26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41 };

//        int[] nums = { 84,66,65,36,100,41 };

        List<Integer> output = countSmaller(nums);
        for (int count: output) {
            System.out.print(count + ", ");
        }
    }

    /**
     * Algorithm
     * Disclaimer - Tricky solution piggybacking on merge sort. See discussion at:
     * https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76584/Mergesort-solution
     */
    public static List<Integer> countSmaller(int[] nums) {
        NumberHelper[] numberHelpers = new NumberHelper[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numberHelpers[i] = new NumberHelper(nums[i], i);
        }

        int[] count = new int[numberHelpers.length];
        modifiedMergeSort(numberHelpers, 0, nums.length - 1, count);

        List<Integer> output = new ArrayList<>();
        for (int num: count) {
            output.add(num);
        }

        return output;
    }

    private static void modifiedMergeSort(NumberHelper[] numberHelpers, int start, int end,
                                               int[] count) {
        if (start >= end) {
            return;
        }

        int mid = (start + end) / 2;
        modifiedMergeSort(numberHelpers, start, mid, count);
        modifiedMergeSort(numberHelpers, mid + 1, end, count);

        merge(numberHelpers, start, end, count);
    }

    private static void merge(NumberHelper[] numberHelpers, int start, int end, int[] count) {
        int mid = (start + end) / 2;

        int leftArrayIndex = start;
        int rightArrayIndex = mid + 1;
        int smallNumberCount = 0;

        int mergeArrayIndex = 0;
        NumberHelper[] mergeArray = new NumberHelper[end - start + 1];

        while (leftArrayIndex <= mid && rightArrayIndex <= end) {
            if (numberHelpers[rightArrayIndex].value < numberHelpers[leftArrayIndex].value) {
                mergeArray[mergeArrayIndex] = numberHelpers[rightArrayIndex];

                rightArrayIndex++;
                smallNumberCount++;
            } else {
                mergeArray[mergeArrayIndex] = numberHelpers[leftArrayIndex];

                int countIndex = numberHelpers[leftArrayIndex].index;
                count[numberHelpers[leftArrayIndex].index] += smallNumberCount;

                leftArrayIndex++;
            }

            mergeArrayIndex++;
        }

        while (leftArrayIndex <= mid) {
            mergeArray[mergeArrayIndex] = numberHelpers[leftArrayIndex];
            count[numberHelpers[leftArrayIndex].index] += smallNumberCount;

            leftArrayIndex++;
            mergeArrayIndex++;
        }

        while (rightArrayIndex <= end) {
            mergeArray[mergeArrayIndex] = numberHelpers[rightArrayIndex];

            rightArrayIndex++;
            mergeArrayIndex++;
        }

        for (int i = start; i <= end; i++) {
            numberHelpers[i] = mergeArray[i - start];
        }
    }

    private static class NumberHelper {
        public int value;
        public int index;

        public NumberHelper(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
