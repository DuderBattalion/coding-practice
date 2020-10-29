public class BurstBalloon {
    public static void main(String[] args) {
//        int[] nums = { 3, 1, 5, 8 };
        int[] nums = { };

        System.out.println(maxCoins(nums));
    }

    /**
     * Disclaimer:
     * See discussion at:
     * https://www.youtube.com/watch?v=IFNibRVgFBo&ab_channel=TusharRoy-CodingMadeSimple
     *
     * Algorithm:
     * Dynamic Programming
     *
     * Intuition:
     * The natural intuition is to try and hunt down which balloon to pop first, but in that case,
     * the coins for popping balloon at kth location would be:
     * dp[i][k-1] * num[k] * dp[k+1][j],
     * with i .. j being the subarray being considered at the moment.
     *
     * The issue with this is that the (k-1) and (k+1) subproblems are not independent, since the adjacency
     * of balloons are going to be affected by popping the kth balloon.
     *
     * So, the solution comes by consider k to be the balloon that is popped last, in a range i .. j,
     * with i = left boundary, and j = right boundary. In this case, we know that the left (k-1) and
     * right (k+1) subproblems are not affected by the i .. j subarray balloon popping - there's just the
     * kth ballon being popped last.
     *
     * For first pass, i = 1, j = 1, so we only consider popping a balloon in one cell and figuring
     * out coin values.
     *
     * From there on, we expand to 2 cell arrays, 3 cell arrays .. all the way to N cells, and use
     * previously calculated values to figure out maximum coins that can be earned. See referenced
     * youtube video for more details.
     *
     * Recurrence formula:
     * dp[i][j] - popping balloon at k, with k going from i .. j
     * then dp[i][j] at k is MAX of
     * dp[i][k-1] + coin pop at k + dp[k+1][j],
     * coin pop at k = balloon left of subarray (i-1) * nums[k] * balloon right of subarray (j+1)
     * ^^ Hold on to the max coin value, and remember the k index where this happens.
     */
    public static int maxCoins(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[][] dp = new int[nums.length][nums.length];

        for (int windowSize = 0; windowSize < nums.length; windowSize++) {
            for (int i = 0; i < (nums.length - windowSize); i++) {
                int j = i + windowSize;

                int leftBalloon = 1, rightBalloon = 1;
                if (i != 0) {
                    leftBalloon = nums[i-1];
                }

                if (j != nums.length - 1) {
                    rightBalloon = nums[j+1];
                }

                for (int k = i; k <= j; k++) {
                    // Left/right of boundary values are 0, since no coins for popping balloons
                    // outside valid indexes
                    int leftDp = (k == i) ? 0 : dp[i][k-1];
                    int rightDp = (k == j) ? 0 : dp[k+1][j];

                    int coinPop = leftBalloon * nums[k] * rightBalloon;
                    dp[i][j] = Math.max(leftDp + coinPop + rightDp, dp[i][j]);
                }
            }
        }

        return dp[0][nums.length-1];
    }
}
