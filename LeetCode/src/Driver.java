import com.leetcode.util.FenwickTreeSum;

import java.util.regex.Pattern;

public class Driver {
    public static void main(String[] args) {
        String s = "2[abbb]c2[abbb]c";
        String substring = "2[abbb]c2[abbb]c";
        String encodedString = "2[2[abbb]c]";

        String print = s.replaceAll(Pattern.quote(substring), encodedString);
        System.out.println(print);
    }
}
