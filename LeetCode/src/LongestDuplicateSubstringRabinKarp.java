public class LongestDuplicateSubstringRabinKarp {
    public static void main(String[] args) {
        System.out.println(longestDupSubstring("banana"));
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

        String duplicate = "";
        for (int i = 0; i < s.length() - substringLength; i++) {
            int j = i + substringLength - 1;
//            String substring = cache[i][j];
            String substring = s.substring(i, j + 1);
            if (s.indexOf(substring, i + 1) > 0) {
                duplicate = substring;
            }
        }

        return duplicate;
    }
}
