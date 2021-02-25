import java.math.BigInteger;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LongestDuplicateSubstringRabinKarp {
    private static final long prime = BigInteger.probablePrime(31, new Random()).longValue();
    private static final long mod = BigInteger.probablePrime(31, new Random()).longValue();
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
            int matchIndex = search(mid, sLen, stringNums);
            if (matchIndex < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        int longestSubstringLength = left - 1;
        int matchIndex = search(longestSubstringLength, sLen, stringNums);
        return s.substring(matchIndex, matchIndex + longestSubstringLength);
    }

    /*
  Rabin-Karp with polynomial rolling hash.
      Search a substring of given length
      that occurs at least 2 times.
      Return start position if the substring exits and -1 otherwise.
      */
    public static int search(int substringLength, int stringLength, int[] stringNums) {
        // compute the hash of string S[:L]
        long h = 0;
        for(int i = 0; i < substringLength; ++i) {
            h = (h * radix + stringNums[i]) % mod;
        }

        // already seen hashes of strings of length L
        HashSet<Long> seen = new HashSet();
        seen.add(h);

        // const value to be used often : a**L % modulus
        long constVal = 1;
        for (int i = 1; i <= substringLength; ++i) {
            constVal = (constVal * radix) % mod;
        }

        for(int start = 1; start < stringLength - substringLength + 1; ++start) {
            // compute rolling hash in O(1) time
            h = (h * radix - stringNums[start - 1] * constVal % mod + mod) % mod;
            h = (h + stringNums[start + substringLength - 1]) % mod;
            if (seen.contains(h)) return start;
            seen.add(h);
        }
        return -1;
    }

//    private static String findDuplicate(String s, int substringLength) {
//        if (substringLength <= 0) {
//            return "";
//        }
//
//        char[] sChars = s.toCharArray();
//        Set<Long> hashCache = new HashSet<>();
//
//        long initHash = initHash(sChars, substringLength);
//        hashCache.add(initHash);
//
//        long oldHash = initHash;
//        int matchIndex = -1;
//        for (int i = 1; i <= s.length() - substringLength; i++) {
//            long rollingHash = rollingHash(sChars, oldHash, i - 1, substringLength);
//
////            String subStr = s.substring(i, (i - 1) + substringLength + 1);
////            System.out.println(subStr + ": " + rollingHash);
//
//            if (hashCache.contains(rollingHash)) { // TODO - Fix collision event
//                matchIndex = i;
//                break;
//            }
//
//            hashCache.add(rollingHash);
//            oldHash = rollingHash;
//        }
//        return matchIndex >= 0
//                ? s.substring(matchIndex, matchIndex + substringLength) : "";
//    }
//
//    // Rabin karp
//    private static long initHash(char[] chars, int size) {
//        StringBuffer buf = new StringBuffer();
//
//        long hash = 0;
//        for (int i = 0; i < size; i++) {
//            hash += (chars[i] * Math.pow(prime, i)) % mod;
//
//            buf.append(chars[i]);
//        }
//
//        System.out.println(buf.toString() + ": " + hash);
//
//        return hash;
//    }
//
//    private static boolean isEqual(char[] pattern, char[] text, int start) {
//        boolean isEqual = true;
//        for (int i = start; i < start + pattern.length; i++) {
//            if (pattern[i - start] != text[i]) {
//                isEqual = false;
//                break;
//            }
//        }
//
//        return isEqual;
//    }
//
//    private static long rollingHash(char[] textChars, long oldHash, int oldIndex, int patternLength) {
//        int newIndex = oldIndex + patternLength;
//
//        long newHash = oldHash - textChars[oldIndex];
//        newHash = newHash / prime;
//        newHash += (textChars[newIndex] * Math.pow(prime, patternLength - 1)) % mod;
//
//        StringBuffer buf = new StringBuffer();
//        for (int i = oldIndex + 1; i <= oldIndex + patternLength; i++) {
//            buf.append(textChars[i]);
//        }
//
//        System.out.println(buf.toString() + ": " + newHash);
//
//        return newHash;
//    }
}
