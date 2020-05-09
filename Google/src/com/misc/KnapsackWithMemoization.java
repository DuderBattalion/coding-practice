package com.misc;

public class KnapsackWithMemoization {

  public static void main(String[] args) {
    int[] value = {6, 10, 12};
    int[] weight = {1, 2, 3};
    int W = 5;

    int[][] dp = new int[value.length + 1][W + 1];
    for (int i = 0; i <= value.length; i++) {
      for(int j = 0; j <= W; j++) {
        dp[i][j] = -1;
      }
    }

    int maxVal = calcKnapsack(value, weight, W, value.length - 1, dp);
    System.out.println(String.format("Max val: %s", maxVal));
  }

  private static int calcKnapsack(int[] value, int[] weight, int W, int i, int[][] dp) {
    // Base case
    if (i == 0) {
      return 0;
    }

    if (dp[i][W] != -1) {
      return dp[i][W];
    }

    if (weight[i] <= W) {
      dp[i][W] = Math.max(
          value[i] + calcKnapsack(value, weight, W - weight[i], i - 1, dp),
          dp[i-1][W]);
    } else {
      dp[i][W] = dp[i-1][W];
    }

    return dp[i][W];
  }
}
