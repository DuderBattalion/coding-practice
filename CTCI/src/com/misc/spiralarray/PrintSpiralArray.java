package com.misc.spiralarray;

public class PrintSpiralArray {
  String[][] spiralArr;
  StringBuilder result;
  final static String RESULT_SEP  = ", ";

  public PrintSpiralArray(String[][] inputArr) {
    spiralArr = inputArr;
    result = new StringBuilder();
  }

  public String run() {
    recurseSpiralArray(0, 0, 4);
    return result.toString()
        .substring(0, result.length() - RESULT_SEP.length()); // Exclude trailing separator
  }

  private void recurseSpiralArray(int startRow, int startCol,
                                  int numChars) {
    ArrayPosition nextPosition = printRowRight(startRow, startCol, numChars);
    nextPosition = printColDown(nextPosition.row, nextPosition.col, numChars - 1);
    nextPosition = printRowLeft(nextPosition.row, nextPosition.col, numChars - 1);
    nextPosition = printColUp(nextPosition.row, nextPosition.col, numChars - 2);

    if (nextPosition != null) {
      recurseSpiralArray(nextPosition.row, nextPosition.col, numChars - 2);
    }
  }

  private ArrayPosition printRowRight(int row, int col, int numChars) {
    if (numChars <= 0) {
      return null; // End recursion
    }

    for (int j = 0; j < numChars; j++) {
      result.append(this.spiralArr[row][col]);
      result.append(RESULT_SEP);

      col++;
    }

    col--; // The last col++ call is not needed
    return new ArrayPosition(row + 1, col);
  }

  private ArrayPosition printColDown(int row, int col, int numChars) {
    if (numChars <= 0) {
      return null;
    }

    for (int j = 0; j < numChars; j++) {
      result.append(this.spiralArr[row][col]);
      result.append(RESULT_SEP);

      row++;
    }

    row--; // The last row-- call is not needed
    return new ArrayPosition(row, col - 1);
  }

  private ArrayPosition printRowLeft(int row, int col, int numChars) {
    if (numChars <= 0) {
      return null;
    }

    for (int j = 0; j < numChars; j++) {
      result.append(this.spiralArr[row][col]);
      result.append(RESULT_SEP);

      col--;
    }

    col++;
    return new ArrayPosition(row - 1, col);
  }

  private ArrayPosition printColUp(int row, int col, int numChars) {
    if (numChars <= 0) {
      return null;
    }

    StringBuilder result = new StringBuilder();
    for (int j = 0; j < numChars; j++) {
      result.append(this.spiralArr[row][col]);
      result.append(RESULT_SEP);

      row--;
    }

    row++;
    return new ArrayPosition(row, col + 1);
  }
}
