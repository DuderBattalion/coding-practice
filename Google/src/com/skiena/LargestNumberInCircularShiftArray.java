package com.skiena;

/**
 * Find largest number in circularly shifted array (in increasing order) in O(lg n).
 */
public class LargestNumberInCircularShiftArray {

  public static void main(String[] args) {
    int[] numArr = {27, 29, 35, 42, 5, 15};

    int start = 0;
    int end = numArr.length - 1;

    int largestNum = findLargestNum(numArr, start, end);
    System.out.println("Largest num: " + largestNum);
  }

  /**
   * Recursive modification of binary search to find largest number in
   * circularly shifted array.
   */
  private static int findLargestNum(int[] numArr, int start, int end) {
    if (start == end) {
      return numArr[start];
    } else if (start > end) {
      throw new RuntimeException(String.format("Error: start(%d) > end(%d).", start, end));
    }

    int mid = start + (end - start)/2;
    if (mid == 0) {
      return numArr[mid];
    }

    if (numArr[mid - 1] < numArr[mid] && numArr[mid + 1] < numArr[mid]) {
      return numArr[mid];
    }

    return Math.max(findLargestNum(numArr, start, end - 1),
        findLargestNum(numArr, start + 1, end));
  }
}
