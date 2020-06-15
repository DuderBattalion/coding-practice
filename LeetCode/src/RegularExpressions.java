public class RegularExpressions {

  public static void main(String[] args) {
    // String s = "aaa";
    // String p = "ab*a*c*a";

    String s = "a";
    String p = ".*..a*";

    System.out.println(isMatch(s, p));
  }

  public static boolean isMatch(String s, String p) {
    return isMatch(s, p, null);
  }

  public static boolean isMatch(String s, String p, Character lastWildcardChar) {
    if (s.length() == 0 && p.length() == 0) {
      return true;
    }

    Character pChar = getLastChar(p);
    Character sChar = getLastChar(s);

    // CASE - SUBSTITUTE
    if (pChar != null && pChar == '.') {
      s = (s.isEmpty() ? s : s.substring(0, s.length() - 1));
      return isMatch(s, p.substring(0, p.length() - 1), null);
    }

    // CASE - REPLACE
    if (pChar != null && pChar == '*') {
      char pNextChar = p.charAt(p.length() - 2);

      // If char match, keep pattern and shorten s string
      if (sChar != null && (pNextChar == sChar || pNextChar == '.')) {
        return isMatch(s.substring(0, s.length() - 1), p, sChar);
      }
      // No match - remove pattern and move on
      else {
        return isMatch(s, p.substring(0, p.length() - 2), lastWildcardChar); // - 2 because removing 'a*'
      }
    }

    // CASE - CHARACTER MATCH
    if (pChar == sChar) {
      return isMatch(s.substring(0, s.length() - 1), p.substring(0, p.length() - 1), null);
    }
    // Analyze case:
    // s = aaa
    // p = ab*a*c*a
    // p remains after s is exhausted, needing extra processing
    else if (sChar == null && pChar == lastWildcardChar) {
      return isMatch(s, p.substring(0, p.length() - 1), null);
    } else {
      return false;
    }
  }

  private static Character getLastChar(String s) {
    if (s.length() == 0) {
      return null;
    }

    return s.charAt(s.length() - 1);
  }
}
