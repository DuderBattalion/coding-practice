package com.misc.spiralarray;

import com.util.HelperMethods;

public class SpiralArray {

  public static void main(String[] args) {
    String[][] inputArr = {
        { "1", "2", "3", "4" },
        { "12", "13", "14", "5" },
        { "11", "16", "15", "6" },
        { "10", "9", "8", "7" }
    };

    PrintSpiralArray printSpiralArray = new PrintSpiralArray(inputArr);
    String result = printSpiralArray.run();

    System.out.println(result);
  }


}
