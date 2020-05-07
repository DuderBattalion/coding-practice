package com.skiena;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a denomination 1, 5, 10, 25 cents - calculate number of ways to make up n cents.
 */
public class DollarCombo {

  public static void main(String[] args) {
    int[] denominations = { 1, 5, 10, 25 };
    int target = 25;

    List<List<Integer>> results = new ArrayList<>();
    calcTotalCombinations(target, denominations, 0,
        new ArrayList<Integer>(), results);

    results.forEach((combo) -> {
      combo.forEach(num -> System.out.print(num + ", "));
      System.out.println();
    });
  }

  private static void calcTotalCombinations(int target, int[] denominations,
                                           int currTotal, List<Integer> currCombo,
                                           List<List<Integer>> results ) {
    if (currTotal == target) {
      results.add(currCombo);
      return;
    }

    if (currTotal > target) {
      return;
    }

    for (int denom: denominations) {
      List<Integer> newCombo = new ArrayList<>(currCombo);
      newCombo.add(denom);
      calcTotalCombinations(target, denominations, currTotal + denom, newCombo, results);
    }
  }
}
