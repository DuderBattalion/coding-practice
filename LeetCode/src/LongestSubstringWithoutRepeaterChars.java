import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

    //  <K,V> = <Char, Previous index>
    Map<Character, Integer> charCache = new HashMap<>();
    int subStrLen = 0;
    int i = 0;

    while (i < sChars.length) {
      if (charCache.containsKey(sChars[i])) {
        if (subStrLen > maxLen) {
          maxLen = subStrLen;
        }

        // Rewind back to index where repeated char was encountered + 1
        // Example string: "dvdf", expected output: 3 (vdf)
        i = charCache.get(sChars[i]) + 1;
        subStrLen = 0;
        charCache.clear();
      } else {
        charCache.put(sChars[i], i);
        subStrLen++;
        i++;
      }
    }

    // Check if last non-repeated sequence is the largest
    if (subStrLen > maxLen) {
      maxLen = subStrLen;
    }

    return maxLen;
  }

}
