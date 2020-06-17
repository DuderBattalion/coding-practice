import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegerToRoman {
    public static void main(String[] args) {
        System.out.println(intToRoman(1010));
    }

    public static String intToRoman(int num) {
        var charMap = initRomanCharMap();

        String numStr = String.valueOf(num);

        // TODO - Ideally, this should have number checks to make sure no cast issues
        // But problem statements guarantees numbers between 1 - 3999, which are fine to cast
        int multiplier = (int) Math.pow(10, (numStr.length() - 1));

        int digit;
        List<String> outputTokens = new ArrayList<String>();

        for (int i = 0; i < numStr.length(); i++) {
            digit = Character.getNumericValue(numStr.charAt(i));
            if (digit == 0) {
                multiplier = multiplier/10;
                continue;
            }

            digit = digit * multiplier;
            outputTokens.add(charMap.get(digit));
            multiplier = multiplier/10;
        }

        StringBuilder output = new StringBuilder();
        outputTokens.forEach(output::append);

        return output.toString();
    }

    private static Map<Integer, String> initRomanCharMap() {
        var map = new HashMap<Integer, String>();

        map.put(1, "I");
        map.put(2, "II");
        map.put(3, "III");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(6, "VI");
        map.put(7, "VII");
        map.put(8, "VIII");
        map.put(9, "IX");

        map.put(10, "X");
        map.put(20, "XX");
        map.put(30, "XXX");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(60, "LX");
        map.put(70, "LXX");
        map.put(80, "LXXX");
        map.put(90, "XC");

        map.put(100, "C");
        map.put(200, "CC");
        map.put(300, "CCC");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(600, "DC");
        map.put(700, "DCC");
        map.put(800, "DCCC");
        map.put(900, "CM");

        map.put(1000, "M");
        map.put(2000, "MM");
        map.put(3000, "MMM");

        return map;
    }
}
