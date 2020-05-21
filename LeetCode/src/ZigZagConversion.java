import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion {

  public static void main(String[] args) {
    String text = "PAYPALISHIRING";
    // String text = "ABCD";
    System.out.println(convert(text, 4));
  }

  public static String convert(String s, int numRows) {
    if (numRows == 1) {
      return s;
    }

    List<List<Character>> skipLists = createSkipLists(numRows);

    // Block of text to be processed in one pass
    // numRows chars down, numRows - 2 chars diagonal
    int blockSize = numRows + (numRows - 2);
    char[] sChars = s.toCharArray();

    int start = 0;
    int end = blockSize;
    if (end > s.length()) {
      end = s.length();
    }

    String block;
    while (start < sChars.length) {
      block = s.substring(start, end);
      block = padBlock(block, blockSize);
      fillSkipLists(skipLists, block.toCharArray());

      start += blockSize;
      end = start + blockSize;
      if (end > s.length()) {
        end = s.length();
      }
    }

    return reduceSkipLists(skipLists);
  }

  private static List<List<Character>> createSkipLists(int numLists) {
    List<List<Character>> skipLists = new ArrayList<>();

    for (int i = 0; i < numLists; i++) {
      List<Character> skipList = new ArrayList<>();
      skipLists.add(skipList);
    }

    return skipLists;
  }

  /**
   * Pads block with $ if block is less than expected size
   */
  private static String padBlock(String block, int blockSize) {
    StringBuilder padding = new StringBuilder();
    if (block.length() < blockSize) {
      int diff = blockSize - block.length();
      for (int i = 0; i < diff; i++) {
        padding.append("$");
      }
    }

    padding.append("$"); // One extra at the end of the block needed
    return block + padding.toString();
  }

  private static void fillSkipLists(List<List<Character>> skipLists, char[] block) {
    int skipListIndex = 0;
    int start = 0;
    int end = block.length - 1;

    List<Character> skipList;
    while(start < block.length && start <= end) {
      skipList = skipLists.get(skipListIndex);

      // Ignore sentinel
      if (block[start] != '$') {
        skipList.add(block[start]);
      }

      if (start != end && block[end] != '$') {
        skipList.add(block[end]);
      }

      skipListIndex++;
      start++;
      end--;
    }
  }

  private static String reduceSkipLists(List<List<Character>> skipLists) {
    StringBuilder result = new StringBuilder();
    for (List<Character> skipList: skipLists) {
      for (Character character: skipList) {
        result.append(String.valueOf(character));
      }
    }

    return result.toString();
  }
}
