public class RegularExpressions {

  public static void main(String[] args) {
    String s = "aaa";
    String p = "ab*a*c*a";

    System.out.println(isMatch(s, p));
  }

  public static boolean isMatch(String s, String p) {
    if (s.length() == 0) {
      if (p.length() == 0) {
        return true;  
      }
      
      return processRemainingPattern(p);
    }

    Character pChar = getLastChar(p);
    Character sChar = getLastChar(s);

    // CASE - SUBSTITUTE
    if (pChar != null && pChar == '.') {
      return isMatch(s.substring(0, s.length() - 1), p.substring(0, p.length() - 1));
    }

    // CASE - REPLACE
    if (pChar != null && pChar == '*') {
      char pNextChar = p.charAt(p.length() - 2);

      // If char match, keep pattern and shorten s string
      if (sChar != null && (pNextChar == sChar || pNextChar == '.')) {
        return isMatch(s.substring(0, s.length() - 1), p);
      }
      // No match - remove pattern and move on
      else {
        return isMatch(s, p.substring(0, p.length() - 2)); // - 2 because removing 'a*'
      }
    }

    // CASE - CHARACTER MATCH
    if (pChar == sChar) {
      return isMatch(s.substring(0, s.length() - 1), p.substring(0, p.length() - 1));
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

  /**
   * Analyse case:
   * s = aaa
   * p = ab*a*c*a
   * which empties out the s, while still leaving a valid pattern behind
   */
  private static boolean processRemainingPattern(String p) {
    if (p.length() < 2) {
      return false;
    }

    boolean isMatch = true;
    while (!p.isEmpty()) {
      if
    }
  }

}
