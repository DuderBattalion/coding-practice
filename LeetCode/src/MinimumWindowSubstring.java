import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
//        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
//        System.out.println(minWindow("ADOBECODEBANCEEFFG", "ABC"));
        System.out.println(minWindow("AA", "BCDG"));
//        System.out.println(minWindow("a", "a"));
    }

    public static String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty() || s.length() < t.length()) {
            return "";
        }

        int start = 0, end = t.length() - 1, minWindowLength = Integer.MAX_VALUE;
        String minWindowStr = "";
        char[] sChars = s.toCharArray();
        Map<Character, Integer> targetChars = getCharCounts(t.toCharArray(), 0, t.length() - 1);

        while (start <= end) {
            boolean hasTarget = hasTarget(sChars, start, end, targetChars);

            if (hasTarget) {
                int subStrLength = end - start;
                if (subStrLength < minWindowLength) {
                    minWindowStr = s.substring(start, end + 1);
                    minWindowLength = subStrLength;
                }

                start++;
            } else {
                if (end < (s.length() - 1)) {
                    end++;
                } else {
                    start++;
                }
            }
        }

        return minWindowStr;
    }

    private static boolean hasTarget(char[] s, int start, int end, Map<Character, Integer> targetChars) {
        Map<Character, Integer> subStrChars = getCharCounts(s, start, end);

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

    private static Map<Character, Integer> getCharCounts(char[] str, int start, int end) {
        Map<Character, Integer> charCounts = new HashMap<>();
        for (int i = start; i <= end; i++) {
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
}
