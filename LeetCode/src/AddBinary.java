public class AddBinary {
    public static void main(String[] args) {
        System.out.println(addBinary("11", "1"));
    }

    public static String addBinary(String a, String b) {
        String shortStr = (a.length() < b.length()) ? new String(a) : new String(b);
        String longStr = (a.length() >= b.length()) ? new String(a) : new String(b);

        // Pad short string with zeroes
        shortStr = "0".repeat(longStr.length() - shortStr.length()) + shortStr;

        StringBuilder output = new StringBuilder();
        SumCarry sumCarry = new SumCarry(0, 0);
        for (int i = longStr.length() - 1; i >= 0; i--) {
            sumCarry = calcSumCarry(
                    String.valueOf(longStr.charAt(i)),
                    String.valueOf(shortStr.charAt(i)),
                    sumCarry.carry
            );

            output.append(sumCarry.sum);
        }

        if (sumCarry.carry > 0) {
            output.append(sumCarry.carry);
        }

        return output.reverse().toString();
    }

    private static SumCarry calcSumCarry(String aDigit, String bDigit, int carry) {
        int a = Integer.parseInt(aDigit, 2);
        int b = Integer.parseInt(bDigit, 2);

        int sum = a + b + carry;

        SumCarry sumCarry;
        String binarySum = Integer.toBinaryString(sum);
        if (binarySum.length() > 1) {
            sumCarry = new SumCarry(
                    Character.getNumericValue(binarySum.charAt(1)),
                    Character.getNumericValue(binarySum.charAt(0)));
        } else {
            sumCarry = new SumCarry(Character.getNumericValue(binarySum.charAt(1)), 0);
        }

        return sumCarry;
    }

    private static class SumCarry {
        public int sum;
        public int carry;

        public SumCarry(int sum, int carry) {
            this.sum = sum;
            this.carry = carry;
        }
    }
}
