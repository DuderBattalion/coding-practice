
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PalindromePairs {
    public static void main(String[] args) {
//        String[] words = { "abcd","dcba","lls","s","sssll" };
//        String[] words = { "bat", "tab", "cat" };
//        String[] words = { "a", "" };
//        String[] words = { "", ""};

        String[] words = { "a", "abc", "aba", ""};
//        String[] words = { "a","aa","aaa" };

        List<List<Integer>> palindromePairs = palindromePairs(words);
        for (List<Integer> palindromePair: palindromePairs) {
            for (int index: palindromePair) {
                System.out.print(index + ", ");
            }

            System.out.println();
        }
    }

    public static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> results = new ArrayList<>();

        ModifiedTrie reverseWordPrefixes = new ModifiedTrie();
        for (int i = 0; i < words.length; i++) {
            reverseWordPrefixes.insert(reverseWord(words[i]), i);
        }

        for (int i = 0; i < words.length; i++) {
//            // Empty words can be added to all other words to make palindrome pairs
//            if (words[i].isEmpty()) {
//                for (int palindromeIndex: reverseWordPrefixes.getPalindromeIndices()) {
//                    addResult(results, i, palindromeIndex);
//                    addResult(results, palindromeIndex, i);
//                }
//
//                continue;
//            }

//            processPrefixWithChildren(words[i], i, results, reverseWordPrefixes); // abcd + dcba
//
//            if (words[i].length() > 1) {
//                processPrefixOnly(words[i].substring(0, words[i].length() - 1), i, // abcd + cba
//                        results, reverseWordPrefixes);
//            }

            // Case 1: Prefix of string a + palindrome string a  + whole string b
            String word = words[i];
            for (int j = 1; j <= word.length(); j++) {
                String prefix = word.substring(0, j);
                ModifiedTrieNode node = reverseWordPrefixes.search(prefix);
                if (node == null || !node.isWord || node.wordIndex == i) {
                    continue;
                }

                String suffix = "";
                if (j < word.length()) {
                    suffix = word.substring(j, word.length());
                }

                if (isPalindrome(suffix, 0, suffix.length() - 1)) {
                    addResult(results, i, node.wordIndex);
                }
            }

            // Case 2: Whole word a + palindrome (b) + reverse prefix of b
            ModifiedTrieNode reversePrefix = reverseWordPrefixes.search(word);
            if (reversePrefix != null) {
//                if (reversePrefix.isWord) {
//                    addResult(results, i, reversePrefix.wordIndex);
//                }

                for (int childIndex: reversePrefix.getChildPalindromeIndices()) {
                    addResult(results, i, childIndex);
                }
            }
        }

        return results;
    }

    private static class ModifiedTrie {
        ModifiedTrieNode root;

        public ModifiedTrie() {
            this.root = new ModifiedTrieNode();
        }

        public void insert(String word, int wordIndex) {
            ModifiedTrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new ModifiedTrieNode();
                }

                if (isPalindrome(word, i, word.length() - 1)) {
                    node.addChildPalindrome(wordIndex);
                }

                node = node.children[index];
            }

            node.isWord = true;
            node.wordIndex = wordIndex;
        }

        public ModifiedTrieNode search(String key) {
            ModifiedTrieNode node = root;
            for (Character token: key.toCharArray()) {
                int index = token - 'a';
                if (node.children[index] == null) {
                    node = null;
                    break;
                }

                node = node.children[index];
            }

            return node;
        }

        public List<Integer> getPalindromeIndices() {
            return root.childPalindromeIndices;
        }
    }

    private static class ModifiedTrieNode {
        ModifiedTrieNode[] children;
        boolean isWord;
        int wordIndex;
        List<Integer> childPalindromeIndices;

        ModifiedTrieNode() {
            this.children = new ModifiedTrieNode[26];
            this.wordIndex = -1;
            childPalindromeIndices = new ArrayList<>();
        }

        public void addChildPalindrome(int childIndex) {
            childPalindromeIndices.add(childIndex);
        }

        public List<Integer> getChildPalindromeIndices() {
            return childPalindromeIndices;
        }
    }

    private static boolean isPalindrome(String word, int start, int end) {
        boolean isPalindrome = true;
        while (start < end) {
            if (word.charAt(start) != word.charAt(end)) {
                isPalindrome = false;
                break;
            }

            start++;
            end--;
        }

        return isPalindrome;
    }

    private static String reverseWord(String word) {
        return new StringBuilder(word).reverse().toString();
    }

    private static void addResult(List<List<Integer>> results, int first, int second) {
        if (results == null) {
            return;
        }

        List<Integer> result = new ArrayList<>();
        result.add(first);
        result.add(second);

        results.add(result);
    }

    private static void processPrefixOnly(String prefix, int wordIndex,
                                      List<List<Integer>> results,
                                      ModifiedTrie reverseWordPrefixes) {
        ModifiedTrieNode node = reverseWordPrefixes.search(prefix);
        if (node != null) {
            if (node.isWord && node.wordIndex != wordIndex) {
                addResult(results, wordIndex, node.wordIndex);
            }
        }
    }

    /**
     * 1st case : prefix of String a + latter part of a(palindrome) + whole String b = new palindrome.
     * 2nd case : whole String a + prefix of String b(palindrome) + suffix of String b = new palindrome.
     */
    private static void processPrefixWithChildren(String word, int wordIndex,
                                      List<List<Integer>> results,
                                      ModifiedTrie reverseWordPrefixes) {
//        ModifiedTrieNode node = reverseWordPrefixes.search(prefix);
//        if (node != null) {
//            if (node.isWord && node.wordIndex != wordIndex) {
//                addResult(results, wordIndex, node.wordIndex);
//            }
//
//            List<Integer> childPalindromeIndices = node.getChildPalindromeIndices();
//            for (int childIndex: childPalindromeIndices) {
//                addResult(results, wordIndex, childIndex);
//            }
//        }

//        // TODO - impelement both cases
//        // Case 1: Prefix of string a + Palindrome of a + whole string b
//        for (int i = 0; i < )
    }
}
