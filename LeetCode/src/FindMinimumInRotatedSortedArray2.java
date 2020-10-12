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

    /**
     * Algorithm: Modified binary search
     * Cases:
     * 1) If pivot has duplicate neighbors, no way to judge which way to go. Recurse both left and right.
     * 2) If pivot is on the right (nums[mid] > nums[N]), recurse right
     * 3) If pivot is on the left (nums[mid] < N), recurse left
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private static int findMin(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }

        if (end - start == 1) {
            return Math.min(nums[start], nums[end]);
        }

        int mid = start + (end - start) / 2;
        int leftElemIndex = mid - 1;
        int rightElemIndex = mid + 1;

        int leftElem = nums[mid - 1];
        int rightElem = nums[mid + 1];
        int lastElem = nums[nums.length - 1];

        // Case 0: Mid has neighboring duplicates
        if (leftElem == nums[mid] || rightElem == nums[mid]) {
            return Math.min(findMin(nums, start, mid - 1), findMin(nums, mid + 1, end));
        }

        // Case 1: Mid is pivot
        if (leftElem > nums[mid] && rightElem > nums[mid]) {
            return nums[mid];
        }

        // Case 2: Pivot is right of mid - recurse right
        if (nums[mid] > lastElem) {
            return findMin(nums, rightElemIndex, end);
        }

        // Case 3: Pivot is left of mid - recurse left
        return findMin(nums, start, leftElemIndex);
    }
}
