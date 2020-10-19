public class BasicCalculator {
    public static void main(String[] args) {
        System.out.println(calculate("1+(2+3)"));
        System.out.println(calculate("(1+(2+3))-4"));
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(calculate("(1+3)+(6+8)"));
        System.out.println(calculate(" 2-1 + 2 "));

        System.out.println(calculate("4-5+2"));
        System.out.println(calculate("(7)-(0)+(4)"));
    }

    /**
     * Algorithm:
     * Ex: 1 + (2 + 3) - 4
     * - Step 1: Pick first num
     * - Pick 1st token
     * - If token is '(', then do recursive calculate sub result.
     * - result += subResult.sum (or normal num)
     * - Move index to subResult.index + 1 (or index + 1)
     * -
     * - Step 2
     * - Pick operand '+' or '-'
     * -
     * - Step 3
     * - Pick token
     * - Same steps as Step 1
     * -
     * - Step 4
     * - Do calculations: firstNum, operand, secondNum
     * - Move index to after secondNum
     * -
     * - Keep doing this till index is at end of string.
     *
     */
    public static int calculate(String s) {
        s = s.replaceAll(" ", "");

        int subResult;
        int[] globalIndex = new int[1];
        do {
            subResult = recursiveCalculate(s, 0, globalIndex);
            if (globalIndex[0] >= s.length()) {
                break;
            }

            s = subResult + s.substring(globalIndex[0]);
            globalIndex[0] = 0;
        } while (true);

        return subResult;
    }

    private static int recursiveCalculate(String s, int i, int[] globalIndex) {
        if (i >= s.length()) {
            globalIndex[0] = i;
            return 0;
        }

        String leftNumStr = getNumStr(s, i);
        if (leftNumStr.equals("(")) {
            int subResult = recursiveCalculate(s, i+1, globalIndex);
            leftNumStr = String.valueOf(subResult);
            i = globalIndex[0];
        } else {
            i += leftNumStr.length();
        }

        if (i >= s.length()) {
            globalIndex[0] = i;
            return Integer.parseInt(leftNumStr);
        }

        char operand = s.charAt(i);
        i++;
        if (operand == ')') {
            globalIndex[0] = i;
            return Integer.parseInt(leftNumStr);
        }

//        char operand;
//        do {
//            operand = s.charAt(i);
//            i++;
//        } while (operand == ')' && i < s.length());

        if (i >= s.length()) {
            globalIndex[0] = i;
            return Integer.parseInt(leftNumStr);
        }

        String rightNumStr = getNumStr(s, i);
        if (rightNumStr.equals("(")) {
            int subResult = recursiveCalculate(s, i+1, globalIndex);
            rightNumStr = String.valueOf(subResult);

            i = globalIndex[0];
        } else {
            i += rightNumStr.length();
        }

        int leftNum = Integer.parseInt(leftNumStr);
        int rightNum = Integer.parseInt(rightNumStr);

        int result;
        if (operand == '+') {
            result = leftNum + rightNum;
        } else {
            result = leftNum - rightNum;
        }

        globalIndex[0] = i;
        return result;
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

//    private static SubResult recursiveCalculate(String s, SubResult result) {
//        if (result.index >= s.length()) {
//            return result;
//        }
//
//        char token = s.charAt(result.index);
//        if (token == '(') {
//            SubResult subResult = recursiveCalculate(s, new SubResult(0, result.index+1));
//            result.index = subResult.index + 1;
//            result.result += subResult.result;
//        } else {
//            // TODO - more intelligent parse to get multi-digit number
//            // Assuming single digit for now.
//            // Also, assuming no spaces. Have to strip out spaces.
//            Equation equation = parseEquation(s, result.index);
//
//            int leftNum = Character.getNumericValue(token);
//            char operator = s.charAt(result.index+1);
//
//            char rightNumChar = s.charAt(result.index+2);
//            int rightNum;
//            if (rightNumChar == '(') {
//                SubResult subResult = recursiveCalculate(s, new SubResult(0, result.index+3));
//                rightNum = subResult.result;
//                result.index = subResult.index + 1;
//            } else {
//                rightNum = Character.getNumericValue(s.charAt(result.index+2));
//                result.index = result.index + 3;
//            }
//
//            if (operator == '+') {
//                result.result += (leftNum + rightNum);
//            } else {
//                result.result += (leftNum - rightNum);
//            }
//        }
//
//        return result;
//    }

//    private static Equation parseEquation(String s, int i) {
//        String leftNumStr = getNumStr(s, i);
//        if (leftNumStr.equals("(")) {
//
//        }
//
//        i += leftNumStr.length();
//
//        char operand = s.charAt(i);
//
//        String rightNumStr = getNumStr(s, i);
//
//    }
//
//    private static class SubResult {
//        public int result;
//        public int index;
//
//        public SubResult(int result, int index) {
//            this.result = result;
//            this.index = index;
//        }
//    }
//
//    private static class Equation {
//        public int leftNum;
//        public int rightNum;
//        public char operand;
//
//        public int solveEquation() {
//            if (operand == '+') {
//                return leftNum + rightNum;
//            } else {
//                return leftNum - rightNum;
//            }
//        }
//    }
}
