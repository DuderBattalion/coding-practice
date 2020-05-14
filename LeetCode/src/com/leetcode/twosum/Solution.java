package com.leetcode.twosum;

import java.util.HashMap;
import java.util.Map;

public class Solution {
  public static void main(String[] args) {
    int[] nums = { 3, 2, 4 };
    int target = 6;

    int[] result = twoSum(nums, target);
    System.out.println(String.format("[%d, %d]", result[0], result[1]));
  }

  public static int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> numMap = new HashMap<Integer, Integer>();
    for (int i = 0; i < nums.length; i++) {
      numMap.put(nums[i], i);
    }

    int[] result = new int[2];
    for (int i = 0; i < nums.length; i++) {
      int diff = target - nums[i];
      int diffIndex = getIndex(diff, numMap);

      if (diffIndex >= 0 && diffIndex != i) {
        result[0] = i;
        result[1] = numMap.get(diff);

        break;
      }
    }

    return result;
  }

  private static int getIndex(int num, Map<Integer, Integer> numMap) {
    int index = -1;
    if (numMap.containsKey(num)) {
      index = numMap.get(num);
    }

    return index;
  }
}
