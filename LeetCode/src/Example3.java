public class Example3 {
    public static void main(String[] args) {
        String text = "abcd";

        StringBuilder reverse = new StringBuilder();
        recursiveReverse(text, text.length() - 1, reverse);
        System.out.println(reverse.toString());
    }

    private static void recursiveReverse(String text, int index, StringBuilder buf) {
        if (index < 0) {
            return;
        }

        if (index >= text.length()) {
            throw new RuntimeException("Error: Invalid index");
        }

        char token = text.charAt(index);
        buf.append(token);

        recursiveReverse(text, index - 1, buf);
    }
}
