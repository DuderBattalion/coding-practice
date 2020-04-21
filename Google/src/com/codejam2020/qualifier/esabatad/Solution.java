package com.codejam2020.qualifier.esabatad;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    Solution solution = new Solution();

    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    int t = in.nextInt();
    int b = in.nextInt();

    int[] result = new int[b];
    Arrays.fill(result, -1); // Default uninitialized value

    int position = 0;
    printLn(position + 1); // Position is 1 indexed, instead of 0 (as is for arrays)
    flushStdOut();

    result[position] = in.nextInt();
    position++;

    for (int i = position; i < b; i++) {
      printLn(i + 1);
      flushStdOut();

      result[i] = in.nextInt();
    }

    printArr(result);
  }

  private static<T> void printLn(T msg) {
    System.out.println(msg.toString());
  }

  private static<T> void print(T msg) {
    System.out.print(msg.toString());
  }

  private static void flushStdOut() {
    System.out.flush();
  }

  private static void printArr(int[] bits) {
    for (int bit: bits) {
      print(bit);
    }
  }
}
