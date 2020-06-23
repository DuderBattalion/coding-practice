import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SubstringConcatAllWords {
  public static void main(String[] args) {
//    String s = "barfoothefoobarfoo";
//    String[] words = { "foo","bar", "foo" };

    String s = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
    String[] words = { "fooo","barr","wing","ding","wing" };

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
//    List<String> strTokens = getTokens(s, tokenLength);

    int i = 0;
    List<String> substrTokens;
    while (i < s.length() - (words.length - 1)) {
      substrTokens = getNTokens(i, s, words.length, tokenLength); // TODO - implement cache

      Collections.sort(substrTokens);
      if (isTokensMatch(substrTokens, words)) {
        substrings.add(i);
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
  private static List<String> getNTokens(int start, String s, int numTokens, int tokenLength) {
    List<String> tokens = new ArrayList<>();
    if (start >= s.length()) {
      return tokens;
    }

    for (int i = 0; i < numTokens; i++) {
      if (start + tokenLength >= s.length()) {
        tokens.add(s.substring(start, s.length()));
        break;
      }

      tokens.add(s.substring(start, start + tokenLength));
      start += tokenLength;
    }

    return tokens;
  }

}
