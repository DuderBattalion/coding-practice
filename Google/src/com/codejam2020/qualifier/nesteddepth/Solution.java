package com.codejam2020.qualifier.nesteddepth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    Solution app = new Solution();
    StringBuilder result = new StringBuilder();
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    int numTestCases = in.nextInt();
    in.nextLine();

    List<String> lines = new ArrayList<>();
    for (int i = 0; i < numTestCases; i++) {
      lines.add(in.nextLine());
    }

    String input = "";
    int delta = 0;
    for (int i = 0; i < numTestCases; i++) {
      result.append("Case #");
      result.append(i + 1);
      result.append(": ");

      input = lines.get(i);
      int[] nums = app.getNumArr(input);

      for (int j = 0; j < nums.length; j++) {
        delta = app.getDelta(nums, j);

        if (delta > 0) {
          app.printLeftBrackets(delta, result);
        } else {
          app.printRightBrackets(Math.abs(delta), result);
        }

        app.printNum(nums[j], result);
      }

      app.printRightBrackets(nums[nums.length - 1], result);

      System.out.println(result.toString());
      result.setLength(0);
    }
  }

  private int[] getNumArr(String line) {
    char[] chars = line.toCharArray();
    int[] numArr = new int[chars.length];
    for (int i =0; i < chars.length; i++) {
      numArr[i] = Character.getNumericValue(chars[i]);
    }

    return numArr;
  }

  private int getDelta(int[] nums, int index) {
    int delta;
    if (index == 0) {
      delta = nums[index];
    } else {
      delta = nums[index] - nums[index - 1];
    }

    return delta;
  }

  private void printLeftBrackets(int delta, StringBuilder result) {
    for (int i = 0; i < delta; i++) {
      result.append("(");
    }
  }

  private void printNum(int num, StringBuilder result) {
    result.append(num);
  }

  private void printRightBrackets(int delta, StringBuilder result) {
    for (int i = 0; i < delta; i++) {
      result.append(")");
    }
  }

  private void clearResult(StringBuilder result) {
    result.setLength(0);
  }
}
