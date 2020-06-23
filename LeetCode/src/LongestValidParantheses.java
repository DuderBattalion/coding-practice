import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LongestValidParantheses {
    public static void main(String[] args) {
//        String s = ")()())";
//        String s = "(()";

        String s = "())()()(())((()(()()(((()))((((())((()(())()())(()((((()))()(()))(())()(())(()(((((())((((((()())())(()(()((())()))(()))))))()(()))((((())()()()))()()()(((()(()())(()()(()(()()(((()))))))()()))())())((()()))))))((()))(((()((())()(()()))((())))()()())))))))()))))(()))))()))()))()((())))((()))(()))))))(((()))))))))()(()()()(())((())()))()()(())))()()))(()())()))(((()())()))((())((((()))(()(()(()()()(((())()(((((()))((()(((((())(()()))((((((((()(()(()(()(())))(())(()())())(()((((()(())((()(())))(())))()(((((()(()()(())))))))())(())(())(()()(((())))((()))(((((()))))())))()((()))()))))())))))((())(((((()()))((((())))(((()(()(())())(((()(()(()()()())))())()))((()((())())()()()(((())(((((()((((((()((()())))((((())((()(((((((()(()((()()()(()(()())(()(()()((((())))()(((()())))(()()))()(()()()()(((((())(()))))((()))())))()((((((()))())))()(()))(())))((((()())(((((()()())(((((())(()())(()))))()(()()))()))))))())))(((())(()(()()))(()))()(((())))())((((()(((()))))))()(()(()))()()(()()))))))))((()))))))(())((()((()))()))((((((()())))))(()((())((((()))))(()(()()()()(()))()()(()(()))(()()(((((((()())(())(()())((())())()(()())((())()())())(()())))())))(())())())(())((()())(((()()))()))()()))()(()(())((((((((())))()((())((()((((((((((()))))(()(((((())(()(()())())))((())())))))()))(()((()()))((()((())()()()((()(())())((())())(()()(((())))))())()()(()))()())(()(()((())))((((()()(())))())(())(()(()(())())())(()()())()(())())))(()()(((())))((()()(((())()()(()())((((()()(()())(()((((()(()()(()(()(((()((()())(()()))(()((((()(((((()))))()()))(((()((((((()(()()()()())()))(()(())))))((()(((()())())))(((()()))(()(()(((((((()()))(()(())))())()(())())(())(()))(())(()))()()(()()())))))()))()((())(((()((((((((())()()))())))((()())(";
        System.out.println(longestValidParentheses(s));
    }

    public static int longestValidParentheses(String s) {
        return findLongestValidParantheses(s, 0, s.length(), new HashMap<String, Integer>());
    }

    private static int findLongestValidParantheses(String s, int start, int end,
                                                   Map<String, Integer> cache) {
        if (start == end) {
            return 0;
        }

        int paranLen;
        String key = start + "," + end;

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        String possibleLongestParans = s.substring(start, end);
        if (isValidParans(possibleLongestParans)) {
            paranLen = possibleLongestParans.length();

            cache.put(key, paranLen);
            return paranLen;
        }

        int shrinkRightParan = findLongestValidParantheses(s, start + 1, end, cache);
        int shrinkLeftParan = findLongestValidParantheses(s, start, end - 1, cache);

        paranLen = Math.max(shrinkLeftParan, shrinkRightParan);
        cache.put(key, paranLen);

        return paranLen;
    }

    private static boolean isValidParans(String parans) {
        Deque<Character> paranStack = new LinkedList<>();

        boolean isValid = true;
        for (int i = 0; i < parans.length(); i++) {
            char token = parans.charAt(i);
            if (token == '(') {
                paranStack.push(token);
                continue;
            }

            if (paranStack.isEmpty()) {
                isValid = false;
                break;
            }

            paranStack.pop();
        }

        return isValid && paranStack.isEmpty();
    }
}
