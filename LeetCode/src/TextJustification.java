import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    public static void main(String[] args) {

    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> output = new ArrayList<>();

        if (words.length == 0) {
            return output;
        }

        boolean isLastLine = false;
        int currWord = 0;
        while (!isLastLine) {
            isLastLine = checkIfLastLine(words, currWord);
            if (isLastLine) {
                // TODO - left align
                generateLastLine(words, currWord);
            }

            WordsInLine wordsInLine = getNumWordsInLine(words, currWord, maxWidth);
            String line = generateLine(wordsInLine, maxWidth);
            output.add(line);

            currWord += wordsInLine.words.size();
        }
    }

    private WordsInLine getNumWordsInLine(String[] words, int currWord, int maxWidth) {
        WordsInLine wordsInLine = new WordsInLine();
        while(wordsInLine.wordsLength < maxWidth) {
            String word = words[currWord];
            if (wordsInLine.wordsLength + word.length() + 1 <= maxWidth) { // + 1 for space
                wordsInLine.words.add(word);
                wordsInLine.wordsLength += word.length();
            } else {
                break;
            }
        }
    }

    private static class WordsInLine {
        public List<String> words;
        public int wordsLength;

        public WordsInLine() {
            this.words = new ArrayList<>();
            this.wordsLength = 0;
        }
    }
}
