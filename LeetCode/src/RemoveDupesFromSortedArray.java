public class RemoveDupesFromSortedArray {
    public static void main(String[] args) {
//        int[] nums = { 1, 1, 2 };
//        int[] nums = { 0,0,1,1,1,2,2,3,3,4 };
//        int[] nums = { 1, 2, 3, 4 };
        int[] nums = { };
        int numLen = removeDuplicates(nums);

        System.out.println(numLen);
        for (int i = 0; i < numLen; i++) {
            System.out.print(nums[i] + ",");
        }
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int prevNum = nums[0];
        int writeIndex = 1;
        int numLen = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != prevNum) {
                nums[writeIndex] = nums[i];
                prevNum = nums[i];

                writeIndex++;
                numLen++;
            }
        }

        return numLen;
    }
}
