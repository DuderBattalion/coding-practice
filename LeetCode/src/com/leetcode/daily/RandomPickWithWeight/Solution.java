package com.leetcode.daily.RandomPickWithWeight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Solution {
  // Creates a list that represents the probability of
  // an index being picked based on it's weight. Each index
  // of the distributor array stores a pickIndex to be called.
  List<Integer> pickIndexDistributor;

  Random random;

  public Solution(int[] w) {
    random = new Random();
    pickIndexDistributor = new ArrayList<>();

    for (int i = 0; i < w.length; i++) {
      for (int j = 0; j < w[i]; j++) {
       pickIndexDistributor.add(i);
      }
    }
  }

  /**
   * Randomly picks an index from the pickIndexDistributor.
   * Since the distributor already represents weight probability, a
   * random pick will represent similar weight distribution
   */
  public int pickIndex() {
    int randomIndex = random.nextInt(pickIndexDistributor.size());
    return pickIndexDistributor.get(randomIndex);
  }
}
