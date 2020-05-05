package com.skiena;

import java.util.Collections;
import java.util.PriorityQueue;

public class SmallestKNumbers {

  public static void main(String[] args) {
    int[] numArr = { 3, 7, 6, 1, 5, 8, 2};
    int k = 4;

    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    for (int num: numArr) {
      if (maxHeap.size() < k) {
        maxHeap.offer(num);
      } else {
        if (num < maxHeap.peek()) {
          maxHeap.remove();
          maxHeap.offer(num);
        }
      }
    }

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < k; i++) {
      result.append(maxHeap.remove());
      result.append(" ");
    }

    System.out.println(
        String.format("Smallest K numbers: %s", result.toString()));
  }
}
