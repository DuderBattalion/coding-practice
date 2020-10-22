public class IntegerToEnglishWords {
    private final static String[] ONES = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
    private final static String[] TENS = { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
    private final static String[] THOUSANDS = { "", "Thousand", "Million", "Billion" };

    public static void main(String[] args) {
        System.out.println(numberToWords(1));
        System.out.println(numberToWords(12));
        System.out.println(numberToWords(123));
        System.out.println(numberToWords(12345));
        System.out.println(numberToWords(1234567));
        System.out.println(numberToWords(1234567891));

        System.out.println(numberToWords(Integer.MAX_VALUE));
        System.out.println(numberToWords(0));
    }

    public static String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        StringBuilder word = new StringBuilder();
        int i = 0;
        while (num > 0) {
            int remainder = num % 1000;
            if (remainder != 0) {
                String snippet = createSnippet(remainder);
                word.insert(0, snippet + THOUSANDS[i] + " ");
            }

            num = num/1000;
            i++;
        }

        return word.toString().toString().trim();
    }

    private static String createSnippet(int num) {
        StringBuilder snippet = new StringBuilder();
        if (num < 20) {
            snippet.append(ONES[num]).append(" ");
        } else if (num < 100) {
            int secondNum = num % 10;
            int firstNum = num/10;

            snippet.append(TENS[firstNum]).append(" ").append(ONES[secondNum]).append(" ");
        } else if (num < 1000) {
            int secondNums = num % 100;
            int firstNum = num/100;

            snippet.append(ONES[firstNum]).append(" Hundred ").append(createSnippet(secondNums));
        }

        return snippet.toString();
    }
}
