import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ValidParantheses {
    public static void main(String[] args) {
//        String text = "()[]{}({[]})";
        String text = "((";
        System.out.println(isValid(text));
    }

    public static boolean isValid(String s) {
        if (s.isEmpty()) {
            return true;
        }

        if (s.length() % 2 != 0) {
            return false;
        }

        Map<Character, Character> bracketPairs = createBracketPairs();

        boolean isValid = true;
        Deque<Character> bracketStack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char token = s.charAt(i);
            if (token == '(' || token == '{' || token == '[') {
                bracketStack.push(token);
            } else {
                if (bracketStack.isEmpty()) {
                    isValid = false;
                    break;
                }

                char prevToken = bracketStack.pop();
                Character expectedToken = bracketPairs.get(token);
                if (expectedToken == null || expectedToken != prevToken) {
                    isValid = false;
                    break;
                }
            }
        }

        return (isValid && bracketStack.isEmpty());
    }

    private static Map<Character, Character> createBracketPairs() {
        var map = new HashMap<Character, Character>();

        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        return map;
    }
}
