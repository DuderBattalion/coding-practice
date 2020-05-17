import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeaterChars {

  public static void main(String[] args) {
    // String text = "abcabcbb";
    // String text = "bbbbb";
    String text = "dvdf";

    System.out.println(String.format("Longest substr: %d", lengthOfLongestSubstring(text)));
  }

  public static int lengthOfLongestSubstring(String s) {
    int maxLen = 0;
    char[] sChars = s.toCharArray();

    Set<Character> charCache = new HashSet<>();
    int subStrLen = 0;
    for (char sChar: sChars) {
      if (charCache.contains(sChar)) {
        if (subStrLen > maxLen) {
          maxLen = subStrLen;
        }

        // Reset char cache and add current character
        charCache.clear();
        charCache.add(sChar);
        subStrLen = 1;
      } else {
        charCache.add(sChar);
        subStrLen++;
      }
    }

    // Check if last non-repeated sequence is the largest
    if (subStrLen > maxLen) {
      maxLen = subStrLen;
    }

    return maxLen;
  }

}
