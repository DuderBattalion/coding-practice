//public class CountOfRangeSum {
//    public static void main(String[] args) {
//
//    }
//
//    public static int countRangeSum(int[] nums, int lower, int upper) {
//        int[] prefixSums = calculatePrefixSums(nums);
//        return mergeSort(nums, prefixSums, lower, upper, 0, 0);
//    }
//
//    private static int mergeSort(int[] nums, int[] prefixSums,
//                                 int lower, int upper,
//                                 int start, int end) {
//        int mid = (start + end) / 2;
//
//        int leftCount = mergeSort(nums, prefixSums, lower, upper, start, mid);
//        int rightCount = mergeSort(nums, prefixSums, lower, upper, mid+1, end);
//
//        int mergeCount = merge(nums, prefixSums, lower, upper, start, end);
//        return leftCount + rightCount + mergeCount;
//    }
//
//    private static int merge(int[] nums, int[] prefixSums,
//                             int lower, int upper,
//                             int start, int end) {
//        int mid = (start + end) / 2;
//
//        // i = range start
//        // j = first index where prefixSum[j] >= lower
//        // k = first index where prefixSum[k] > upper
//        // All values between j to k are in range.
////        int i = start;
////        int j = mid + 1;
////
////        while (j <= end && (prefixSums[j] - prefixSums[i]) < lower) {
////            j++;
////        }
////
////        int k = j;
////        while (k <= end && (prefixSums[k] - prefixSums[i]) <= upper) {
////            k++;
////        }
//
//        int count = 0;
//        int leftArrayIndex = start;
//        int rightArrayIndex = end;
//
//        while (leftArrayIndex <= mid && rightArrayIndex <= end) {
//            int i = leftArrayIndex;
//            int j = rightArrayIndex;
//
//            while (j <= end && (prefixSums[j] - prefixSums[i]) < lower) {
//                j++;
//            }
//
//            int k = j;
//            while (k <= end && (prefixSums[k] - prefixSums[i]) <= upper) {
//                k++;
//            }
//
//            count += (k - j);
//
//
//
//
//        }
//
//        return (k - j);
//    }
//}
