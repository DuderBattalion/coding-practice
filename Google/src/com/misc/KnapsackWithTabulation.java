package com.misc;

public class KnapsackWithTabulation {

  public static void main(String[] args) {
    int[] value = {6, 10, 12};
    int[] weight = {1, 2, 3};
    int W = 5;

    int[][] dp = new int[value.length + 1][W + 1];

    // Base cases
    for (int i = 0; i <= value.length; i++) {
      dp[i][0] = 0;
    }

    for (int j = 0; j <= W; j++) {
      dp[0][j] = 0;
    }

    int maxVal = calcKnapsack(value, weight, W, dp);
    System.out.println(String.format("Max Value: %d", maxVal));
  }

  /**
   * Calculates DP cache array and returns final max value.
   * Note indexes for value[] and weight[] have to be reduced by 1 since
   * the item and wt loop starts with 1 instead of 0.
   */
  private static int calcKnapsack(int[] value, int[] wt, int W, int[][] dp) {
    for (int item = 1; item <= value.length; item++) {
      for (int weight = 1; weight <= W; weight++) {
        // If item weight exceeds capacity, then only option is
        // optimal (i - 1) item solution
        if (wt[item - 1] <= weight) {
          // Max of including element, or excluding element at this point
          dp[item][weight] = Math.max(value[item-1] + dp[item-1][weight - wt[item-1]],
              dp[item-1][weight]);
        } else {
          dp[item][weight] = dp[item - 1][weight];
        }
      }
    }

    printKnapsackArr(dp, value.length + 1, W + 1);

    return dp[value.length][W];
  }

  private static void printKnapsackArr(int[][] dp, int rows, int cols) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (dp[i][j] < 10) {
          System.out.print(dp[i][j] + "   ");
        } else if (dp[i][j] < 100){
          System.out.print(dp[i][j] + "  ");
        } else {
          System.out.print(dp[i][j] + " ");
        }

      }

      System.out.println();
    }
  }
}
