import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        Set<String> output = new HashSet<>();
        String[] currentChainContainer = new String[1];
        currentChainContainer[0] = "";

        recursiveRemoveExtraBrackets(s, 0, output, currentChainContainer,
                0, 0, numExtraBrackets);

        return new ArrayList<>(output);
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
                                                     Set<String> output,
                                                     String[] currentChainContainer,
                                                     int bracketCount, int numRemove,
                                                     int maxRemove) {
        if (bracketCount < 0) {
            return;
        }

        String currentChain = currentChainContainer[0];
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
        recursiveRemoveExtraBrackets(s, i+1, output, currentChainContainer,
                bracketCount, numRemove + 1, maxRemove);

        // 2. Keep bracket
        char token = s.charAt(i);
        if (token == '(') {
            bracketCount++;
        } else if (token == ')') {
            bracketCount--;
        }

        currentChain += token;
        currentChainContainer[0] = currentChain;

        recursiveRemoveExtraBrackets(s, i+1, output, currentChainContainer,
                bracketCount, numRemove, maxRemove);

        // Backtrack
        currentChain = currentChain.substring(0, currentChain.length() - 1);
        currentChainContainer[0] = currentChain;
    }
}
