package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sun.tools.javac.jvm.ByteCodes.pop;

public class ThreeSum {

  public static void main(String[] args) {
    int[] nums = { -1, 0, 1, 2, -1, -4 };
    List<List<Integer>> results = threeSum(nums);

    StringBuffer output = new StringBuffer();
    for (List<Integer> result: results) {
      output.setLength(0);
      for (int num: result) {
        output.append(num);
        output.append(", ");
      }

      System.out.println(output.substring(0, output.length() - 1).toString());
    }
  }

  public static List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> results = new ArrayList<>();

    Map<Integer, Integer> numCountMap = createNumCountMap(nums);
    List<NumPair> numPairs = getAllNumPairs(nums);

    Integer a, b, c;
    for (NumPair pair: numPairs) {
      a = pop(numCountMap, pair.first);
      b = pop(numCountMap, pair.second);

      if (a == null || b == null) {
        throw new RuntimeException("Error: NumPair should not be null.");
      }

      c = -(a + b);

      if (numCountMap.containsKey(c)) {
        List<Integer> newResult = new ArrayList<>();
        newResult.add(a);
        newResult.add(b);
        newResult.add(c);

        results.add(newResult);
      }

      push(numCountMap, a);
      push(numCountMap, b);
    }

    return results;
  }

  private static Map<Integer, Integer> createNumCountMap(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int num: nums) {
      if (map.containsKey(num)) {
        int count = map.get(num);
        map.put(num, count + 1);
      } else {
        map.put(num, 1);
      }
    }

    return map;
  }

  private static class NumPair {
    public int first;
    public int second;

    public NumPair(int first, int second) {
      this.first = first;
      this.second = second;
    }
  }

  private static List<NumPair> getAllNumPairs(int[] nums) {
    List<NumPair> pairs = new ArrayList<>();

    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        NumPair pair = new NumPair(nums[i], nums[j]);
        pairs.add(pair);
      }
    }

    return pairs;
  }

  private static Integer pop(Map<Integer, Integer> numCountMap, int num) {
    Integer result = null;

    if (numCountMap.containsKey(num)) {
      result = num;

      int count = numCountMap.get(num);
      count--;

      if (count < 0) {
        throw new RuntimeException(
            String.format("Error: NumcountMap in invalid state. %d count is %d", num, result));
      }

      if (count == 0) {
        numCountMap.remove(num);
      }
    }

    return num;
  }

  private static void push(Map<Integer, Integer> numCountMap, int num) {
    if (numCountMap.containsKey(num)) {
      int count = numCountMap.get(num);
      numCountMap.put(num, count + 1);
    } else {
      numCountMap.put(num, 1);
    }
  }

}
