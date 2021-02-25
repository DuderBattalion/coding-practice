import java.math.BigInteger;
import java.util.*;

public class LongestDuplicateSubstringRabinKarp {
    private static final long prime = BigInteger.probablePrime(63, new Random()).longValue();
    private static final long mod = BigInteger.probablePrime(63, new Random()).longValue();
    private static final int radix = 256;

    public static void main(String[] args) {
        System.out.println(longestDupSubstring("nnpxouomcofdjuujloanjimymadkuepightrfodmauhrsy"));
    }


    /**
     * Algorithm
     * Longest substring length lies between 1 .. N
     * Guess longest substring length using binary search
     * For each guessed length, search if duplicates exist
     * If exists, binary search moves right, else left.
     */
    public static String longestDupSubstring(String s) {
        int sLen = s.length();
        int[] stringNums = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            stringNums[i] = Character.getNumericValue(s.charAt(i)) - Character.getNumericValue('a');
        }


        int left = 0, right = sLen;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int matchIndex = search(s, mid, sLen, stringNums);
            if (matchIndex < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        int longestSubstringLength = left - 1;
        int matchIndex = search(s, longestSubstringLength, sLen, stringNums);
        return s.substring(matchIndex, matchIndex + longestSubstringLength);
    }

    /**
        Rabin-Karp with polynomial rolling hash.
        Search a substring of given length
        that occurs at least 2 times.
        Return start position if the substring exits and -1 otherwise.
      */
    public static int search(String s, int substringLength, int stringLength, int[] stringNums) {
        // compute the hash of string S[:L]
        long h = 0;
        for(int i = 0; i < substringLength; ++i) {
            h = (h * radix + stringNums[i]) % mod;
        }

        // already seen hashes of strings of length L
        Map<Long, List<SeenVal>> seen = new HashMap<>();
        List<SeenVal> seenVals = new ArrayList<>();
        seenVals.add(new SeenVal(h, 0, substringLength));
        seen.put(h, seenVals);

        // const value to be used often : a**L % modulus
        long constVal = 1;
        for (int i = 1; i <= substringLength; ++i) {
            constVal = (constVal * radix) % mod;
        }

        for(int start = 1; start < stringLength - substringLength + 1; ++start) {
            // compute rolling hash in O(1) time
            h = (h * radix - stringNums[start - 1] * constVal % mod + mod) % mod;
            h = (h + stringNums[start + substringLength - 1]) % mod;

            if (seen.containsKey(h)) {
                List<SeenVal> existingVals = seen.get(h);

                boolean isMatch = false;
                for (SeenVal existingVal: existingVals) {
                    String existing = s.substring(existingVal.i, existingVal.j);
                    String current = s.substring(start, start + substringLength);

                    if (existing.equals(current)) {
                        isMatch = true;
                        break;
                    }
                }

                if (isMatch) {
                    return start;
                } else {
                    existingVals.add(new SeenVal(h, start, start + substringLength));
                }
            }

            List<SeenVal> newVals = new ArrayList<>();
            SeenVal newVal = new SeenVal(h, start, start + substringLength);
            newVals.add(newVal);
            seen.put(h, newVals);
        }

        return -1;
    }

    private static class SeenVal {
        long val;
        int i;
        int j;

        public SeenVal(long val, int i, int j) {
            this.val = val;
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SeenVal seenVal = (SeenVal) o;
            return val == seenVal.val;
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, i, j);
        }
    }
}
