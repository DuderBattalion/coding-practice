public class DistinctSubsequences {
    public static void main(String[] args) {
        System.out.println(numDistinct("rabbbit", "rabbit"));
        System.out.println(numDistinct("babgbag", "bag"));

        System.out.println(numDistinct("", "bag"));
        System.out.println(numDistinct("abcde", ""));
        System.out.println(numDistinct("", ""));
    }

    public static int numDistinct(String s, String t) {
        if (s.length() < t.length()) {
            return 0;
        }

        return calculateNumDistinct(s, t, 0, 0, "");
    }

    private static int calculateNumDistinct(String s, String t, int i, int count, String subSequence) {
        if (i >= s.length()) {
            return count;
        }

        // Cases:
        // 1) Skip s.charAt(i)
        // 2) Include s.charAt(i)

        // Case 1: Skip s.charAt(i)
        count = calculateNumDistinct(s, t, i + 1, count, subSequence);

        // Case 2: Include s.charAt(i)
        subSequence += s.charAt(i);
        if (subSequence.equals(t)) {
            count++;
        }
        count = calculateNumDistinct(s, t, i + 1, count, subSequence);

        return count;
    }
}
