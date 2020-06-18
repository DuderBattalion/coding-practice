import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RomanToInteger {
    public static void main(String[] args) {
        System.out.println(romanToInt("IX"));
    }

    public static int romanToInt(String s) {
        var romanToIntMap = initRomanToIntMap();

        int i = 0;
        int numVal = 0;

        while (i < s.length()) {
            Optional<String> twoChars = getNextTwoChars(s, i);
            if (twoChars.isPresent() && romanToIntMap.containsKey(twoChars.get())) {
                numVal += romanToIntMap.get(twoChars.get());
                i += 2;
            } else {
                numVal += romanToIntMap.get(String.valueOf(s.charAt(i)));
                i++;
            }
        }

        return numVal;
    }

    private static Map<String, Integer> initRomanToIntMap() {
        var map = new HashMap<String, Integer>();

        map.put("I", 1);

        map.put("IV", 4);
        map.put("V", 5);

        map.put("IX", 9);
        map.put("X", 10);

        map.put("XL", 40);
        map.put("L", 50);

        map.put("XC", 90);
        map.put("C", 100);

        map.put("CD", 400);
        map.put("D", 500);

        map.put("CM", 900);
        map.put("M", 1000);

        return map;
    }

    private static Optional<String> getNextTwoChars(String s, int i) {
        if (i + 1 >= s.length()) {
            return Optional.empty();
        }

        return Optional.of(
                String.valueOf(s.charAt(i))
                + String.valueOf(s.charAt(i + 1)));
    }
}
