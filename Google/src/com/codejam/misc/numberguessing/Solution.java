package com.codejam.misc.numberguessing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    int numTestCases = in.nextInt();

    int start;
    int end;
    int maxTries;
    int guess;
    for (int i = 0; i < numTestCases; i++) {
      start = in.nextInt();
      end = in.nextInt();
      maxTries = in.nextInt();



      boolean isSolved = false;
      for (int j = 0; j < maxTries; j++) {
        if (end < start) {
          throw new RuntimeException("Wires are crossed. This shouldn't happen.");
        }

        guess = (end - start) / 2;

        System.out.println(guess);
        System.out.flush();

        String judgeResponse = in.nextLine();
        if (judgeResponse.equals("TOO_SMALL")) {
          start++;
        } else if (judgeResponse.equals("TOO_BIG")) {
          end++;
        } else if (judgeResponse.equals("CORRECT")) {
          isSolved = true;
          break;
        } else if (judgeResponse.equals("WRONG_ANSWER")) {
          throw new RuntimeException("Wrong answer.");
        }
      }

      if (!isSolved) {
        throw new RuntimeException("Unsolved!");
      }
    }

  }
}
