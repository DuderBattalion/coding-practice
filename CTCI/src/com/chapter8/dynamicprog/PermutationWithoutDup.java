package com.chapter8.dynamicprog;

import com.util.HelperMethods;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PermutationWithoutDup {

  private static String insertCharAtIndex(String seed, int index, char nextChar) {
    char[] seedChars = seed.toCharArray();
    StringBuilder result = new StringBuilder("");
    for (int i = 0; i < seedChars.length; i++) {
      if (index == i) {
        result.append(nextChar);
      }

      result.append(seedChars[i]);
    }

    return result.toString();
  }

  private static ArrayList<String> generatePermutation(String seed, char nextChar) {
    ArrayList<String> perms = new ArrayList<>();
    String perm = "";
    for (int i = 0; i < seed.length(); i++) {
      perm = insertCharAtIndex(seed, i, nextChar);
      perms.add(perm);
    }

    return perms;
  }

  public static ArrayList<String> generateAllPermutations(ArrayList<String> seedList, Queue<Character> nextChars) {
    if (nextChars.isEmpty()) {
      return seedList;
    }

    ArrayList<String> nextPerms = new ArrayList<>();
    char nextChar = nextChars.remove();
    for (String seed: seedList) {
      nextPerms.addAll(generatePermutation(seed, nextChar));
    }

    // seedList.addAll(nextPerms);
    return generateAllPermutations(nextPerms, nextChars);
  }

  public static void main(String[] args) {
    String rootString = "abc";
    char[] rootChars = rootString.toCharArray();

    ArrayList<String> seedList = new ArrayList<>();
    seedList.add(String.valueOf(rootChars[0]));

    Queue<Character> nextChars = new LinkedList<>();
    Boolean first = true;
    for (char rootChar: rootChars) {
      if (first) {
        first = false;
        continue;
      }

      nextChars.add(rootChar);
    }

    ArrayList<String> results = generateAllPermutations(seedList, nextChars);
    HelperMethods.printArrayList(results);
  }
}
