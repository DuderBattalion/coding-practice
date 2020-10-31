import java.util.Arrays;
import java.util.List;

public class CountSmallerNumbersWithMergeSort {
    public static void main(String[] args) {
        int[] nums = { 5, 2, 6, 1 };
//        int[] nums = {  };
//        int[] nums = { 5 };

//        int[] nums = { 26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41 };

//        int[] nums = { 84,66,65,36,100,41 };

        List<Integer> output = countSmaller(nums);
        for (int count: output) {
            System.out.print(count + ", ");
        }
    }

    public static List<Integer> countSmaller(int[] nums) {
        NumberHelper[] numberHelpers = new NumberHelper[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numberHelpers[i] = new NumberHelper(nums[i], i);
        }

        Integer[] count = new Integer[numberHelpers.length];
        modifiedMergeSort(numberHelpers, 0, 0, count);

        return Arrays.asList(count);
    }

    private static void modifiedMergeSort(NumberHelper[] numberHelpers, int start, int end,
                                               Integer[] count) {
        if (start >= end) {
            return;
        }

        int mid = (start + end) / 2;
        modifiedMergeSort(numberHelpers, start, mid, count);
        modifiedMergeSort(numberHelpers, mid + 1, end, count);

        merge(numberHelpers, start, end, count);
    }

    private static void merge(NumberHelper[] numberHelpers, int start, int end, Integer[] count) {
        int mid = (start + end) / 2;

        int leftIndex = start;
        int rightIndex = mid + 1;
        int smallNumberCount = 0;

        int mergedIndex = 0;
        NumberHelper[] merged = new NumberHelper[end - start + 1];


        if (leftIndex <= mid && rightIndex <= end) {
            if (numberHelpers[rightIndex].value < numberHelpers[leftIndex].value) {
                merged[mergedIndex] = numberHelpers[rightIndex];

                rightIndex++;
                smallNumberCount++;
            } else {
                merged[mergedIndex] = numberHelpers[leftIndex];
                count[numberHelpers[leftIndex].index] = smallNumberCount;

                leftIndex++;
            }

            mergedIndex++;
        }

        while (leftIndex <= mid) {
            merged[mergedIndex] = numberHelpers[leftIndex];
            count[numberHelpers[leftIndex].index] = smallNumberCount;

            leftIndex++;
            mergedIndex++;
        }

        while (rightIndex <= end) {
            merged[mergedIndex] = numberHelpers[rightIndex];
            rightIndex++;
        }

        for (int i = start; i <= end; i++) {
            numberHelpers[i] = merged[i - start];
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
