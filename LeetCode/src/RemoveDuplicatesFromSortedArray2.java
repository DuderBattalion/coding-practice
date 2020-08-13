public class RemoveDuplicatesFromSortedArray2 {
    public static void main(String[] args) {
//        int[] nums = { 1, 1, 1, 2, 2, 3 };
        int[] nums = { 0, 0, 1, 1, 1, 1, 2, 3, 3 };

        int count = removeDuplicates(nums);
        for (int i = 0; i < count; i++) {
            System.out.print(nums[i] + ",");
        }
    }

    public static int removeDuplicates(int[] nums) {
        int i = 0, insertIndex = 1;
        while (i < nums.length) {
            if (i + 1 == nums.length) {
                break;
            }

            if (nums[i] == nums[i + 1]) {
                nums[insertIndex] = nums[i];
                i++;
                insertIndex++;

            }

            int nextI = findNextNum(i, nums);
            if (nextI > 0) {
                nums[insertIndex] = nums[nextI];
                i = nextI;
                insertIndex++;
            }
        }

        return insertIndex;
    }

    private static int findNextNum(int index, int[] nums) {
        int num = nums[index];
        int nextIndex = -1;
        for (int i = index + 1; i < nums.length; i++) {
            if (nums[i] != num) {
                nextIndex = i;
                break;
            }
        }

        return nextIndex;
    }
}
