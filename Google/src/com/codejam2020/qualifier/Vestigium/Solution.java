package com.codejam2020.qualifier.Vestigium;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {

  public static void main(String[] args) {
    Solution app = new Solution();
    StringBuilder result = new StringBuilder();
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    int numTestCases = in.nextInt();
    in.nextLine();
    for (int i = 0; i < numTestCases; i++) {
      result.append("Case #" + (i + 1) + ": ");

      // N
      int matrixSize = in.nextInt();
      in.nextLine();
      if (matrixSize <= 0) {
        continue;
      }

      // 2D array
      Integer[][] arr;
      try {
        arr = app.build2DArray(in, matrixSize);
      } catch (Exception e) {
        e.printStackTrace();
        continue;
      }

      int k = app.calcTrace(arr);
      result.append(k + " ");

      int r = app.calcRepeatedRows(arr);
      result.append(r + " ");

      int c = app.calcRepeatedCols(arr);
      result.append(c + " " + "\n");
    }

    System.out.print(result.toString());
  }

  private Integer[][] build2DArray(Scanner in, int matrixSize) throws Exception {
    Integer[][] arr = new Integer[matrixSize][matrixSize];

    int arrRow = 0;
    String lineStr = "";
    String[] lineTokens;
    for (int i = 0; i < matrixSize; i++) {
      int j = 0;

      lineStr = in.nextLine();
      lineTokens = lineStr.split(" ");
      if (lineTokens.length <= 0) {
        throw new Exception("Invalid line format.");
      }

      for (String token: lineTokens) {
        arr[arrRow][j] = Integer.parseInt(token);
        j++;
      }

      arrRow++;
    }

    return arr;
  }

  private int calcTrace(Integer[][] arr) {
    int trace = 0;
    for (int i = 0; i < arr.length; i++) {
      trace += arr[i][i];
    }

    return trace;
  }

  private int calcRepeatedRows(Integer[][] arr) {
    int numRepeatedRows = 0;

    Set<Integer> rowNums = new HashSet<>();
    for (int i = 0; i < arr.length; i++) {
      boolean isRepeated = false;
      rowNums.clear();

      for (int j = 0; j < arr[i].length; j++) {
        if (rowNums.contains(arr[i][j])) {
          isRepeated = true;
          break;
        }

        rowNums.add(arr[i][j]);
      }

      if (isRepeated) {
        numRepeatedRows++;
      }
    }

    return numRepeatedRows;
  }

  private int calcRepeatedCols(Integer[][] arr) {
    int numRepeatedCols = 0;

    Set<Integer> rowCols = new HashSet<>();
    for (int j = 0; j < arr[0].length; j++) {
      boolean isRepeated = false;
      rowCols.clear();

      for (int i = 0; i < arr.length; i++) {
        if (rowCols.contains(arr[i][j])) {
          isRepeated = true;
          break;
        }

        rowCols.add(arr[i][j]);
      }

      if (isRepeated) {
        numRepeatedCols++;
      }
    }

    return numRepeatedCols;
  }
}
