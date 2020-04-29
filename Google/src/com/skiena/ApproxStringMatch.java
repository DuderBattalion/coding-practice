package com.skiena;

import javafx.util.Pair;

import java.util.Deque;
import java.util.LinkedList;

public class ApproxStringMatch {

  public static void main(String[] args) {
    String source = "Thou shalt not";
    String target = "You should not";

    // Base case is " " - add to source and target
    source = " " + source;
    target = " " + target;

    int[][] cache = calcEditDistance(source, target);

    System.out.println("Min cost = " + cache[source.length() - 1][target.length() - 1]);

    // DEBUG
    printCache(cache, source.length(), target.length());

    String reconstructedOps = getReconstructedOps(cache, source.length(), target.length());
    System.out.println("Operations: " + reconstructedOps);


  }

  private static int[][] calcEditDistance(String source, String target) {
    char[] sourceChars = source.toCharArray();
    char[] targetChars = target.toCharArray();

    int match = 0;
    int insert = 0;
    int delete = 0;
    int minCost = 0;

    int[][] cache = createCache(sourceChars.length, targetChars.length);
    for (int i = 1; i < sourceChars.length; i++) {
      for (int j = 1; j < targetChars.length; j++) {
        // Remember how char pointers are moving in source and target in prev iteration
        // which determine where cache value is coming from
        match = cache[i-1][j-1] + getMatchCost(sourceChars[i], targetChars[j]);
        insert = cache[i][j-1] + 1;
        delete = cache[i-1][j] + 1;
        minCost = getMinCost(match, insert, delete);

        cache[i][j] = minCost;
      }
    }

    return cache;
  }

  /**
   * Creates a cache and pre-seeds cache for base case - " " strings
   */
  private static int[][] createCache(int sourceLength, int targetLength) {
    int[][] cache = new int[sourceLength][targetLength];

    // Pre-seed cache for base case - source = empty string
    // Converting " " to target would require target.length changes
    for (int i = 0; i < sourceLength; i++) {
      cache[i][0] = i;
    }

    // Pre-seed cache for base case - target = empty string
    // Converting " " to source would require target.length changes
    for (int i = 0; i < targetLength; i++) {
      cache[0][i] = i;
    }

    return cache;
  }

  /**
   * If source and target chars match, then cost = 0. Else cost = 1
   */
  private static int getMatchCost(char source, char target) {
    return source == target ? 0 : 1;
  }

  private static int getMinCost(int match, int insert, int delete) {
    int minCost;
    minCost = match;
    if (insert < minCost) {
      minCost = insert;
    }

    if (delete < minCost) {
      minCost = delete;
    }
    return minCost;
  }

  /**
   * Helper function. Prints the contents of the cache.
   */
  private static void printCache(int[][] cache, int sourceLength, int targetLength) {
    System.out.println("=======Cache======");
    final String SPACE = " ";
    final String DBL_SPACE = "  ";
    for (int i = 0; i < sourceLength; i++) {
      for (int j = 0; j < targetLength; j++) {
        if (cache[i][j] < 10) {
          System.out.print(cache[i][j] + DBL_SPACE);
        } else {
          System.out.print(cache[i][j] + SPACE);
        }
      }

      System.out.println();
    }
  }

  private static String getReconstructedOps(int[][] cache, int sourceLength, int targetLength) {
    Deque<Character> reconstructPath = new LinkedList<>();
    int i = sourceLength - 1;
    int j = targetLength - 1;

    int match, insert, delete, nextI, nextJ;
    while (i != 0 && j != 0) {
      match = cache[i-1][j-1];
      insert = cache[i][j-1];
      delete = cache[i-1][j];

      char minOp = getMinOp(match, insert, delete);
      reconstructPath.push(minOp);

      Pair<Integer, Integer> nextPos = getNextPosition(i, j, minOp);
      i = nextPos.getKey();
      j = nextPos.getValue();
    }

    StringBuilder result = new StringBuilder();
    while (!reconstructPath.isEmpty()) {
      result.append(reconstructPath.removeLast());
    }

    return result.toString();
  }

  // TODO - Refactor this into getMinCost
  private static char getMinOp(int match, int insert, int delete) {
    char op = 'M';
    int minCost = match;

    if (insert < minCost) {
      op = 'I';
      minCost = insert;
    }

    if (delete < minCost) {
      op = 'D';
      minCost = delete;
    }

    return op;
  }

  private static Pair<Integer, Integer> getNextPosition(int i, int j, int minOp) {
    if (minOp == 'M') {
      i = i - 1;
      j = j - 1;
    } else if (minOp == 'I') {
      j = i - 1;
    } else if (minOp == 'D') {
      i = i - 1;
    } else {
      throw new RuntimeException("[Error]: Invalid operation.");
    }

    if (i < 0) {
      i = 0;
    }

    if (j < 0) {
      j = 0;
    }

    System.out.println(String.format("i: %d, j: %d", i, j)); // TODO - debug remove
    return new Pair<>(i, j);
  }
}
