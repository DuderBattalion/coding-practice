public class WildcardMatching {
    public static void main(String[] args) {
//        System.out.println(isMatch("adceb", "a*b"));
        System.out.println(isMatch("acdcb", "a*c?cb"));
    }

    public static boolean isMatch(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        Character sChar = s.isEmpty() ? null : s.charAt(0);
        char pChar = p.charAt(0);

        if (pChar == '*') {
            if (s.isEmpty()) {
                return isMatch(s, snipString(p));
            } else {
                // Either use the wildcard, or ignore it
                return (isMatch(snipString(s), p) || isMatch(s, snipString(p)));
            }
        } else if (pChar == '?' || (sChar != null && pChar == sChar)) {
            return isMatch(snipString(s), snipString(p));
        } else {
            return false;
        }
    }

    /**
     * Snips the first character of a string and returns the snipped string.
     * If string is empty or null, returns empty string.
     */
    private static String snipString(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        return s.substring(1, s.length());
    }
}
