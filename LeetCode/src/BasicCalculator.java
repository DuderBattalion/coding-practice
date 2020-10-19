public class BasicCalculator {
    public static void main(String[] args) {
//        System.out.println(calculate("1+(2+3)"));
//        System.out.println(calculate("(1+(2+3))-4"));
//        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(calculate("(1+3)+(6+8)"));
    }

    /**
     * Algorithm:
     * Ex: 1 + (2 + 3) - 4
     * - Step 1: Pick first num
     * - Pick 1st token
     * - If token is '(', then do recursive calculate sub result.
     * - result += subresult.sum (or normal num)
     * - Move index to subresult.index + 1 (or index + 1)
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
        int result = 0;
        int index = 0;

        do {
            SubResult subResult = recursiveCalculate(s, new SubResult(0, 0));
            result = subResult.result;
            index = subResult.index;

            s = result + s.substring(index);
        } while (s.length() != 1);

        return result;
    }

    private static SubResult recursiveCalculate(String s, SubResult result) {
        if (result.index >= s.length()) {
            return result;
        }

        char token = s.charAt(result.index);
        if (token == '(') {
            SubResult subResult = recursiveCalculate(s, new SubResult(0, result.index+1));
            result.index = subResult.index + 1;
            result.result += subResult.result;
        } else {
            // TODO - more intelligent parse to get multi-digit number
            // Assuming single digit for now.
            // Also, assuming no spaces. Have to strip out spaces.
            int leftNum = Character.getNumericValue(token);
            char operator = s.charAt(result.index+1);

            char rightNumChar = s.charAt(result.index+2);
            int rightNum;
            if (rightNumChar == '(') {
                SubResult subResult = recursiveCalculate(s, new SubResult(0, result.index+3));
                rightNum = subResult.result;
                result.index = subResult.index + 1;
            } else {
                rightNum = Character.getNumericValue(s.charAt(result.index+2));
                result.index = result.index + 3;
            }

            if (operator == '+') {
                result.result += (leftNum + rightNum);
            } else {
                result.result += (leftNum - rightNum);
            }
        }

//        s = result.result + s.substring(result.index);
        return result;
    }

    private static class SubResult {
        public int result;
        public int index;

        public SubResult(int result, int index) {
            this.result = result;
            this.index = index;
        }
    }
}
