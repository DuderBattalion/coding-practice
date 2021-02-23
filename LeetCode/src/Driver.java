import com.leetcode.util.FenwickTreeSum;
import com.leetcode.util.RabinKarp;

import java.util.regex.Pattern;

public class Driver {
    public static void main(String[] args) {
        String text = "012345abc8910";
        String pattern = "abc";

        RabinKarp rabinKarp = new RabinKarp();
        System.out.println(rabinKarp.search(text, pattern));
    }
}
