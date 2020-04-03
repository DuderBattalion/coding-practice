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

  /**
   * Recurses through the 2D array doing 4 operations on repeat until it runs out
   * of operations.
   * Operation 1: Print N chars in row to the right
   * Operation 2: From there, print N- 1 chars in the column downwards
   * Operation 3: Print N - 1 chars in row to the left
   * Operation 4: Print N - 2 chars in the column upwards
   *
   * Repeating this pattern over and over again will complete the spiral. The
   * recursion terminates once all characters in a step have been printed.
   * @param startRow Starting row position for recursion pass
   * @param startCol Starting column position
   * @param numChars Number of characters expected to be printed in Operation 1
   */
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

  // Note: the printRow<Direction>() functions below can be further optimized into a single printChars()
  // function to eliminate some code redundancy, but with a trade-off having reduced readability. The
  // code as such is easier to read and reduces complexity with the minor trade-off of some extra lines
  // of code. This cab a good point of architectural discussion in a Pull Request with the team.

  /**
   * Prints a row, going right.
   * @return Returns the next array position to print.
   */
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
