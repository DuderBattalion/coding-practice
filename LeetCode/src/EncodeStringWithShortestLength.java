import java.math.BigInteger;
import java.util.*;

class EncodeStringWithShortestLength {
    public static void main(String[] args) {
        System.out.println(encode("abbbabbbcabbbabbbc"));
    }

    // Algorithm
    // Since no obvious greedy solution, use DP.
    //
    // Three cases for each recursion
    // 1. Do nothing with the string
    // 2. Encode the entire string once
    // 3. Split the string into two, and combine the encodings
    //
    // Keep track of the min string formed. Return it at end.
    private static final long prime = BigInteger.probablePrime(63, new Random()).longValue();
    private static final long mod = BigInteger.probablePrime(63, new Random()).longValue();
    private static final int radix = 256;

    public static String encode(String s) {
        return encodeRecursive(s, new HashMap<String, String>());
    }

    private static  String encodeRecursive(String s, Map<String, String> cache) {
        if (cache.containsKey(s)) {
            return cache.get(s);
        }

        if (s.length() < 5) {
            cache.put(s, s);
            return s;
        }

        // Case 2: Encode string
        String stringEncoding = compressString(s);

        // Case 3: Split into substrings
        String minSubEncoding = "";
        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0, i);
            String right = s.substring(i);

            left = encodeRecursive(left, cache);
            right = encodeRecursive(right, cache);

            String encodedSubstring = left + right;
            if (minSubEncoding.isEmpty()) {
                minSubEncoding = encodedSubstring;
            } else if (encodedSubstring.length() < minSubEncoding.length()) {
                minSubEncoding = encodedSubstring;
            }
        }

        String minString = findMinString(s, stringEncoding, minSubEncoding);
        cache.put(s, minString);

        return minString;
    }

    private static String compressString(String s) {
        String repeatingSubstring = "";
        int index = (s + s).indexOf(s, 1);
        if (index >= 0) {
            repeatingSubstring = s.substring(0, index);
        }

        int repCount = s.length() / repeatingSubstring.length();
        String encodedString = String.format("%d[%s]", repCount, repeatingSubstring);

        return encodedString.length() < s.length() ? encodedString : s;
    }

    private static  String findMinString(String s, String stringEncoding, String minSubEncoding) {
        PriorityQueue<String> queue = new PriorityQueue<>(3, (a, b) -> a.length() - b.length());
        queue.offer(s);
        queue.offer(stringEncoding);
        queue.offer(minSubEncoding);

        return queue.peek();
    }


}