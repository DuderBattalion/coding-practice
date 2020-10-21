public class IntegerToEnglishWords {
    public static void main(String[] args) {

    }

    public static String numberToWords(int num) {
        int power = 9;
        double divider;
        do {
           divider = Math.pow(10, power);
           power-= 3;
        } while (divider < num);

        StringBuilder word = new StringBuilder();
        while (power >= 0) {
            divider = Math.pow(10, power);

            int quotient = (int) (num/divider);
            int remainder = (int) (num % divider);

            String snippet = createSnippet(quotient, power);
            word.append(snippet);
            word.append(" ");

            power = getNextPower(power);
        }

        return word.toString().trim();
    }

    private static String createSnippet(int num, int power) {
        return null;
    }
}
