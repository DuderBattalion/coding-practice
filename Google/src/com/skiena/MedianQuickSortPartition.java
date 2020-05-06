package com.skiena;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Find the median element of an array using the partitioning philosophy found in
 * Quicksort. Algorithm should run in expected O(n) time.
 * See: http://www.algorist.com/algowiki/index.php/TADM2E_4.16
 */
public class MedianQuickSortPartition {
  public static void main(String[] args) {
    int[] numArr = { 5, 7, 1, 9, 2, 10, 4, 12 }; // Sorted: 1, 2, 4, 5, 7, 9, 10, 12 Median: 5

    int pivot = 0;
    Random random = new Random();

    List<Integer> arr = new ArrayList<>();
    List<Integer> leftArr = new ArrayList<>();
    List<Integer> rightArr = new ArrayList<>();

    Arrays.stream(numArr).forEach(arr::add);

    int numsToMedianRight = (int) (Math.ceil(arr.size()/2.0) - 1);
    int median = findMedian(arr, numsToMedianRight);
    System.out.println(String.format("Median: %d", median));
  }

  private static int findMedian(List<Integer> arr, int numsToRight) {
    int pivot = getRandomIndex(arr.size());
    SplitArrayStruct splitArrayStruct = splitArr(pivot, arr);

    int median;
    if (splitArrayStruct.rightArr.size() == numsToRight) {
      median = arr.get(pivot - 1);
    } else if (splitArrayStruct.rightArr.size() > numsToRight) {
      median = findMedian(splitArrayStruct.rightArr, numsToRight);
    } else {
      median = findMedian(splitArrayStruct.leftArr,
          numsToRight - splitArrayStruct.rightArr.size());
    }

    return median;
  }

  /**
   * Helper data structure to hold left and right arrays
   * that are formed after splitting at pivot index - similar to quicksort
   */
  private static class SplitArrayStruct {
    public List<Integer> leftArr;
    public List<Integer> rightArr;

    public SplitArrayStruct() {
      leftArr = new ArrayList<>();
      rightArr = new ArrayList<>();
    }
  }

  /**
   * Splits the num array around a pivot such that the left array has nums less
   * than equal to the pivot, and right arr has nums greater than the pivot.
   */
  private static SplitArrayStruct splitArr(int pivot, List<Integer> arr) {
    SplitArrayStruct splitArrayStruct = new SplitArrayStruct();

    if (pivot < 0 || pivot >= arr.size()) {
      return splitArrayStruct;
    }

    int pivotNum = arr.get(pivot);
    arr.stream().forEach(num -> {
      if (num < pivotNum) {
        splitArrayStruct.leftArr.add(num);
      } else {
        splitArrayStruct.rightArr.add(num);
      }
    });

    return splitArrayStruct;
  }

  private static int getRandomIndex(int max) {
    // TODO - ideally this should be a class member, only init once
    Random random = new Random();
    return random.nextInt(max);
  }

}
