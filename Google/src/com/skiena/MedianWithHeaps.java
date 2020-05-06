package com.skiena;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianWithHeaps {

  public static void main(String[] args) {
    int[] numArr = { 5, 7, 1, 9, 2, 10, 4 }; // Sorted: 1, 2, 4, 5, 7, 9, 10, 12 Median: 5

    if (numArr.length == 0) {
      System.out.println("Empty array.");
      return;
    }

    if (numArr.length <= 2) {
      System.out.println("Median: " + numArr[numArr.length - 1]);
      return;
    }

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    for (int i = 0; i < (numArr.length/2); i++) {
      maxHeap.offer(numArr[i]);
    }

    for(int i = numArr.length/2; i < numArr.length; i++) {
      if(numArr[i] < maxHeap.peek()) {
        minHeap.offer(maxHeap.poll());
        maxHeap.offer(numArr[i]);
      } else {
        minHeap.offer(numArr[i]);
      }
    }

    System.out.println("Median: " + minHeap.peek());
  }
}
