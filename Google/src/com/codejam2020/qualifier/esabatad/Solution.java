package com.codejam2020.qualifier.esabatad;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    Solution solution = new Solution();

    // Debug messages into a file
    FileWriter fileWriter = null;
    try {
      fileWriter = new FileWriter("DebugLog.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }

    PrintWriter writer = new PrintWriter(fileWriter);

    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    int t = in.nextInt();
    int b = in.nextInt();
    in.nextLine();

    int[] result = new int[b];
    for (int i = 0; i < t; i++) {
      Arrays.fill(result, -1); // Default uninitialized value

      int position = 0;
      printLn(position + 1); // Position is 1 indexed, instead of 0 (as is for arrays)
      flushStdOut();

      result[position] = in.nextInt();
      in.nextLine();
      writer.print(result[position]); // DEBUG
      position++;
      ;

      for (int j = position; j < b; j++) {
        printLn(j + 1);
        flushStdOut();

        result[j] = in.nextInt();
        in.nextLine();
        writer.print(result[j]); // DEBUG
      }

      printArr(result);
      flushStdOut();

      String judgeSays = in.nextLine();
      if (judgeSays.equals("N")) {
        break;
      }

      writer.println();
    }

    writer.close();
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

    printLn("");
  }
}
