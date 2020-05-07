package com.skiena;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class GenerateAllSubsets {

  public static void main(String[] args) {
    int[] numArr = { 1, 2, 3, 4, 5 };

    List<BitSet> greyCode = generateGreyCode(numArr.length);
    StringBuilder set = new StringBuilder();
    for (BitSet bits: greyCode) {
      for (int i = 0; i < bits.length(); i++) {
        if (bits.get(i)) {
          set.append(numArr[i]);
          set.append(", ");
        }
      }

      String setStr = set.toString();
      if (setStr.length() > 0) {
        setStr = setStr.substring(0, setStr.length() - 2); // Remove trailing comma
      }

      System.out.println(setStr);
      set.setLength(0); // Clear buffer
    }
  }

  private static List<BitSet> generateGreyCode(int num) {
    List<BitSet> greyCode = new ArrayList<>();
    for (int i = 0; i < Math.pow(2, num); i++) {
      BitSet bit = createBitsFromInt(i);
      greyCode.add(bit);
    }

    return greyCode;
  }

  private static BitSet createBitsFromInt(int num) {
    return BitSet.valueOf(new long[] {num});
  }
}
