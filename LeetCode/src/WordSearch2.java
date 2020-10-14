import com.leetcode.util.Trie;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearch2 {
    public static void main(String[] args) {
        char[][] board ={
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };

        String[] words = { "oath","pea","eat","rain" };

        Set<String> output = findWords(board, words);
        for (String word: output) {
            System.out.print(word + " ,");
        }
    }

    public static Set<String> findWords(char[][] board, String[] words) {
        Trie wordPrefixes = new Trie();
        for (String word: words) {
            wordPrefixes.insert(word);
        }

        Set<String> output = new HashSet<>();
        recursiveFindWord(board, wordPrefixes, Arrays.asList(words), 0, 0, new StringBuilder(), output);

        return output;
    }

    private static void recursiveFindWord(char[][] board, Trie wordPrefixes, List<String> words, int i, int j,
                                          StringBuilder word, Set<String> output) {
        if (i < 0 || i > board.length || j < 0 || j > board[0].length) {
            return;
        }

        word.append(board[i][j]);
        if (!wordPrefixes.search(word.toString())) {
            return;
        }

        if (words.contains(word.toString())) {
            output.add(word.toString());
        }

        recursiveFindWord(board, wordPrefixes, words, i - 1, j, word, output); // Go up
        recursiveFindWord(board, wordPrefixes, words, i, j + 1, word, output); // Go right
        recursiveFindWord(board, wordPrefixes, words, i + 1, j, word, output); // Go down
        recursiveFindWord(board, wordPrefixes, words, i, j - 1, word, output); // Go left

        // Backtrack
        word.deleteCharAt(word.length() - 1);
    }
}
