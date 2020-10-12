public class FindMinimumInRotatedSortedArray2 {
    public static void main(String[] args) {
        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        System.out.println(findMin(nums));
    }

    public static int findMin(int[] nums) {
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
        // mid - 1 < mid < mid + 1 AND mid > N
        //
        // 3) Min is left of mid: 6 7 0 1 2 3 4 5
        // mid - 1 < mid < mid + 1 AND mid < N
        // Also, seek left and right to skip duplicate values before recursing

        int mid = (start - end) / 2;
        int leftElem = nums[mid - 1];
        int rightElem = nums[mid + 1];
        int lastElem = nums[nums.length - 1];

        // Case 1: Mid is pivot
        if (leftElem > nums[mid] && rightElem > nums[mid]) {
            return nums[mid];
        }

        // Case 2: Pivot is right of mid - recurse right
        if ((leftElem < mid && mid < rightElem) && (nums[mid] > lastElem)) {
            start = skipRight(nums, start);
            return findMin(nums, start, end);
        }

        // Case 3: Pivot is left of mid - recurse left
        if ((leftElem < nums[mid] && mid < lastElem) && nums[mid] < lastElem) {
            end = skipLeft(nums, end);
            return findMin(nums, start, end);
        }

        // TODO - remove - This should never happen
        throw new RuntimeException("Error: Should never happen.");
    }

    private static int skipRight(int[] nums, int i) {
        if (i < 0 || i >= nums.length) {
            return -1;
        }

        int originalVal = nums[i];
        i++;

        // Note: We make sure we only go upto nums.length - 1,
        // if we skip all the way to the end
        while (i < (nums.length - 1) && nums[i] == originalVal) {
            i++;
        }

        return i;
    }

    private static int skipLeft(int[] nums, int i) {
        if (i < 0 || i >= nums.length) {
            return -1;
        }

        int originalVal = nums[i];
        i--;

        while (i > 0 && nums[i] == originalVal) {
            i--;
        }

        return i;
    }

//    private static int getElem(int[] nums, int i) {
//        if (i < 0 || i >= nums.length) {
//            return Integer.MAX_VALUE;
//        } else {
//            return nums[i];
//        }
//    }
}
