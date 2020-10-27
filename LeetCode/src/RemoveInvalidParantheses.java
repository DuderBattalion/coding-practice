import java.util.ArrayList;
import java.util.List;

public class RemoveInvalidParantheses {
    public static void main(String[] args) {
        String s = "()())()";
        List<String> output = removeInvalidParentheses(s);

        for (String result: output) {
            System.out.println(result);
        }
    }

    public static List<String> removeInvalidParentheses(String s) {
        int numExtraBrackets = calcExtraBrackets(s);

        List<String> output = new ArrayList<>();
        recursiveRemoveExtraBrackets(s, 0, output, new StringBuilder(),
                0, 0, numExtraBrackets);

        return output;
    }

    private static int calcExtraBrackets(String s) {
        int bracketCount = 0;
        for (char token: s.toCharArray()) {
            if (token == '(') {
                bracketCount++;
            } else if (token == ')') {
                bracketCount--;
            }
        }

        return Math.abs(bracketCount);
    }

    private static void recursiveRemoveExtraBrackets(String s, int i,
                                                     List<String> output,
                                                     StringBuilder currentChain,
                                                     int bracketCount, int numRemove,
                                                     int maxRemove) {
        if (bracketCount < 0) {
            return;
        }

        if (i >= s.length()) {
            if (bracketCount == 0) {
                output.add(currentChain.toString());
            }

            return;
        }

        if (numRemove > maxRemove) {
            return;
        }

        // Cases at index i:
        // 1. Remove bracket
        // 2. Keep bracket

        // 1. Remove bracket
        recursiveRemoveExtraBrackets(s, i+1, output, currentChain,
                bracketCount, numRemove + 1, maxRemove);

        // 2. Keep bracket
        char token = s.charAt(i);
        if (token == '(') {
            bracketCount++;
        } else if (token == ')') {
            bracketCount--;
        }

        currentChain.append(token);
        recursiveRemoveExtraBrackets(s, i+1, output, currentChain,
                bracketCount, numRemove, maxRemove);
    }
}
