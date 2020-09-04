import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ScrambleString {
    public static void main(String[] args) {
//        System.out.println(isScramble("great", "rgeat"));
//        System.out.println(isScramble("abcde", "caebd"));
//        System.out.println(isScramble("abcdbdacbdac", "bdacabcdbdac"));
//        System.out.println(isScramble("abb", "bba"));
        System.out.println(isScramble("abcdbdacbdac", "bdacabcdbdac"));

//        System.out.println(isScramble("", "caebd"));
//        System.out.println(isScramble("abcde", ""));
//        System.out.println(isScramble("", ""));
    }

    /**
     * Algorithm: Recursion + Memoization
     * Split at all index points - left and right
     * Two choices after each split.
     * Case 1: Don't swap the splits and recurse further down each substring from the split
     * Case 2: Swap the splits and then recurse further down each substring
     * If the substrings match, return true. Both left and right recursions need to be true for a complete match
     */
    public static boolean isScramble(String s1, String s2) {
        return isScramble(s1, s2, new HashSet<>());
    }

    private static boolean isScramble(String s1, String s2, Set<String> memoCache) {
        if (s1.equals(s2)) {
            return true;
        }

        String memoKey = s1 + "," + s2;
        if (memoCache.contains(memoKey)) {
            return false;
        } else {
            memoCache.add(memoKey);
        }

        if (!matchCharCounts(s1, s2)) {
            return false;
        }

        boolean isScrambled = false;
        for (int i = 1; i < s1.length(); i++) {
            isScrambled =
                    (isScramble(s1.substring(0, i), s2.substring(0, i), memoCache) // No swap
                            && isScramble(s1.substring(i), s2.substring(i), memoCache))
                    ||(isScramble(s1.substring(0, i), s2.substring(s2.length() - i), memoCache) // Swap
                            && isScramble(s1.substring(i), s2.substring(0, s2.length() - i), memoCache));

            if (isScrambled) {
                break;
            }
        }

        return isScrambled;
    }

    private static boolean matchCharCounts(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        String s1Sorted = sortString(s1);
        String s2Sorted = sortString(s2);

        return s1Sorted.equals(s2Sorted);
    }

    private static String sortString(String str) {
        if (str.isEmpty()) {
            return str;
        }

        char[] strArr = str.toCharArray();
        Arrays.sort(strArr);

        return new String(strArr);
    }
}
