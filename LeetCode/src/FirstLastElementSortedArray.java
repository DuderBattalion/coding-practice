import java.util.Arrays;

public class FirstLastElementSortedArray {
    public static void main(String[] args) {
        int[] nums = { 5,7,7,8,8,10 };
        int[] range = searchRange(nums, 6);

        System.out.println(String.format("[%d, %d]", range[0], range[1]));
    }

    public static int[] searchRange(int[] nums, int target) {
        int[] range = new int[2];
        range[0] = -1;
        range[1] = -1;

        int index = Arrays.binarySearch(nums, target);
        if (index == -1) {
            return range;
        }

        if (index == 0) {
            // Leftmost index
            range[0] = 0;
            range[1] = findRightRange(nums, target, 1);
        } else if (index == nums.length - 1) {
            // Rightmost index
            range[0] = findLeftRange(nums, target, nums.length - 1);
            range[1] = nums.length - 1;
        } else if (nums[index-1] == target && nums[index+1] == target) {
            // Spreads both left and right
            range[0] = findLeftRange(nums, target, index);
            range[1] = findLeftRange(nums, target, index);
        } else if (nums[index-1] == target) {
            // Spreads only left
            range[0] = findLeftRange(nums, target, index-1);
            range[1] = index;
        } else {
            // Spreads only right
            range[0] = index;
            range[1] = findRightRange(nums, target, index + 1);
        }

        return range;
    }

    private static int findRightRange(int[] nums, int target, int start) {
        if (start >= nums.length) {
            return -1;
        }

        int index = Arrays.binarySearch(nums, start, nums.length, target);
        if (index < 0) {
            return -1;
        }

        if (index == nums.length) {
            return index;
        }

        if (nums[index + 1] == target) {
            // Keep looking
            return findRightRange(nums, target, index + 1);
        } else {
            return index;
        }
    }

    private static int findLeftRange(int[] nums, int target, int end) {
        if (end < 0) {
            return -1;
        }

        // Note: end+1 required - binarySearch end param is exclusive bu default
        int index = Arrays.binarySearch(nums, 0, end+1, target);
        if (index < 0) {
            return -1;
        }

        if (index == 0) {
            return index;
        }

        if (nums[index - 1] == target) {
            // Keep looking
            return findRightRange(nums, target, index - 1);
        } else {
            return index;
        }
    }

//    private static int findLeftRange(int[] nums, int target, int start, int end) {
//        if (start > end) {
//            return -1;
//        }
//
//        if (start < 0 || end >= nums.length) {
//            throw new RuntimeException(
//                    String.format(
//                            "[FindLeftRange]: Unexpected Error: index out of range. start: %d, end: %d",
//                            start, end));
//        }
//
//        if (end == 0) {
//            return end;
//        }
//
//        if (nums[end - 1] != target) {
//            return end;
//        }
//
//        int mid = (end - start)/2;
//        if (nums[mid] == target) {
//            // Keep going left
//            return findLeftRange(nums, target, start, mid);
//        } else {
//            // Left range is somewhere between mid and end
//            return findLeftRange(nums, target, mid, end);
//        }
//    }
//
//    private static int findRightRange(int[] nums, int target, int start, int end) {
//        if (start > end) {
//            return -1;
//        }
//
//        if (start < 0 || end >= nums.length) {
//            throw new RuntimeException(
//                    String.format(
//                            "[FindLeftRange]: Unexpected Error: index out of range. start: %d, end: %d",
//                            start, end));
//        }
//
//        if (start == nums.length - 1) {
//            return -1;
//        }
//
//
//    }
}
