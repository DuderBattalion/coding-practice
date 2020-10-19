public class BasicCalculator {
    public static void main(String[] args) {
        System.out.println(calculate("1+(2+3)"));
    }

    public static int calculate(String s) {
        SubResult result = recursiveCalculate(s, new SubResult(0, 0));
        return result.result;
    }

    private static SubResult recursiveCalculate(String s, SubResult result) {
        if (result.index >= s.length()) {
            return result;
        }

        char token = s.charAt(result.index);
        if (token == '(') {
            SubResult subResult = recursiveCalculate(s, new SubResult(0, result.index+1));
            result.index = subResult.index;
            result.result += subResult.result;
        } else if (token == ')') {
            result.index += 1;
        }  else {
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
                result.index = subResult.index;
            } else {
                rightNum = Character.getNumericValue(s.charAt(result.index+2));
                result.index = result.index + 3;
            }

            if (operator == '+') {
                result.result += (leftNum + rightNum);
            } else {
                result.result += (rightNum - leftNum);
            }
        }

        return recursiveCalculate(s, result);
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
