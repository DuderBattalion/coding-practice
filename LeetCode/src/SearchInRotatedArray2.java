import java.util.Arrays;

public class SearchInRotatedArray2 {
    public static void main(String[] args) {
//        int[] nums = { 2, 5, 6, 0, 0, 0, 0, 0, 1, 2 };
        int[] nums = { 3, 5, 1 };

        System.out.println(search(nums, 1));
    }

    public static boolean search(int[] nums, int target) {
        if (nums.length == 0) {
            return false;
        }

        int start = 0, end = nums.length - 1;
        int mid = (start + end) / 2;

        boolean isFound = false;
        while (start <= end) {
            if (start == end) {
                isFound = (nums[start] == target);
                break;
            }

            mid = (end - start) / 2;
            if (nums[mid] == target) {
                isFound = true;
                break;
            }

            int leftFromMid = findLeftMid(mid, nums, start);
            int rightFromMid = findRightMid(mid, nums, end);

            if (leftFromMid >= 0 && nums[leftFromMid] >= target) {
                end = leftFromMid;
            } else if (rightFromMid >= 0 && nums[rightFromMid] <= target) {
                start = rightFromMid;
            } else {
                break;
            }
        }

        return isFound;
    }

    private static int modifiedBinarySearch(int[] nums, int target, int start, int end) {
        if (start >= end) {
            return (nums[start] == target) ? start : -1 ;
        }

        // Base case - If only two left, no more recursion
        if ((end - start) == 1) {
            if (nums[start] == target) {
                return start;
            } else if (nums[end] == target) {
                return end;
            } else {
                return -1;
            }
        }

        int mid = (start + end)/2;

        int leftFromMid = findLeftMid(mid, nums, start);
        int rightFromMid = findRightMid(mid, nums, end);

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
        } else if (leftFromMid >= 0 && nums[start] < nums[leftFromMid + 1]) {
            if (nums[start] <= target && target <= nums[leftFromMid + 1]) {
                // mid + 2 to make include mid
                int index = Arrays.binarySearch(nums, start, leftFromMid + 2, target);

                return fixBinarySearchResult(index);
            } else {
                return modifiedBinarySearch(nums, target, mid, nums.length - 1);
            }
        } else {
            if (rightFromMid >= 0 && nums[rightFromMid] <= target && target <= nums[nums.length - 1]) {
                int index = Arrays.binarySearch(nums, rightFromMid, nums.length, target);

                return fixBinarySearchResult(index);
            } else {
                return modifiedBinarySearch(nums, target, start, rightFromMid);
            }
        }
    }

    // For non-zero starting arrays, if not found, gives weird negative index
    // Re-adjust to -1
    private static int fixBinarySearchResult(int index) {
        if (index < -1) {
            index = -1;
        }

        return index;
    }

    private static  int findLeftMid(int index, int[] nums, int start) {
        if (index < 0 || index >= nums.length) {
            return -1;
        }

        int num = nums[index];
        index--;

        int leftMid = -1;
        while (index >= start) {
            if (nums[index] != num) {
                leftMid = index;
                break;
            }

            index--;
        }

        return leftMid;
    }

    private static int findRightMid(int index, int[] nums, int end) {
        if (index < 0 || index >= nums.length) {
            return -1;
        }

        int num = nums[index];
        index++;

        int rightMid = -1;
        while (index <= end) {
            if (nums[index] != num) {
                rightMid = index;
                break;
            }

            index++;
        }

        return rightMid;
    }



}
