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

//        String s = "pineapplepenapple";
//        List<String> wordDict = new ArrayList<>();
//        wordDict.add("fffff");

        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("a");
        wordDict.add("aa");
        wordDict.add("aaa");
        wordDict.add("aaaa");
        wordDict.add("aaaaa");
        wordDict.add("aaaaaa");
        wordDict.add("aaaaaaa");
        wordDict.add("aaaaaaaa");
        wordDict.add("aaaaaaaaa");
        wordDict.add("aaaaaaaaaa");

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
    }
}
