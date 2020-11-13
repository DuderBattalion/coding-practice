
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromePairs {
    public static void main(String[] args) {

    }

//    public List<List<Integer>> palindromePairs(String[] words) {
//        ModifiedTrie reverseWordPrefixes = new ModifiedTrie();
//        Map<String, Integer> wordIndexes = new HashMap<>();
//
//        for (int i = 0; i < words.length; i++) {
//            reverseWordPrefixes.insert(reverseWord(words[i]));
//            wordIndexes.put(words[i], i);
//        }
//
//        List<List<Integer>> results = new ArrayList<>();
//        for (String word: words) {
//            String reverseWord = reverseWord(word);
//            addResultIfValid(results, word, reverseWord, wordIndexes);
//
//            ModifiedTrieNode node = reverseWordPrefixes.search(reverseWord);
//            for (String palindromes: node.getChildPalindromes()) {
//                addResultIfValid(result, word, reverseWord, wordIndexes);
//            }
//        }
//
//        return results;
//    }

    private static void addResultIfValid(List<List<Integer>> results, String word,
                                  String reverseWord, Map<String, Integer> wordIndexes) {
       Integer wordIndex = wordIndexes.get(word);
       if (wordIndex == null) {
           return;
       }

       Integer reverseWordIndex = wordIndexes.get(reverseWord);
       if (reverseWordIndex == null) {
           return;
       }

       List<Integer> result = new ArrayList<>();
       result.add(wordIndex);
       result.add(reverseWordIndex);

       results.add(result);
    }
}
