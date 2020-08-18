public class MergeSortedArray {
    public static void main(String[] args) {
        int[] nums1 = { 1, 2, 3, 0, 0, 0 };
        int[] nums2 = { 2, 5, 6 };

        merge(nums1, 3, nums2, 3);
        for (int num: nums1){
            System.out.print(num + ", ");
        }
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // Note: Zero index - minus 1 required
        int numLen = (m + n) - 1;
        m--;
        n--;

        while (numLen >= 0) {
            if (n >= 0 && m >= 0) {
                // Both arrays have values
                if (nums1[m] >= nums2[n]) {
                    nums1[numLen] = nums1[m];
                    m--;
                } else {
                    nums1[numLen] = nums2[n];
                    n--;
                }
            } else if (n < 0) {
                // Second array is done
                nums1[numLen] = nums1[m];
                m--;
            } else {
                // First array is done
                nums1[numLen] = nums2[n];
                n--;
            }

            numLen--;
        }
    }
}
