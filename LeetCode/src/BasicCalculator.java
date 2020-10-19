import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculator {
    public static void main(String[] args) {
//        System.out.println(calculate("1+(2+3)"));
//        System.out.println(calculate("(1+(2+3))-4"));
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(calculate("(1+3)+(6+8)"));
        System.out.println(calculate(" 2-1 + 2 "));
//
        System.out.println(calculate("4-5+2"));
        System.out.println(calculate("(7)-(0)+(4)"));
        System.out.println(calculate("2-4-(8+2-6+(8+4-(1)+8-10))"));
    }

    /**
     * Algorithm:
     * Keep calculating results one by one. Start with 0 + .
     *
     * When we hit bracket, "(" - push state on stack (sum and sign before bracket)
     * Reset sum to 0, sign to '+', and process expression inside bracket.
     *
     * When we hit bracket ")", pop state from stack and add current state.
     *
     * Keep doing this till end of string.
     */
    public static int calculate(String s) {
        s = s.replaceAll(" ", "");

        int sum = 0, sign = 1;
        Deque<EquationState> equationStates = new ArrayDeque<>();

        int i = 0;
        while (i < s.length()) {
            Token token = getToken(s, i);
            switch (token.type) {
                case "num":  // TODO - make enum
                    int num = Integer.parseInt(token.val);
                    if (num < 0) {
                        throw new RuntimeException("[Error]: Number cannot be negative. num: " + num);
                    }

                    sum += num * sign;
                    break;
                case "sign":
                    if (token.val.equals("+")) {
                        sign = 1;
                    } else {
                        sign = -1;
                    }
                    break;
                case "bracketOpen": {
                    EquationState state = new EquationState(sum, sign);
                    equationStates.push(state);

                    sum = 0;
                    sign = 1;
                    break;
                }
                case "bracketClose": {
                    EquationState state = equationStates.pop();
                    sum = state.sum + sum * state.sign;
                    break;
                }
            }

            i += token.val.length();
        }

        return sum;
    }

    private static class EquationState {
        public int sum;
        public int sign;

        public EquationState(int sum, int sign) {
            this.sum = sum;
            this.sign = sign;
        }
    }

    private static Token getToken(String s, int i) {
        char tokenChar = s.charAt(i);

        Token token;
        if (isNumChar(tokenChar)) {
            String numStr = getNumStr(s, i);
            token = new Token(numStr, "num");
        } else if (tokenChar == '+' || tokenChar == '-'){
            token = new Token(String.valueOf(tokenChar), "sign");
        } else if (tokenChar == '(') {
            token = new Token(String.valueOf(tokenChar), "bracketOpen");
        } else if (tokenChar == ')') {
            token = new Token(String.valueOf(tokenChar), "bracketClose");
        } else {
            throw new RuntimeException("[Error]: Invalid token character: " + tokenChar);
        }

        return token;
    }

    private static class Token {
        String val;
        String type;

        public Token(String val, String type) {
            this.val = val;
            this.type = type;
        }
    }

    private static String getNumStr(String s, int i) {
        if (s.charAt(i) == '(') {
            return "(";
        }

        StringBuilder num = new StringBuilder();

        // Handle negative value
        if (s.charAt(i) == '-') {
            num.append("-");
            i++;
        }

        while (i < s.length() && isNumChar(s.charAt(i))) {
            num.append(s.charAt(i));
            i++;
        }

        return num.toString();
    }

    private static boolean isNumChar(char token) {
        int numToken = (token - '0');
        return numToken >= 0 && numToken <= 9;
    }
}
