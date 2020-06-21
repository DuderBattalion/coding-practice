public class RemoveElement {
    public static void main(String[] args) {
//        int[] nums = { 0,1,2,2,3,0,4,2 };
        int[] nums = {};

        int numLen = removeElement(nums, 2);
        System.out.println(numLen);
        for (int i = 0; i < numLen; i++) {
            System.out.print(nums[i] + ",");
        }
    }

    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }

        int numLen = 0;
        int writeIndex = 0;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == val) {
                i++;
                continue;
            }

            nums[writeIndex] = nums[i];
            writeIndex++;
            i++;
            numLen++;
        }

        return numLen;
    }
}
