import java.util.HashMap;
import java.util.Map;

public class WildcardMatching {
    public static void main(String[] args) {
//        System.out.println(isMatch("adceb", "a*b"));
//        System.out.println(isMatch("acdcb", "a*c?cb"));
//        System.out.println(isMatch("", "?"));
        System.out.println(isMatch("aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba",
                "*****b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*"));

    }

    public static boolean isMatch(String s, String p) {
        return isMatch(s, p, new HashMap<String, Boolean>());
    }

    public static boolean isMatch(String s, String p, Map<String, Boolean> cache) {
        String key = s + "," + p;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        //        System.out.println(String.format("s: %s, p: %s", s, p));

        if (p.isEmpty()) {
            return s.isEmpty();
        }

        Character sChar = s.isEmpty() ? null : s.charAt(0);
        char pChar = p.charAt(0);

        boolean matchResult;
        if (pChar == '*') {
            if (s.isEmpty()) {
                matchResult = isMatch(s, snipString(p), cache);
            } else {
                // Either use the wildcard, or ignore it
                matchResult = (isMatch(snipString(s), p, cache) || isMatch(s, snipString(p), cache));
            }
        } else if (sChar != null && (pChar == '?' || pChar == sChar)) {
            matchResult = isMatch(snipString(s), snipString(p), cache);
        } else {
            matchResult = false;
        }

        cache.put(key, matchResult);
        return matchResult;
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
