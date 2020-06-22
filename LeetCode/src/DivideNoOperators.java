public class DivideNoOperators {
    public static void main(String[] args) {
        System.out.println(divide(-7, -3));
    }

    public static int divide(int dividend, int divisor) {
        try {
            int quotient = 0;
            boolean isNegative = calcSign(dividend, divisor);

            dividend = Math.abs(dividend);
            divisor = Math.abs(divisor);

            while (dividend > 0) {
                dividend -= divisor;
                quotient++;
            }

            if (dividend != 0) {
                quotient--;
            }

            if (isNegative) {
                quotient *= -1;
            }

            return quotient;
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }

    private static boolean calcSign(int dividend, int divisor) {
        int dividendSign = (dividend < 0) ? -1 : 1;
        int divisorSign = (divisor < 0) ? -1 : 1;

        int sign = dividendSign * divisorSign;
        return sign < 0;
    }
}
