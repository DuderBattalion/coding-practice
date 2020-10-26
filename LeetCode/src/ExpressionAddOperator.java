import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExpressionAddOperator {
    public static void main(String[] args) throws ScriptException {
//        String num = "123";
//        int target = 6;

//        String num = "232";
//        int target = 8;

//        String num = "105";
//        int target = 5;

//        String num = "00";
//        int target = 0;

        String num = "3456237490";
        int target = 9191;

//        String num = "123456789";
//        int target = 45;

//        String num = "123456789";
//        int target = 45;

        List<String> output = addOperators(num, target);
        for (String row: output) {
            System.out.println(row);
        }
    }

    /**
     * Algorithm:
     * Scan from left to right. Recurse through the following cases:
     * - add
     * - subtract
     * - multiply
     *
     * Special cases to consider
     * - For first operand starting at i = 0, no operators, simply add value
     * - For multi-character numbers, go from i ... j, with j from i to num.length() - and recurse
     * - For multiplication operator, revert the last operation: ex: 1 + 2 (=3), but this changes on
     * 1+2*3 - since multiplication takes priority. So revert last operation (-2).
     * @return
     */
    public static List<String> addOperators(String num, int target) {
        List<String> output = new ArrayList<>();
        if (num.isEmpty()) {
            return output;
        }

        recursiveAddOperators(num, target, output, 0, "", 0, 0);
        return output;
    }

    private static void recursiveAddOperators(String num, int target, List<String> output, int i,
                                              String expression, long sum, long prevOperand) {
        if (i == num.length()) {
            if (sum == target) {
                output.add(expression);
            }

            return;
        }

        for (int j = i; j < num.length(); j++) {
            // Skip multi-character sequences that start with "0" - ex: 1 * 05
            if (j != i && num.charAt(i) == '0') {
                break;
            }

            long operand = Long.parseLong(num.substring(i, j + 1));

            if (i == 0) {
                recursiveAddOperators(num, target, output, j + 1,
                        expression + operand, operand, operand);
            } else {
                recursiveAddOperators(num, target, output, j + 1,
                        expression + "+" + operand, sum + operand, operand);

                recursiveAddOperators(num, target, output, j + 1,
                        expression + "-" + operand, sum - operand, -operand);

                long newSum = sum - prevOperand + (prevOperand * operand);
                recursiveAddOperators(num, target, output, j+1,
                        expression + "*" + operand, newSum, prevOperand * operand);
            }
        }
    }
}
