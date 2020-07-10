import java.util.ArrayList;
import java.util.List;

public class MultiplyStrings {
    public static void main(String[] args) {
        multiply("123", "456");
    }

    public static String multiply(String num1, String num2) {
        if (num1.length() == 0 || num2.length() == 0) {
            return "0";
        }

        String longString, shortString;
        if (num1.length() < num2.length()) {
            longString = num2;
            shortString = num1;
        } else {
            longString = num1;
            shortString = num2;
        }

        longString = reverseString(longString);
        shortString = reverseString(shortString);

        List<String> intermediateResults = new ArrayList<>();
        int i = longString.length() - 1;

        while (i >= 0) {
            int longDigit = getDigit(longString, i);
            StringBuilder result = new StringBuilder();

            // Add trailing zeroes for intermediate results
            result.append("0".repeat(i));

            int j = shortString.length() - 1;

            int sum = 0;
            int carry = 0;
            while (j >= 0) {
                int shortDigit = getDigit(shortString, j);
                int digitMultiply = (longDigit * shortDigit) + carry;

                if (digitMultiply > 9) {
                    carry = digitMultiply % 10;
                    sum = digitMultiply/10;
                }

                result.append(sum);
                j--;
            }

            intermediateResults.add(result.reverse().toString());
            i--;
        }

        // Debug test
        intermediateResults.forEach(System.out::println);
        return "";

//        return calcSumIntermediateResults(intermediateResults);


    }

    private static int getDigit(String str, int index) {
        if (str.isEmpty() || index >= str.length()) {
            return -1;
        }

        return Character.getNumericValue(str.charAt(index));
    }

    private static String reverseString(String str) {
        if (str.isEmpty()) {
            return str;
        }

        return new StringBuilder(str).reverse().toString();
    }
}
