import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    public static String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty() || s.length() < t.length()) {
            return "";
        }

        int start = 0, end = t.length() - 1, minWindowLength = Integer.MAX_VALUE;
        String minWindowStr = "";
        while (start < end) {
            String subStr = s.substring(start, end + 1);
            boolean hasTarget = hasTarget(subStr, t);
            if (hasTarget && (subStr.length() < minWindowLength)) {
                minWindowStr = subStr;
                minWindowLength = subStr.length();

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

    private static boolean hasTarget(String subStr, String target) {
        Map<Character, Integer> subStrChars = getCharCounts(subStr);
        Map<Character, Integer> targetChars = getCharCounts(target);

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

    private static Map<Character, Integer> getCharCounts(String str) {
        Map<Character, Integer> charCounts = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char token = str.charAt(i);
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
