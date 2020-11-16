
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PalindromePairs {
    public static void main(String[] args) {
//        String[] words = { "abcd","dcba","lls","s","sssll" };
//        String[] words = { "bat", "tab", "cat" };
//        String[] words = { "a", "" };
        String[] words = { "", ""};

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

        Set<Integer> emptyWordIndices = new HashSet<>();
        Set<Integer> nonEmptyWordIndices = new HashSet<>();

        ModifiedTrie reverseWordPrefixes = new ModifiedTrie();
        for (int i = 0; i < words.length; i++) {
            if (words[i].isEmpty()) {
                emptyWordIndices.add(i);
                continue;
            }

            reverseWordPrefixes.insert(reverseWord(words[i]), i);
            nonEmptyWordIndices.add(i);
        }

        // Empty words can be added to all other words to make palindrome pairs
        for (int emptyWordIndex: emptyWordIndices) {
            for (int nonEmptyWordIndex: nonEmptyWordIndices) {
                List<Integer> result = new ArrayList<>();
                result.add(emptyWordIndex);
                result.add(nonEmptyWordIndex);

                results.add(result);

                result = new ArrayList<>();
                result.add(nonEmptyWordIndex);
                result.add(emptyWordIndex);

                results.add(result);
            }
        }

        for (int i = 0; i < words.length; i++) {
            if (words[i].isEmpty()) {
                continue; // TODO - can be replaced with nonEmptyIndices set loop
            }

            ModifiedTrieNode node = reverseWordPrefixes.search(words[i]);
            if (node != null) {
                List<Integer> result = new ArrayList<>();
                if (node.isWord && node.wordIndex != i) {
                    result.add(i);
                    result.add(node.wordIndex);

                    results.add(result);
                }

                List<Integer> childPalindromeIndices = node.getChildPalindromeIndices();
                for (int childIndex: childPalindromeIndices) {
                    result = new ArrayList<>();
                    result.add(i);
                    result.add(childIndex);

                    results.add(result);
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

                if (isPalindrome(word, i+1, word.length() - 1)) {
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
        if (start < 0 || start >= word.length() || end < 0 || end >= word.length()) {
            return false;
        }

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

//    private static void addResultIfValid(List<List<Integer>> results, String word,
//                                  String reverseWord, Map<String, Integer> wordIndexes) {
//       Integer wordIndex = wordIndexes.get(word);
//       if (wordIndex == null) {
//           return;
//       }
//
//       Integer reverseWordIndex = wordIndexes.get(reverseWord);
//       if (reverseWordIndex == null) {
//           return;
//       }
//
//       List<Integer> result = new ArrayList<>();
//       result.add(wordIndex);
//       result.add(reverseWordIndex);
//
//       results.add(result);
//    }
}
