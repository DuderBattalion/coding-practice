public class SearchInRotatedArray2 {
    public static void main(String[] args) {
//        int[] nums = { 2, 5, 6, 0, 0, 0, 0, 0, 1, 2 };
        int[] nums = { 2 };

        System.out.println(search(nums, 3));
    }

    public static boolean search(int[] nums, int target) {
        if (nums.length == 0) {
            return false;
        }

        int start = 0, end = nums.length - 1;
        int mid = (start + end) / 2;

        boolean isFound = false;
        while (start <= end) {
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
