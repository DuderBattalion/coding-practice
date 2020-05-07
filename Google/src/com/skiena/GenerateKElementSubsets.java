package com.skiena;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class GenerateKElementSubsets {

  public static void main(String[] args) {
    int[] numArr = { 1, 2, 3, 4, 5 };

    int k = 3;
    List<Set<Integer>> kElementSets = getAllKElementSets(numArr, k);
    for (Set<Integer> set: kElementSets) {
      set.stream().forEach(num -> System.out.print(num + ", "));
      System.out.println();
    }

    // TODO - Call generate all subsets algo from here
  }

  private static List<Set<Integer>> getAllKElementSets(int[] numArr, int k) {
    List<Set<Integer>> sets = new ArrayList<>();
    Queue<Integer> nums = new LinkedList<>();

    Arrays.stream(numArr).forEach(nums::offer);
    recurseGenKElemSubsets(nums, sets, new HashSet<>(), k);

    return sets;
  }

  private static void recurseGenKElemSubsets(Queue<Integer> nums, List<Set<Integer>> resultSet,
                                             Set<Integer> currSet, int k) {
    if (currSet.size() == k) {
      resultSet.add(currSet);
      return;
    }

    if (nums.isEmpty()) {
      return;
    }

    int num = nums.poll();

    // Case A: Skip adding this num to set
    recurseGenKElemSubsets(new LinkedList<Integer>(nums), resultSet, new HashSet<Integer>(currSet), k);

    // Case B: Include num in set
    currSet.add(num);
    recurseGenKElemSubsets(new LinkedList<Integer>(nums), resultSet, new HashSet<Integer>(currSet), k);
  }


}
