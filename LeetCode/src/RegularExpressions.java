public class RegularExpressions {

  public static void main(String[] args) {
    String s = "aab";
    String p = "dc*a*b";

    System.out.println(isMatch(s, p));
  }

  public static boolean isMatch(String s, String p) {
    if (s.length() == 0 && p.length() == 0) {
      return true;
    }

    Character pChar = getLastChar(p);
    Character sChar = getLastChar(s);

    // CASE - SUBSTITUTE
    if (pChar != null && pChar == '.') {
      if (pChar == sChar) {
        return isMatch(s.substring(0, s.length() - 1), p.substring(0, p.length() - 1));
      } else {
        return false;
      }
    }

    // CASE - REPLACE
    if (pChar != null && pChar == '*') {
      char pNextChar = p.charAt(p.length() - 2);

      // If char match, keep pattern and shorten s string
      if (sChar != null && pNextChar == sChar) {
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

}
