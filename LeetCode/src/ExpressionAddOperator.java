import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperator {
    public static void main(String[] args) {
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

        List<String> output = addOperators(num, target);
        for (String row: output) {
            System.out.println(row);
        }
    }

    public static List<String> addOperators(String num, int target) {
        List<String> output = new ArrayList<>();
        if (num.length() == 1) {
            if (Integer.parseInt(num) == target) {
                output.add(num);
            }

            return output;
        }

        String expression = num.substring(0, 1);
        int value = Integer.parseInt(expression);

        recursiveAddOperators(num, target, expression, value, 1,
                value, output);

        return output;
    }

    private static void recursiveAddOperators(String num, int target, String expression,
                                              double value, int i, double prevOperand,
                                              List<String> output) {
        // Checking expression length to get around num = "00", while
        // expression is "0" - which apparently is not accepted as
        // an answer - *commence eye roll* -
        if (value == target && expression.length() >= num.length()) {
            output.add(expression);
        }

        if (i >= num.length()) {
            return;
        }

        String operand = String.valueOf(num.charAt(i));
        double operandValue = Double.parseDouble(operand);

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
        String mergeOperand = String.valueOf(prevOperand) + operand;
        newExpression = expression + operand;
        double mergeOperandValue = Double.parseDouble(mergeOperand);
        value = (value - prevOperand) + mergeOperandValue;

        // To get around results like 1*05 - where 05 is not eligible
        if (String.valueOf(mergeOperandValue).length() == mergeOperand.length()) {
            recursiveAddOperators(num, target, newExpression, value, i+1, mergeOperandValue, output);
        }
    }
}
