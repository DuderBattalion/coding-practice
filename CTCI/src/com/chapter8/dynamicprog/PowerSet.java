package com.chapter8.dynamicprog;

import com.util.HelperMethods;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// Question 8.4 - Return all subsets of a set
public class PowerSet {
  public static void printSubsets(ArrayList<String> set, Queue<String> nextChars) {
    if (nextChars.isEmpty()) {
      return;
    }

    String nextChar = nextChars.remove();
    Set<String> newSubsets = new HashSet<>();
    for (String member: set) {
      StringBuilder subsetStringBuf = new StringBuilder(member);
      subsetStringBuf.append(", ");
      subsetStringBuf.append(nextChar);

      String subsetString = subsetStringBuf.toString();
      HelperMethods.print(subsetString.toString());

      newSubsets.add(subsetString + ", ");
    }

    for (String newSubset: newSubsets) {
      set.add(newSubset);
    }

    printSubsets(set, nextChars);
  }

  public static void main(String[] args) {
    ArrayList<String> set = new ArrayList<>();
    set.add("a");

    Queue<String> nextChars = new LinkedList<>();
    nextChars.add("b");
    nextChars.add("c");

    printSubsets(set, nextChars);
  }
}
