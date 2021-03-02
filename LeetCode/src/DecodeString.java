public class DecodeString {
    public static void main(String[] args) {
        System.out.println(decodeString("100[leetcode]"));
    }

    public static String decodeString(String s) {
        StringBuilder result = new StringBuilder();

        int i = 0;
        while (i < s.length()) {
            char sChar = s.charAt(i);
            if (isNumber(sChar)) {
                int repCount = parseNumber(s, i);

                // move index ahead of number, + 1 to consider '[' bracket.
                // can refactor StringHelper.
                i += String.valueOf(repCount).length() + 1;

                StringHelper helper = expandString(s, i, repCount);
                String expandedString = helper.text;
                result.append(expandedString);

                i = helper.nextIndex;
            } else {
                result.append(sChar);
                i++;
            }
        }

        return result.toString();
    }

    private static boolean isNumber(char sChar) {
        return sChar >= '0' && sChar <= '9';
    }

    private static int parseNumber(String s, int index) {
        StringBuilder numStr = new StringBuilder();
        while (index < s.length()) {
            char sChar = s.charAt(index);
            if (isNumber(sChar)) {
                numStr.append(sChar);
            } else {
                break;
            }

            index++;
        }

        int num;
        try {
            num = Integer.parseInt(numStr.toString());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Not a number: " + e.toString());
        }

        return num;
    }

    private static StringHelper expandString(String s, int index, int repCount) {
        // TODO - validate input - assume correct

        int originalIndex = index;

        StringBuilder expandedString = new StringBuilder();
        while (index < s.length() && s.charAt(index) != ']') {
            if (isNumber(s.charAt(index))) {
                int subRepCount = parseNumber(s, index);

                // move index ahead of number, + 1 to consider '[' bracket.
                // can refactor StringHelper.
                index += String.valueOf(subRepCount).length() + 1;

                StringHelper helper = expandString(s, index, subRepCount);
                expandedString.append(helper.text);
                index = helper.nextIndex;
            } else {
                expandedString.append(s.charAt(index));
                index++;
            }
        }

        index++; // for the closing ']'

        StringHelper helper = new StringHelper();
        helper.text = expandedString.toString().repeat(repCount);
        helper.nextIndex = index;

        System.out.println("expandedString: " + helper.text);
        System.out.println("index: " + originalIndex);
        System.out.println("nextIndex: " + helper.nextIndex);

        return helper;
    }

    private static class StringHelper {
        public String text;
        public int nextIndex;
    }
}
