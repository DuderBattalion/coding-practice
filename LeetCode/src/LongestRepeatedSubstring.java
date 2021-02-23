import com.leetcode.util.SuffixArray;

import java.util.HashSet;
import java.util.Set;

public class LongestRepeatedSubstring {

    public static void main(String[] args) {
        System.out.println(longestRepeatingSubstring("aabcaabdaab"));
    }

    public static int longestRepeatingSubstring(String s) {
        SuffixArray suffixArray = new SuffixArray(s);

        int maxLcp = Integer.MIN_VALUE;
        for (int i = 1; i < suffixArray.length(); i++) {
            int lcp = suffixArray.lcp(i);
            if (lcp > maxLcp) {
                maxLcp = lcp;
            }
        }

        return maxLcp;
    }
}
