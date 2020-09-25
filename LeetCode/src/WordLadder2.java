import java.util.*;

public class WordLadder2 {
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";

        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        List<List<String>> results = findLadders(beginWord, endWord, wordList);
        for (List<String> result: results) {
            for (String word: result) {
                System.out.print(word + ", ");
            }

            System.out.println();
        }
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> results = new ArrayList<>();
        if (!wordList.contains(endWord)) {
            return results;
        }

//        Set<String> wordSet = new HashSet<>(wordList);
//        findLaddersRecursive(beginWord, endWord, wordSet, results, new ArrayList<>(), new HashSet<>());

        Set<String> wordSet = new HashSet<>(wordList);
        List<Character> allChars = createAllCharsList(wordList);

        List<String> result = new ArrayList<>();
        Map<String, List<String>> cache = new HashMap<>(); // TODO - caching

        for (int i = 0; i < beginWord.length(); i++) {
            for (Character token: allChars) {
                String newWord = replaceWordChar(beginWord, token, i);

                if (wordSet.contains(newWord)) {

                }
            }
        }

        return results;
    }

    // TODO - Performance improve - memoization
    private static void findLaddersRecursive(String beginWord, String endWord, Set<String> wordList,
                                                           List<List<String>> results, List<String> wordLadder,
                                                           Set<String> cache) {
        if (beginWord.equals(endWord)) {
            results.add(wordLadder);
            return;
        }

        if (cache.contains(beginWord)) {
            return;
        } else {
            cache.add(beginWord);
        }

        // Try replacing each character in begin word
        for (int i = 0; i < beginWord.length(); i++) {
            char endChar = endWord.charAt(i);
            String newBeginWord = replaceWordChar(beginWord, endChar, i);

            System.out.println("[Debug]: new word = " + newBeginWord);

            if (wordList.contains(newBeginWord)) {
                wordLadder.add(newBeginWord);
                findLaddersRecursive(newBeginWord, endWord, wordList, results, wordLadder, cache);

                // Backtrack
                wordLadder.remove(newBeginWord);
            }
        }
    }

    private static String replaceWordChar(String word, char token, int i) {
        return (word.substring(0, i) + token + word.substring(i+1));
    }
}
