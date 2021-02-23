import com.leetcode.util.SuffixArray;

public class EncodeStringWithShortestLength {

    /**
     * Algorithm
     * Build suffix array
     * For each substring, check if
     * - Has LRS
     * - Is composed of LRS x x repetitions
     * - If so, compress it
     * Keep doing this till no change.
     */
    public static String encode(String s) {
        SuffixArray suffixArray = new SuffixArray(s);

        String encodedString = "";
        for (SuffixArray.Suffix suffix: suffixArray.suffixes) {
            String lrs = findLrs(suffix.toString());
            if (lrs.length() < 2) {
                continue;
            }

            String substring = suffix.toString();
            int repetition = calculateLrsRepetition(substring, lrs);
            if (repetition > 1) {
                String encodedLrs = encode(lrs);
                String encodedSubstring = String.format("%d[%s]", repetition, encodedLrs);
                encodedString = s.replaceAll(substring, encodedSubstring);

                break;
            }
        }

        if (encodedString.isEmpty()) {
            encodedString = s;
        } else {
            encodedString = encode(encodedString);
        }

        return encodedString;
    }

    private static String findLrs(String s) {
        SuffixArray suffixArray = new SuffixArray(s);

        int maxLcp = Integer.MIN_VALUE;
        String lrs = "";
        for (int i = 1; i < suffixArray.length(); i++) {
            int lcp = suffixArray.lcp(i);
            if (lcp > maxLcp) {
                maxLcp = lcp;
                lrs = suffixArray.select(i).substring(0, lcp);
            }
        }

        return lrs;
    }

    private static int calculateLrsRepetition(String substring, String lrs) {
        if (substring.length() % lrs.length() != 0) {
            return 1;
        }

        int repetition = substring.length() / lrs.length();
        String repeatedLrs = lrs.repeat(repetition);

        if (!repeatedLrs.equals(substring)) {
            repetition = 1;
        }

        return repetition;
    }

    public static void main(String[] args) {
        String s = "abcabcdabcdabc";
        System.out.println(encode(s));
    }
}