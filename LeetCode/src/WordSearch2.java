import com.leetcode.util.Trie;

import java.util.*;

public class WordSearch2 {
    public static void main(String[] args) {
//        char[][] board ={
//                {'o','a','a','n'},
//                {'e','t','a','e'},
//                {'i','h','k','r'},
//                {'i','f','l','v'}
//        };

//        char[][] board = { };

//        String[] words = { "oath","pea","eat","rain" };

        char[][] board = {
                {'b','b','a','a','b','a'},
                {'b','b','a','b','a','a'},
                {'b','b','b','b','b','b'},
                {'a','a','a','b','a','a'},
                {'a','b','a','a','b','b'}
        };

        String[] words = { "abbbababaa" };

        List<String> output = findWords(board, words);
        for (String word: output) {
            System.out.print(word + ", ");
        }
    }

    public static List<String> findWords(char[][] board, String[] words) {
        Trie wordPrefixes = new Trie();
        for (String word: words) {
            wordPrefixes.insert(word);
        }

        Set<String> output = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                recursiveFindWord(board, wordPrefixes, Arrays.asList(words), i, j,
                        new StringBuilder(), output, new HashSet<>());
            }
        }

        return new ArrayList<>(output);
    }

    private static void recursiveFindWord(char[][] board, Trie wordPrefixes, List<String> words, int i, int j,
                                          StringBuilder word, Set<String> output, Set<String> visitedNodes) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || isVisited(i, j, visitedNodes)) {
            return;
        }

        markVisited(i, j, visitedNodes);

        word.append(board[i][j]);
        if (!wordPrefixes.searchPrefix(word.toString())) {
            backtrack(word, visitedNodes, i, j);
            return;
        }

        if (words.contains(word.toString())) {
            output.add(word.toString());
        }

        recursiveFindWord(board, wordPrefixes, words, i - 1, j, word, output, visitedNodes); // Go up
        recursiveFindWord(board, wordPrefixes, words, i, j + 1, word, output, visitedNodes); // Go right
        recursiveFindWord(board, wordPrefixes, words, i + 1, j, word, output, visitedNodes); // Go down
        recursiveFindWord(board, wordPrefixes, words, i, j - 1, word, output, visitedNodes); // Go left

        backtrack(word, visitedNodes, i, j);
    }

    private static boolean isVisited(int i, int j, Set<String> visitedNodes) {
        return visitedNodes.contains(i + "," + j);
    }

    private static void markVisited(int i, int j, Set<String> visitedNodes) {
        visitedNodes.add(i + "," + j);
    }

    private static void unmarkVisited(int i, int j, Set<String> visitedNodes) {
        visitedNodes.remove(i + "," + j);
    }

    private static void backtrack(StringBuilder word, Set<String> visitedNodes, int i, int j) {
        word.deleteCharAt(word.length() - 1);
        unmarkVisited(i, j, visitedNodes);
    }
}
