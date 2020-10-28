import java.util.*;

public class RemoveInvalidParantheses {
    public static void main(String[] args) {
//        String s = "()())()";
//        String s = "(a)())()";
//        String s = ")(";
        String s = "x(";

        List<String> output = removeInvalidParentheses(s);

        for (String result: output) {
            System.out.println(result);
        }
    }

    public static List<String> removeInvalidParentheses(String s) {
//        int numExtraBrackets = calcExtraBrackets(s);

        Map<Integer, Set<String>> outputMap = new HashMap<>();
//        String[] currentChainContainer = new String[1];
//        currentChainContainer[0] = "";

        recursiveRemoveExtraBrackets(s, 0, outputMap, new RemoveBracketData(),
                0, 0);

        int minRemove = Integer.MAX_VALUE;
        Set<String> output = new HashSet<>();
        for (Map.Entry<Integer, Set<String>> entry: outputMap.entrySet()) {
            int numRemove = entry.getKey();
            Set<String> result = entry.getValue();

            if (numRemove < minRemove) {
                minRemove = numRemove;
                output = result;
            }
        }

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
                                                     Map<Integer, Set<String>> output,
                                                     RemoveBracketData data,
                                                     int bracketCount, int numRemove) {
        if (bracketCount < 0 || numRemove > data.minRemove) {
            return;
        }

//        String currentChain = currentChainContainer[0];
        if (i >= s.length()) {
            if (bracketCount == 0) {
                if (output.containsKey(numRemove)) {
                    Set<String> results = output.get(numRemove);
                    results.add(data.chain);
                } else {
                    Set<String> results = new HashSet<>();
                    results.add(data.chain);

                    output.put(numRemove, results);
                }

//                output.put(data.chain.toString(), numRemove);
                if (numRemove < data.minRemove) {
                    data.minRemove = numRemove;
                }
            }

            return;
        }

//        if (numRemove > maxRemove) {
//            return;
//        }

        // Cases at index i:
        // 0. If non-bracket token, add to chain and move ahead
        // 1. Remove bracket
        // 2. Keep bracket

        char token = s.charAt(i);

        // 0. Non-bracket token
        if (token != '(' && token != ')') {
            data.chain += token;
            recursiveRemoveExtraBrackets(s, i+1, output, data,
                    bracketCount, numRemove);

            // Backtrack
            data.backtrack();
            return;
        }

        // 1. Remove bracket
        recursiveRemoveExtraBrackets(s, i+1, output, data,
                bracketCount, numRemove + 1);

        // 2. Keep bracket
        if (token == '(') {
            bracketCount++;
        } else if (token == ')') {
            bracketCount--;
        }

        data.chain += token;

        recursiveRemoveExtraBrackets(s, i+1, output, data,
                bracketCount, numRemove);

        // Backtrack
        data.backtrack();
    }

    private static class RemoveBracketData {
        public String chain;
        public int minRemove;

        public RemoveBracketData() {
            this.chain = "";
            this.minRemove = Integer.MAX_VALUE;
        }

        public void backtrack() {
            chain = chain.substring(0, chain.length() - 1);
        }
    }
}
