import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning2 {
    public static void main(String[] args) {
        System.out.println(minCut("aab"));
        System.out.println(minCut("banana"));
        System.out.println(minCut("aabbbcccc"));
    }

    public static int minCut(String s) {
        // [i][j] represents a substring where i = start char, j = end char
        // dp[i][j] stores whether substring(i,j) is a palindrome or not.
        boolean[][] palindromeDp = new boolean[s.length()][s.length()];
        initDp(palindromeDp, s);

        // minCutDp[i] where i stores the minimum cuts required for substring length being considered.
        // For string "banana", minCutDp[3] would store minimum cuts for substring "ban"
        // such that all partitions are palindromes.
        int[] minCutDp = new int[s.length()];

        // Algorithm:
        // Disclaimer: See discussion on https://www.youtube.com/watch?v=WPr1jDh3bUQ&ab_channel=IDeserve
        //
        // What is a palindrome?
        // 1) First and last char match
        // 2) Recursive - internal string is a palindrome
        // Ex: "RadaR" - "R-R" match, internal string "ada" is a palindrome (recursively found)
        //
        // Having calculated the palindromeDp table, we now need to fill out the minCutDp table
        // Calculate minCutDp[i] from left to right, leaning on previous cached values
        // If minCutDp[i] is a palindrome, then minCutDp[i] = 0
        // If not, then here's the tricky but effective way to reason about this dp recurrence formula.
        // minCutDp[i] = 1 + minCutDp[j], at such a point where substring(j+1, i) will be a palindrome
        // ex: "For string, "abradar" - j = 1, partitioning "ab" | "radar"
        // and since j is always less than i, minCutDp[j] would have already been calculated.
        //
        // And, more so, the substring(j+1, i) being a palindrome condition is ALWAYS satisfied,
        // since you get to a point where only one character remains, which is going to be a palindrome.
        // Consider "abcd" - solution would be at j=2, "abc" | "d"
        // we already have the value for minCutDp[2] ("abc") and "d" is a palindrome.

        // minCutDp[i]
        for (int i = 0; i < s.length(); i++) {
            if (palindromeDp[0][i]) {
                minCutDp[i] = 0;
                continue;
            }

            // Calculate min of cuts from 0 to i
            int minCut = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (minCutDp[j] < minCut && palindromeDp[j+1][i]) { // Note: Will be true atleast once
                    minCut = 1 + minCutDp[j];
                }
            }

            minCutDp[i] = minCut;
        }

        return minCutDp[s.length() - 1];
    }

    private static void initDp(boolean[][] palindromeDp, String s) {
        // Note: Initializing in this order is important
        // For palindromes with length more than 2, these need to be populated
        // to act as caches for longer values

        // Single characters are always palindromes
        for (int i = 0; i < s.length(); i++) {
            palindromeDp[i][i] = true;
        }

        // Two characters, check if first and last characters match
        for (int i = 0; i < s.length() - 1; i++) {
            palindromeDp[i][i+1] = (s.charAt(i) == s.charAt(i+1));
        }

        // Greater than two characters, use previously cached values to fill out dp table
        // Note: Order of filling palindromeDp table is important
        // First calculate 3 letter substrings, then 4 and so on.
        for (int subStringLen = 3; subStringLen <= s.length(); subStringLen++) {
            for (int i = 0; i < (s.length() - subStringLen + 1); i++) {
                int j = i + subStringLen - 1;
                if (s.charAt(i) == s.charAt(j) && palindromeDp[i+1][j-1]) {
                    palindromeDp[i][j] = true;
                }
            }
        }
    }
}
