package com.codejam2020.qualifier.esabatad;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
  private static final int DEFAULT_INIT_BIT = -1;

  public static void main(String[] args) {
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    int t = in.nextInt();
    int b = in.nextInt();
    in.nextLine();

    int[] result = new int[b];
    for (int i = 0; i < t; i++) {
      resetArr(result);

      int position = 0;
      printLn(position + 1); // Position is 1 indexed, instead of 0 (as for arrays)

      // result[position] = readInt(in);
      // position++;
      //
      // for (int j = position; j < b; j++) {
      //   printLn(j + 1);
      //   result[j] = readInt(in);
      // }


      printArr(result);

      String judgeSays = in.nextLine();
      if (judgeSays.equals("N")) {
        break;
      }
    }
  }

  private static void resetArr(int[] arr) {
    Arrays.fill(arr, DEFAULT_INIT_BIT); // Default uninitialized value
  }

  private static String getNumBits(int[] bits, Scanner in, int num, int start) {
    int position = start;
    printLn(position + 1); // Position is 1 indexed, instead of 0 (as for arrays)

    bits[position] = readInt(in);
    position++;

    for (int j = position; j < num; j++) {
      printLn(j + 1);
      bits[j] = readInt(in);
    }

    StringBuilder result = new StringBuilder();
    Arrays.stream(bits).forEach(result::append);

    return result.toString();
  }

  private static<T> void printLn(T msg) {
    System.out.println(msg.toString());
    flushStdOut();
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

    printLn("");
    flushStdOut();
  }

  private static int readInt(Scanner in) {
    int token = in.nextInt();
    in.nextLine();

    return token;
  }
}
