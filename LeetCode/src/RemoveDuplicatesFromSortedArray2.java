public class RemoveDuplicatesFromSortedArray2 {
    public static void main(String[] args) {
//        int[] nums = { 1, 1, 1, 2, 2, 3 };
//        int[] nums = { 0, 0, 1, 1, 1, 1, 2, 3, 3 };
//        int[] nums = { 1, 1, 1 };
        int[] nums = {  };

        int count = removeDuplicates(nums);
        for (int i = 0; i < count; i++) {
            System.out.print(nums[i] + ",");
        }
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int insertIndex = 1, numCount = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                if (numCount < 2) {
                    nums[insertIndex] = nums[i];
                    insertIndex++;
                }

                numCount++;
            } else {
                numCount = 1;
                nums[insertIndex] = nums[i];
                insertIndex++;
            }
        }

        return insertIndex;
    }
}
