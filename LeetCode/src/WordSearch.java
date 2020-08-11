public class WordSearch {
  public static void main(String[] args) {
    char[][] board = {
        {'A','B','C','E'},
        {'S','F','C','S'},
        {'A','D','E','E'}
    };

    System.out.println(exist(board, "ASFCED"));
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
        isFound = findWord(board, i, j, word, "");
        if (isFound) {
          break;
        }
      }
    }

    return isFound;
  }

  private static boolean findWord(char[][] board, int i, int j, String word, String currWord) {
    if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
      return false;
    }

    currWord += board[i][j];
    if (currWord.length() == word.length()) {
      return currWord.equals(word);
    }

    // Go right
    boolean isFound = findWord(board, i, j + 1, word, currWord);
    if (isFound) {
      return true;
    }

    // Go down
    isFound = findWord(board, i + 1, j, word, currWord);
    if (isFound) {
      return true;
    }

    // Go left
    isFound = findWord(board, i, j - 1, word, currWord);
    if (isFound) {
      return true;
    }

    // Go up
    isFound = findWord(board, i - 1, j, word, currWord);
    return isFound;
  }
}
