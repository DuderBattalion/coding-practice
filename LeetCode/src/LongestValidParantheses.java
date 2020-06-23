import java.util.Deque;
import java.util.LinkedList;

public class LongestValidParantheses {
    public static void main(String[] args) {
//        String s = ")()())";
//        String s = "(()";

        String s = ")(()(()(((())(((((()()))((((()()(()()())())())()))()()()())(())()()(((()))))()((()))(((())()((()()())((())))(())))())((()())()()((()((())))))((()(((((()((()))(()()(())))((()))()))())";
        System.out.println(longestValidParentheses(s));
    }

    public static int longestValidParentheses(String s) {
        return findLongestValidParantheses(s, 0, s.length());
    }

    private static int findLongestValidParantheses(String s, int start, int end) {
        if (start == end) {
            return 0;
        }

        String possibleLongestParans = s.substring(start, end);
        if (isValidParans(possibleLongestParans)) {
            return possibleLongestParans.length();
        }

        int shrinkRightParan = findLongestValidParantheses(s, start + 1, end);
        int shrinkLeftParan = findLongestValidParantheses(s, start, end - 1);

        return Math.max(shrinkLeftParan, shrinkRightParan);
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
