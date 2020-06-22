public class DivideNoOperators {
    public static void main(String[] args) {
        System.out.println(divide(-2147483648, -2147483648));
    }

    public static int divide(int dividend, int divisor) {
        try {


            int quotient = 0;
            boolean isNegative = calcSign(dividend, divisor);

            // Handle integer overflow edge cases
            if (dividend == Integer.MIN_VALUE) {
                if (divisor == -1) {
                    return Integer.MAX_VALUE;
                } else if (divisor == 1) {
                    return Integer.MIN_VALUE;
                } else if (divisor == Integer.MIN_VALUE) {
                    return 1;
                }

                divisor = Math.abs(divisor);

                // Math.abs won't work, integer overflow
                Long longDividend = (long) dividend * -1;
                longDividend -= divisor;
                quotient++;

                // I think longDividend is guaranteed to be
                // in Integer range here. Since lowest the
                // original dividen can go is Integer.MIN_VALUE
                // and we subtract at least 1 from that (divisor
                // is guaranteed to be non-zero) to bring it
                // back into range.
                dividend = Math.toIntExact(longDividend);
            } else {
                dividend = Math.abs(dividend);
                divisor = Math.abs(divisor);
            }

            while (dividend > 0) {
                dividend -= divisor;
                quotient++;

                if (quotient == Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
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
