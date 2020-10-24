import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperator {
    public static void main(String[] args) {
        String num = "123";
        int target = 6;

        List<String> output = addOperators(num, target);
        for (String row: output) {
            System.out.println(row);
        }
    }

    public static List<String> addOperators(String num, int target) {
        List<String> output = new ArrayList<>();

        recursiveAddOperators(num, target, "", 0, 0,
                String.valueOf(num.charAt(0)), 0, output);

        return output;
    }

    private static void recursiveAddOperators(String num, int target, String expression,
                                              double value, int i, String operand, int prevOperand,
                                              List<String> output) {
        if (value == target) {
            output.add(expression);
        }

        if (i >= num.length()) {
            return;
        }

        int operandValue = Integer.parseInt(operand);
        String nextOperand = "";
        if (i + 1 < num.length()) {
            nextOperand = String.valueOf(num.charAt(i+1));
        }

        String newExpression = "";
        if (!expression.isEmpty()) {
            newExpression = expression + "+";
        }

        recursiveAddOperators(num, target, newExpression + operand,
                value + operandValue, i+1, nextOperand, operandValue, output);

        if (!expression.isEmpty()) {
            newExpression = expression + "-";
        }

        recursiveAddOperators(num, target, newExpression + operand,
                value - operandValue, i+1, nextOperand, -operandValue, output);

        if (!expression.isEmpty()) {
            newExpression = expression + "*";
        }

        double newValue = value - prevOperand + (prevOperand * operandValue);
        recursiveAddOperators(num, target, newExpression + operand,
                newValue, i+1, nextOperand, operandValue, output);

        // No operator added - include next character in operand
        operand += nextOperand;
        recursiveAddOperators(num, target, expression, value, i+1, operand, prevOperand, output);
    }

}
