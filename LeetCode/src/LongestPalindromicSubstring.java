import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LongestPalindromicSubstring {

  public static void main(String[] args) {
    // String text = "babad";
    // String text = "cbbd";
    // String text = "ccc";
    // String text = "ivianyna";
    String text = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";

    System.out.println(String.format("Result: %s", longestPalindrome(text)));
  }

  /**
   * Dynamic programming solution:
   * P(X, Y) = Is Palindrome from position X to Y (true/false)
   * S(i) = String at index i
   * P(i, j) = P(i + 1, j - 1) && S(i) == S(j)
   *
   * Build out solution from base case.
   */
  public static String longestPalindrome(String s) {
    Instant start = Instant.now();

    if (s.isEmpty()) {
      return s;
    }

    List<List<Boolean>> cache = new ArrayList<>();
    String longestPalindrome = initCache(cache, s);

    // Recurrence relation: P(i, j) = (S(i) == S(j)) && (P(i+1, j- 1))
    boolean isPal;
    for (int k = 2; k < s.length(); k++) { // length of palindrome
      cache.add(new ArrayList<Boolean>());
      for (int i = 0; i < s.length() - k; i++) {
        isPal = isPalindrome(cache, k, i, s);
        if (isPal && k > longestPalindrome.length() - 1) { // -1 because k is zero-indexed
          longestPalindrome = s.substring(i, i + k + 1); // +1 because substring is end-exclusive
        }

        cache.get(k).add(isPal);
      }
    }

    Instant end = Instant.now();
    Duration timeElapsed = Duration.between(start, end);
    System.out.println("Perf time: " + timeElapsed.toMillis() + "ms");

    return longestPalindrome;
  }

  private static String initCache(List<List<Boolean>> cache, String s) {
    if (s.isEmpty()) {
      return s;
    }

    String longestPal = String.valueOf(s.charAt(0));
    List<Boolean> oneChars = new ArrayList<>();
    List<Boolean> twoChars = new ArrayList<>();
    for (int i = 0; i < s.length() - 1; i++) {
      // 1 char tokens are always palindromes
      oneChars.add(true);

      // 2 char tokens are palindromes if S(i) == S(i+1)
      boolean isPal = s.charAt(i) == s.charAt(i+1);
      twoChars.add(isPal);
      if (isPal && longestPal.length() < 2) {
        longestPal = String.valueOf(s.charAt(i)) + String.valueOf(s.charAt(i+1));
      }
    }

    cache.add(oneChars);
    cache.add(twoChars);

    return longestPal;
  }

  // k = cacheIndex - and is zero-indexed
  private static boolean isPalindrome(List<List<Boolean>> cache, int k, int i, String s) {
    boolean isEndCharsSame = (s.charAt(i) == s.charAt(i + k));
    if (!isEndCharsSame) {
      return false;
    }

    if (k - 1 > cache.size()) {
      throw new RuntimeException(
          String.format("[Error]: k is more than L1 cache size. k: %d, L1 size: %d",
              k, cache.size() - 1));
    }

    List<Boolean> palList = cache.get(k - 2);
    if (i + 1 > palList.size() - 1) {
      throw new RuntimeException(
          String.format("[Error]: i is more than L2 cache size. i: %d, L2 size: %d",
              i, palList.size()));
    }

    // Is inner string a palindrome? starts at i+1
   return palList.get(i+1);
  }


}
