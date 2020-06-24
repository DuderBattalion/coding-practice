import java.util.Arrays;

public class SearchRotatedArray {
    public static void main(String[] args) {
        int[] nums = { 4,5,6,7,0,1,2 };
//        int[] nums = { 4,5 };

        System.out.println(search(nums, 2));

//        System.out.println(Arrays.binarySearch( nums, 4, 7, 2));
    }

    public static int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        if (nums.length == 1) {
            return (target == nums[0] ? 0 : -1);
        }

        return modifiedBinarySearch(nums, target, 0, nums.length - 1);
    }

    private static int modifiedBinarySearch(int[] nums, int target, int start, int end) {
        if (start >= end) {
            return (nums[start] == target) ? start : -1 ;
        }

        int index = -1;
        int mid = (start + end)/2;

        // Split array in mid point
        // Case 1:
        // If nums[0] < nums[mid], then pivot is in right arr
        // if nums[0] < target < nums[mid], just binary search on left arr
        // If not, then modified binary search on right arr

        // Case 2:
        // If pivot is in left arr (nums[0] > nums[mid])
        // if (nums[mid] < target < nums[last]) - then binary search on right arr
        // else, modified search on left arr

        if (nums[mid] == target) {
            // Case 1 - Target is mid point
            return mid;
        } else if (nums[start] < nums[mid]) {
            if (nums[start] <= target && target <= nums[mid]) {
                return Arrays.binarySearch(nums, start, mid + 1, target); // mid + 1 to make include mid
            } else {
                return modifiedBinarySearch(nums, target, mid, nums.length - 1);
            }
        } else {
            if (nums[mid] <= target && target <= nums[nums.length - 1]) {
                return Arrays.binarySearch(nums, mid, nums.length, target);
            } else {
                return modifiedBinarySearch(nums, target, start, mid);
            }
        }
    }
}
