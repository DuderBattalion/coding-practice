public class LengthOfLastWord {
    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("Hello World"));
    }

    public static int lengthOfLastWord(String s) {
        s = s.trim();

        if (s.length() == 0) {
            return 0;
        }

        s = new StringBuilder(s).reverse().toString();

        StringBuilder lastWord = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char token = s.charAt(i);
            if (token == ' ') {
                break;
            }

            lastWord.append(token);
        }

        return lastWord.length();
    }
}
