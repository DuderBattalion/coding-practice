public class CountAndSay {
    public static void main(String[] args) {
        System.out.println(countAndSay(5));
    }

    public static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        return calcNthCountAndSay(n-1, "1");
    }

    public static String calcNthCountAndSay(int n, String output) {
        if (n == 0) {
            return output;
        }

        String nextOutput = calcNextOutput(output);
        return calcNthCountAndSay(n-1, nextOutput.toString());
    }

    private static String calcNextOutput(String output) {
        StringBuilder nextOutput = new StringBuilder();

        if (output.length() == 1) {
            nextOutput.append("1");
            nextOutput.append(output);

            return nextOutput.toString();
        }

        int i = 0;
        while (i < output.length()) {
            int count = 0;
            char currChar = output.charAt(i);

            while (i < output.length() && currChar == output.charAt(i)) {
                count++;
                i++;
            }

            nextOutput.append(count);
            nextOutput.append(currChar);
        }

        return nextOutput.toString();
    }
}
