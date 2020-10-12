public class FindMinimumInRotatedSortedArray2 {
    public static void main(String[] args) {
//        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
//        int[] nums = { 1, 3, 5 };
//        int[] nums = { 2, 2, 2, 0, 1 };

        int[] nums = { 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 7, 0, 0, 1, 1, 1, 2, 2 };
//        int[] nums = { };
//        int[] nums = { 2 };
//        int[] nums = { 2, 1 };

//        int[] nums = { 1, 1, 1 };
//        int[] nums = { 1, 1, 1, 1, 1, 1, 1, 2 };
//        int[] nums = { 1, 1, 1, 1, 1, 1, 1, 0 };

//        int[] nums = { 3, 1, 1 };

        System.out.println(findMin(nums));
    }

    public static int findMin(int[] nums) {
        if (nums.length == 0) {
            return Integer.MAX_VALUE;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        return findMin(nums, 0, nums.length - 1);
    }

    private static int findMin(int[] nums, int start, int end) {
        if (start > end) {
            // Should never happen
            throw new RuntimeException("error: Start shouldn't be more than end.");
        }

        if (start == end) {
            return nums[start];
        }

        if (end - start == 1) {
            return Math.min(nums[start], nums[end]);
        }

        // Cases
        // 1) Mid is pivot: 4 5 6 0 1 2 3
        // mid - 1 > mid && mid < mid + 1
        //
        // 2) Min is right of mid: 3 4 5 6 7 0 1 2
        // mid > N
        //
        // 3) Min is left of mid: 6 7 0 1 2 3 4 5
        // mid < N
        // Also, seek left or right to skip duplicate values before recursing

        int mid = start + (end - start) / 2;
        int leftElemIndex = mid - 1;
        int rightElemIndex = mid + 1;

        int leftElem = nums[mid - 1];
        int rightElem = nums[mid + 1];
        int lastElem = nums[nums.length - 1];

//        // Case 0: Mid is surrounded by duplicates
//        // If both left and right surround, recurse left and right
//        if (leftElem == nums[mid] && rightElem == nums[mid]) {
//            return Math.min(findMin(nums, start, mid - 1), findMin(nums, mid + 1, end));
//        } else if (leftElem == nums[mid]) {
//            return findMin(nums, start, mid - 1);
//        } else if (rightElem == nums[mid]) {
//            return findMin(nums, mid + 1, end);
//        }

        // Case 0: Mid is surrounded by duplicates
        // If both left and right surround, recurse left and right
        if (leftElem == nums[mid] || rightElem == nums[mid]) {
            return Math.min(findMin(nums, start, mid - 1), findMin(nums, mid + 1, end));
        }

//        if (leftElem == nums[mid]) {
//            leftElemIndex = skipLeft(nums, mid);
//            if (leftElemIndex > 0) {
//                leftElem = nums[leftElemIndex - 1];
//            } else {
//                leftElem = nums[0];
//            }
//        }
//
//        if (rightElem == nums[mid]) {
//            rightElemIndex = skipRight(nums, mid);
//            if (rightElemIndex < nums.length - 2) {
//                rightElem = nums[rightElemIndex + 1];
//            } else {
//                rightElem = nums[nums.length - 1];
//            }
//        }

        // If left elem and right elem still match mid,
        // then entire array is made up of same numbers
        if (leftElem == nums[mid] && rightElem == nums[mid]) {
            return nums[mid];
        }

        // Case 1: Mid is pivot
        if (leftElem > nums[mid] && rightElem > nums[mid]) {
            return nums[mid];
        }

        // Case 2: Pivot is right of mid - recurse right
        if (nums[mid] > lastElem) {
//            start = skipRight(nums, mid);
            return findMin(nums, rightElemIndex, end);
        }

        // Case 3: Pivot is left of mid - recurse left
        if (nums[mid] < lastElem) {
//            end = skipLeft(nums, mid);
            return findMin(nums, start, leftElemIndex);
        }

        // TODO - remove - This should never happen
        throw new RuntimeException("Error: Should never happen.");
    }

    private static int skipRight(int[] nums, int i) {
        if (i < 0 || i >= nums.length) {
            return -1;
        }

        int originalVal = nums[i];
        int index = i;
        while (i < nums.length && nums[i] == originalVal) {
            index = i;
            i++;
        }

        return index;
    }

    private static int skipLeft(int[] nums, int i) {
        if (i < 0 || i >= nums.length) {
            return -1;
        }

        int originalVal = nums[i];
        int index = i;

        while (i >= 0 && nums[i] == originalVal) {
            index = i;
            i--;
        }

        return index;
    }
}
