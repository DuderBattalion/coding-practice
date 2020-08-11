import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
//        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
//        System.out.println(minWindow("ADOBECODEBANCEEFFG", "ABC"));
//        System.out.println(minWindow("AA", "BCDG"));
//        System.out.println(minWindow("a", "a"));
        System.out.println(minWindow("", ""));
//        System.out.println(minWindow("EBANCE", "ABC"));
    }

    public static String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty() || s.length() < t.length()) {
            return "";
        }

        int start = 0, end = t.length() - 1, minWindowLength = Integer.MAX_VALUE;
        String minWindowStr = "";

        Map<Character, Integer> targetChars = getCharCounts(t.toCharArray());
        Map<Character, Integer> subStrChars = getCharCounts(s.substring(0, end + 1).toCharArray());

        while (start <= end) {
            boolean hasTarget = hasTarget(subStrChars, targetChars);

            if (hasTarget) {
                int subStrLength = end - start;
                if (subStrLength < minWindowLength) {
                    minWindowStr = s.substring(start, end + 1);
                    minWindowLength = subStrLength;
                }

                removeChar(subStrChars, s.charAt(start));
                start++;
            } else {
                if (end < (s.length() - 1)) {
                    end++;
                    addChar(subStrChars, s.charAt(end));
                } else {
                    removeChar(subStrChars, s.charAt(start));
                    start++;
                }
            }
        }

        return minWindowStr;
    }

    private static Map<Character, Integer> getCharCounts(char[] str) {
        Map<Character, Integer> charCounts = new HashMap<>();
        for (int i = 0; i < str.length; i++) {
            char token = str[i];
            if (charCounts.containsKey(token)) {
                int count = charCounts.get(token);
                charCounts.put(token, count + 1);
            } else {
                charCounts.put(token, 1);
            }
        }

        return charCounts;
    }

    private static boolean hasTarget(Map<Character, Integer> subStrChars, Map<Character, Integer> targetChars) {
        boolean hasTarget = true;
        for (Map.Entry<Character, Integer> entry: targetChars.entrySet()) {
            char targetKey = entry.getKey();
            int targetVal = entry.getValue();

            if (!(subStrChars.containsKey(targetKey) && subStrChars.get(targetKey) >= targetVal)) {
                hasTarget = false;
                break;
            }
        }

        return hasTarget;
    }

    private static void addChar(Map<Character, Integer> charMap, char token) {
        if (charMap.containsKey(token)) {
            int count = charMap.get(token);
            charMap.put(token, count + 1);
        } else {
            charMap.put(token, 1);
        }
    }

    private static void removeChar(Map<Character, Integer> charMap, char token) {
        if (charMap.containsKey(token)) {
            int count = charMap.get(token);
            charMap.put(token, count - 1);
        }
    }
}
