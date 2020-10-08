import java.util.ArrayList;
import java.util.List;

public class WordBreak2 {
    public static void main(String[] args) {
//        String s = "catsanddog";
//        List<String> wordDict = new ArrayList<>();
//        wordDict.add("cat");
//        wordDict.add("cats");
//        wordDict.add("and");
//        wordDict.add("sand");
//        wordDict.add("dog");

//        String s = "pineapplepenapple";
//        List<String> wordDict = new ArrayList<>();
//        wordDict.add("apple");
//        wordDict.add("pen");
//        wordDict.add("applepen");
//        wordDict.add("pine");
//        wordDict.add("pineapple");

        String s = "pineapplepenapple";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("fffff");

        List<String> output = wordBreak(s, wordDict);
        for (String result: output) {
            System.out.println(result);
        }
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        List<String> output = new ArrayList<>();
        if (s.isEmpty()) {
            return output;
        }

        // i,j - substring is valid word from index i..j
        boolean[][] substringDp = new boolean[s.length()][s.length()];
        initSubstringDp(substringDp, s, wordDict);

        // DFS on substringDP
        // Start from 0th index, and build all possible paths till end index
//        for (int i = 0; i < s.length(); i++) {
//            List<String> result = recursiveFindWordPaths(substringDp, i, s);
//            if (!result.isEmpty()) {
//                output.addAll(result);
//            }
//        }

        recursiveFindWordPaths(substringDp, 0, s, new ArrayList<>(), output);

        return output;
    }

    private static void initSubstringDp(boolean[][] substringDp, String s, List<String> wordDict) {
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String substring = s.substring(i, j+1); // +1 because s.substring endIndex is excluded
                substringDp[i][j] = wordDict.contains(substring);
            }
        }
    }

    private static void recursiveFindWordPaths(boolean[][] substringDp, int i, String s,
                                                List<String> words, List<String> output) {
        if (i > s.length()) {
            return;
        }

        if (i == s.length()) {
//            words.add(s.substring(i, j));
            StringBuffer wordsStringBuf = new StringBuffer();
            for (String word: words) {
                wordsStringBuf.append(word);
                wordsStringBuf.append(" ");
            }

            String wordString = wordsStringBuf.toString().substring(0, wordsStringBuf.length() - 1); // Remove trailing space
            output.add(wordString);

            return;
        }

        for (int j = i; j < substringDp.length; j++) {
            if (!substringDp[i][j]) {
                continue;
            }

            words.add(s.substring(i, j+1)); // +1 for inclusive end index of substring
            recursiveFindWordPaths(substringDp, j+1, s, words, output);

            // Backtrack
            words.remove(words.size() - 1);
        }

//        int j = findNextValidIndex(substringDp, i);
//        if (j < 0) {
//            return;
//        }
//
//        words.add(s.substring(i, j+1)); // +1 for inclusive end index of substring
//        recursiveFindWordPaths(substringDp, j+1, s, words, output);
//
//        // Backtrack
//        words.remove(words.size() - 1);
    }

    private static int findNextValidIndex(boolean[][] substringDp, int row) {
        int nextIndex = -1;
        for (int j = 0; j < substringDp.length; j++) {
            if (substringDp[row][j]) {
                nextIndex = j;
                break;
            }
        }

        return nextIndex;
    }
}
