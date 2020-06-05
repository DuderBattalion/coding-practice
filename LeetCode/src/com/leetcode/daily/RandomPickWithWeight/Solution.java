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

    int runningWeight = 0;
    for (int weight : w) {
      runningWeight += weight;
      pickIndexDistributor.add(runningWeight);
    }
  }

  /**
   * Randomly picks an index from the pickIndexDistributor.
   * Since the distributor already represents weight probability, a
   * random pick will represent similar weight distribution
   */
  public int pickIndex() {
    int randomIndex = random.nextInt(pickIndexDistributor.get(pickIndexDistributor.size() - 1));

    int pickIndex = 0;
    for (int i = 0; i < pickIndexDistributor.size(); i++) {
      if (randomIndex < pickIndexDistributor.get(i)) {
        pickIndex = i;
        break;
      }
    }

    return pickIndex;
  }
}
