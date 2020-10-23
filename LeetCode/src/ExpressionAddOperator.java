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
                String.valueOf(num.charAt(0)), output);

        return output;
    }

    private static void recursiveAddOperators(String num, int target, String expression, double value,
                                              int i, String digit, List<String> output) {
        if (value == target) {
            output.add(expression);
        }

        if (i >= num.length()) {
            return;
        }

//        char nextDigit = num.charAt(i);
        int digitVal = Integer.parseInt(digit);
        String nextDigit = "";
        if (i + 1 < num.length()) {
            nextDigit = String.valueOf(num.charAt(i+1));
        }

        recursiveAddOperators(num, target, expression + "+" + digit,
                value + digitVal, i+1, nextDigit, output);

        recursiveAddOperators(num, target, expression + "-" + digit,
                value - digitVal, i+1, nextDigit, output);

        recursiveAddOperators(num, target, expression + "+" + digit,
                value * digitVal, i+1, nextDigit, output);

        // No operator added - include next character in operand
        digit += nextDigit;
        recursiveAddOperators(num, target, expression, value, i+1, digit, output);
    }

}
