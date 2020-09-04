public class DistinctSubsequences {
    public static void main(String[] args) {
//        System.out.println(numDistinct("rabbbit", "rabbit"));
        System.out.println(numDistinct("babgbag", "bag"));
    }

    public static int numDistinct(String s, String t) {
        if (s.length() < t.length()) {
            return 0;
        }

        return calculateNumDistinct(s, t, 0, 0, "");
    }

    private static int calculateNumDistinct(String s, String t, int i, int count, String subSequence) {
        if (subSequence.equals(t)) {
            count++;
        }

        if (i >= s.length()) {
            return count;
        }

        // Cases:
        // 1) Skip s.charAt(i)
        // 2) Include s.charAt(i)
        char token = s.charAt(i);

        // Case 1: Skip s.charAt(i)
        count = calculateNumDistinct(s, t, i + 1, count, subSequence);

        // Case 2: Include s.charAt(i)
        count = calculateNumDistinct(s, t, i + 1, count, subSequence + token);

        return count;
    }
}
