public class FirstMissingPositive {
    public static void main(String[] args) {
//        int[] nums = { 7, 8, 9, 11, 12 };

//        int[] nums = { 3, 4, -1, 1 };

//        int[] nums = { 1, 2, 0 };

//        int[] nums = { 1, 1 };

//        int[] nums = { 0, 0 };

        int[] nums = { -1, -2, -3 };

        System.out.println(firstMissingPositive(nums));
    }

    public static int firstMissingPositive(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        int sum = 0;

        for (int num: nums) {
            if (num < 0) {
                continue;
            }

            if (num < min) {
                min = num;
            }

            if (num > max) {
                max = num;
            }

            sum += num;
        }

        int missingNum;
        if (min > 1) {
            missingNum = 1;
        } else if (min == 1) {
            if (max > 1) {
                int expectedTotal = calcSeriesTotal(max) - calcSeriesTotal(min - 1);
                missingNum = expectedTotal - sum;
            } else {
                missingNum = 2; // Case: [1, 1]
            }

        } else { // min == 0
            if (max == 0) {
                // All non-positive integers in series
                missingNum = 1;
            } else {
                int expectedTotal = calcSeriesTotal(max);
                missingNum = expectedTotal - sum;
            }
        }

        if (missingNum == 0) {
            missingNum = max + 1;
        }

        return missingNum;
    }

    /**
     * Calculates series total from 0 .. N
     * @param num
     * @return
     */
    private static int calcSeriesTotal(int num) {
        return (num * (num+1))/2;
    }
}
