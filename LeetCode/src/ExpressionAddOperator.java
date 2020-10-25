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

//        String num = "3456237490";
//        int target = 9191;

//        String num = "123456789";
//        int target = 45;

        String num = "123456789";
        int target = 45;

        List<String> output = addOperators(num, target);
        for (String row: output) {
            System.out.println(row);
        }

        System.out.println();
        System.out.println("=======================");

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        for (String row: output) {
            int result = (int) engine.eval(row);
            if (result != target) {
                System.out.println(row);
            } else {
                System.out.println(target);
            }
        }

//        Set<String> test = new HashSet<>(output);
//        System.out.println(String.format("List has %d elements. Set has %d elements", output.size(), test.size()));
    }

    public static List<String> addOperators(String num, int target) {

    }

    private static void recursiveAddOperators(String num, int target, List<String> output, int i,
                                              String expression, long sum, long prevOperand) {
        if (i == num.length()) {
            if (sum == target) {
                output.add(expression);
            }

            return;
        }

        int digit = Character.getNumericValue(num.charAt(i));

        // Add "+"
        recursiveAddOperators(num, target, output, i+1, expression + "+" + digit,
                sum + digit, digit);

        // Subtract "-"
        recursiveAddOperators(num, target, output, i+1, expression + "-" + digit,
                sum - digit, digit);

        // Multiply "*"
        long newSum = (sum - prevOperand) + (prevOperand * digit);
        recursiveAddOperators(num, target, output, i+1, expression + "*" + digit, newSum,
                prevOperand * digit);

        // Add next digit: ex - "1" to "12"
        long newOperand = Long.parseLong(String.valueOf(prevOperand) + String.valueOf(digit));

    }

//    public static List<String> addOperators(String num, int target) {
//        List<String> output = new ArrayList<>();
//        if (num.length() == 1) {
//            if (Integer.parseInt(num) == target) {
//                output.add(num);
//            }
//
//            return output;
//        }
//
//        String expression = num.substring(0, 1);
//        int value = Integer.parseInt(expression);
//
//        recursiveAddOperators(num, target, expression, value, 1,
//                expression, output);
//
//        return output;
//    }
//
//    private static void recursiveAddOperators(String num, int target, String expression,
//                                              long value, int i, String prevOperand,
//                                              List<String> output) {
//        // Checking expression length to get around num = "00", while
//        // expression is "0" - which apparently is not accepted as
//        // an answer - *commence eye roll* -
//        if (value == target && expression.length() >= num.length() && i == num.length()) {
//            output.add(expression);
//        }
//
//        if (i >= num.length()) {
//            return;
//        }
//
//        String operand = String.valueOf(num.charAt(i));
//        long operandValue = Long.parseLong(operand);
//        long prevOperandValue = Long.parseLong(prevOperand);
//
//        String newExpression = expression + "+";
//        recursiveAddOperators(num, target, newExpression + operand,
//                value + operandValue, i+1, operand, output);
//
//        newExpression = expression + "-";
//        recursiveAddOperators(num, target, newExpression + operand,
//                value - operandValue, i+1, "-" + operand, output);
//
//        newExpression = expression + "*";
//        long newValue = value - prevOperandValue + (prevOperandValue * operandValue);
//        recursiveAddOperators(num, target, newExpression + operand,
//                newValue, i+1, String.valueOf(prevOperandValue * operandValue), output);
//
//        // No operator added - include next character in operand
//        String mergeOperand = prevOperand + operand;
//        newExpression = expression + operand;
//        long mergeOperandValue = Long.parseLong(mergeOperand);
//        newValue = (value - prevOperandValue) + mergeOperandValue;
//
//        // To get around results like 1*05 - where 05 is not eligible
//        if (mergeOperand.charAt(0) != '0') {
//            recursiveAddOperators(num, target, newExpression, newValue, i+1, mergeOperand, output);
//        }
//    }
}
