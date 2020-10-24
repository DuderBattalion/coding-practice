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

//        recursiveAddOperators(num, target, "", 0, 0,
//                String.valueOf(num.charAt(0)), 0, output);

        String expression = num.substring(0, 1);
        int value = Integer.parseInt(expression);

        recursiveAddOperators(num, target, expression, value, 1,
                value, output);

        return output;
    }

    private static void recursiveAddOperators(String num, int target, String expression,
                                              double value, int i, int prevOperand,
                                              List<String> output) {
        if (value == target) {
            output.add(expression);
        }

        if (i >= num.length()) {
            return;
        }

        String operand = String.valueOf(num.charAt(i));
        int operandValue = Integer.parseInt(operand);

        String newExpression = expression + "+";
        recursiveAddOperators(num, target, newExpression + operand,
                value + operandValue, i+1, operandValue, output);

        newExpression = expression + "-";
        recursiveAddOperators(num, target, newExpression + operand,
                value - operandValue, i+1, -operandValue, output);

        newExpression = expression + "*";
        double newValue = value - prevOperand + (prevOperand * operandValue);
        recursiveAddOperators(num, target, newExpression + operand,
                newValue, i+1, operandValue, output);

        // No operator added - include next character in operand
        String mergeOperands = String.valueOf(prevOperand) + operand;
        recursiveAddOperators(num, target, expression, value, i+1, Integer.parseInt(mergeOperands), output);
    }

}
