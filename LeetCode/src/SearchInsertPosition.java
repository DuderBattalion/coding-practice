public class SearchInsertPosition {
    public static void main(String[] args) {
//        int[] nums = { 1, 3, 5, 6 };
//        int[] nums = { };
//        int[] nums = { 1, 3 };
        int[] nums = { 1, 2, 4, 6, 7 };

        System.out.println(searchInsert(nums, 3));
    }

    public static int searchInsert(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }

        if (target < nums[0]) {
            return 0;
        }

        if (target > nums[nums.length - 1]) {
            return nums.length;
        }

        return recurseFindInsertPosition(nums, target, 0, nums.length - 1);
    }

    private static int recurseFindInsertPosition(int[] nums, int target, int start, int end) {
        // Base case - nothing found
        if (start == end) {
            if (nums[start] == target) {
                return start;
            } else {
                return start + 1;
            }
        }

        // Base case - just two left
        if (end - start == 1) {
            if (nums[start] == target) {
                return start;
            } else if (nums[end] == target) {
                return end;
            } else {
                // Base Case - Nothing found
                return start + 1;
            }
        }

        int mid = (end + start)/2;
        if (target == nums[mid]) {
            return mid;
        }

        if (target < nums[mid]) {
            return recurseFindInsertPosition(nums, target, start, mid);
        } else {
            return recurseFindInsertPosition(nums, target, mid, end);
        }
    }
}
