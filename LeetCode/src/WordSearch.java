import java.util.HashSet;
import java.util.Set;

public class WordSearch {
  public static void main(String[] args) {
    char[][] board = {
        {'A','B','C','E'},
        {'S','F','C','S'},
        {'A','D','E','E'}
    };

    // System.out.println(exist(board, "ASFCED"));
    // System.out.println(exist(board, "ABCCED"));
    // System.out.println(exist(board, "SEE"));
    System.out.println(exist(board, "ABCB"));
  }

  public static boolean exist(char[][] board, String word) {
    int rows = board.length;
    if (rows == 0) {
      return false;
    }

    int cols = board[0].length;

    boolean isFound = false;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        isFound = findWord(board, i, j, word, "", new HashSet<>());
        if (isFound) {
          break;
        }
      }

      if (isFound) {
        break;
      }
    }

    return isFound;
  }

  private static boolean findWord(char[][] board, int i, int j, String word, String currWord,
                                  Set<String> visitedCells) {
    if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
      return false;
    }

    String cellKey = i + "," + j;
    if (visitedCells.contains(cellKey)) {
      return false;
    } else {
      visitedCells.add(cellKey);
    }

    currWord += board[i][j];
    if (currWord.length() == word.length()) {
      return currWord.equals(word);
    } else if (!word.contains(currWord)) {
      return false;
    }

    // Go right
    boolean isFound = findWord(board, i, j + 1, word, currWord, visitedCells);
    if (isFound) {
      return true;
    }

    // Go down
    isFound = findWord(board, i + 1, j, word, currWord, visitedCells);
    if (isFound) {
      return true;
    }

    // Go left
    isFound = findWord(board, i, j - 1, word, currWord, visitedCells);
    if (isFound) {
      return true;
    }

    // Go up
    isFound = findWord(board, i - 1, j, word, currWord, visitedCells);

    // Backtrack
    visitedCells.remove(cellKey);
    return isFound;
  }
}
