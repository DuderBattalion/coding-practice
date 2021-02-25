import java.util.HashSet;
import java.util.Set;

public class LongestDuplicateSubstringRabinKarp {
    private static int prime = 101;

    public static void main(String[] args) {
        System.out.println(longestDupSubstring("xyzabcuioabcefg"));
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
        char[] sChars = s.toCharArray();

        int left = 0, right = sLen;
        String longestDuplicate = "";
        while (left <= right) {
            int mid = left + (right - left) / 2;
            String duplicate = findDuplicate(s, mid);
            if (duplicate.isEmpty()) {
                right = mid - 1;
            } else {
                left = mid + 1;

                if (duplicate.length() > longestDuplicate.length()) {
                    longestDuplicate = duplicate;
                }
            }
        }

        return longestDuplicate;
    }

    private static String findDuplicate(String s, int substringLength) {
        if (substringLength <= 0) {
            return "";
        }

        char[] sChars = s.toCharArray();
        Set<Long> hashCache = new HashSet<>();

        long initHash = createHash(sChars, substringLength);
        hashCache.add(initHash);

        long oldHash = initHash;
        int matchIndex = -1;
        for (int i = 1; i <= s.length() - substringLength; i++) {
            long newHash = recalculateHash(sChars, i - 1,
                    (i - 1) + substringLength, oldHash, substringLength);

//            String subStr = s.substring(i, (i - 1) + substringLength + 1);
//            System.out.println(subStr + ": " + newHash);

            if (hashCache.contains(newHash)) { // TODO - Fix collision event
                matchIndex = i;
                break;
            }

            hashCache.add(newHash);
            oldHash = newHash;
        }
        return matchIndex >= 0
                ? s.substring(matchIndex, matchIndex + substringLength) : "";
    }

    // Rabin karp
    private static long recalculateHash(char[] str, int oldIndex, int newIndex,
                                        long oldHash, int patternLen) {
        long newHash = oldHash - str[oldIndex];
        newHash = newHash/prime;
        newHash += str[newIndex]*Math.pow(prime, patternLen - 1);
        return newHash;
    }

    private static long createHash(char[] str, int end){
        long hash = 0;
        for (int i = 0 ; i < end; i++) {
            hash += str[i]*Math.pow(prime,i);
        }
        return hash;
    }
}
