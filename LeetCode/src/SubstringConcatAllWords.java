import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SubstringConcatAllWords {
  public static void main(String[] args) {
    String s = "barfoothefoobarmanfoo";
    String[] words = { "foo","bar", "man" };

    List<Integer> indices = findSubstring(s, words);
    indices.forEach(index -> {
      System.out.print(index + ",");
    });
  }

  public static List<Integer> findSubstring(String s, String[] words) {
    List<Integer> substrings = new ArrayList<>();
    if (s.isEmpty() || words.length == 0) {
      return substrings;
    }

    Arrays.sort(words);

    int tokenLength = words[0].length();
    List<String> strTokens = getTokens(s, tokenLength);

    int i = 0;
    List<String> substrTokens;
    while (i < strTokens.size() - (words.length - 1)) {
      substrTokens = getNTokens(i, strTokens, words.length); // TODO - implement cache
      Collections.sort(substrTokens);
      if (isTokensMatch(substrTokens, words)) {
        substrings.add(i * tokenLength);
      }

      i++;
    }

    return substrings;
  }

  private static boolean isTokensMatch(List<String> substrTokens, String[] words) {
    if (substrTokens.size() != words.length) {
      return false;
    }

    boolean isMatch = true;

    int i = 0;
    while (i < words.length) {
      if (!words[i].equals(substrTokens.get(i))) {
        isMatch = false;
        break;
      }

      i++;
    }

    return isMatch;
  }

  // TODO - Implement caching to eliminate doing the same list adds
  // Just need to drop the first char, and add to the last char
  // i.e Sliding window algorithm
  private static List<String> getNTokens(int start, List<String> strTokens, int numTokens) {
    List<String> tokens = new ArrayList<>();
    for (int i = 0; i < numTokens; i++) {
      tokens.add(strTokens.get(start));
      start++;

      if (start >= strTokens.size()) {
        break;
      }
    }

    return tokens;
  }

  private static List<String> getTokens(String s, int tokenLength) {
    List<String> tokens = new ArrayList<>();

    if (s.isEmpty()) {
      return tokens;
    }

    int i = 0;
    do {
      for (int j = 0; j < tokenLength; j++) {
        int end = i + tokenLength;
        if (end >= s.length()) {
          tokens.add(s.substring(i, s.length()));
          i = s.length(); // break while loop
          break;
        }

        tokens.add(s.substring(i, end));
        i += tokenLength;
      }
    } while (i < s.length());

    return tokens;
  }

}
