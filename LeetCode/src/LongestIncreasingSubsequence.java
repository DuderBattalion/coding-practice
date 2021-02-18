public class LongestIncreasingSubsequence {

    // LIS dp[i] = max(dp[j]), j = 0 to i, if num[i] > num[j]
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j]);
                }
            }

            dp[i] = max + 1;
        }

        int max = 0;
        for (int numSequence: dp) {
            max = Math.max(max, numSequence);
        }

        return max;
    }
}
