public class LongestPalindromicSubstring {

  public static void main(String[] args) {
    String text = "cbbd";
    System.out.println(String.format("Result: %s", longestPalindrome(text)));
  }

  public static String longestPalindrome(String s) {
    String longestSubStr = "";

    int subStrLen = s.length();
    String subStr = "";
    int start;
    int end;

    while (subStrLen > 0) {
      start = 0;
      end = start + subStrLen;
      while (end <= s.length()) {
        subStr = s.substring(start, end);
        if (isPalindrome(subStr)) {
          longestSubStr = subStr;
          break;
        }

        start++;
        end++;
      }

      if (longestSubStr.length() > 0) {
        break;
      }

      subStrLen--;
    }

    return longestSubStr;
  }

  private static boolean isPalindrome(String s) {
    String sReverse = new StringBuilder(s).reverse().toString();
    return s.equals(sReverse);
  }
}
