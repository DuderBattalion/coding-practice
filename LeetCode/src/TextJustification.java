import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    public static void main(String[] args) {
        String[] words = { "This", "is", "an", "example", "of", "text", "justification." };
        int maxWidth = 16;

        List<String> justifiedText = fullJustify(words, maxWidth);
        for (String line: justifiedText) {
            System.out.println(line);
        }
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> output = new ArrayList<>();

        if (words.length == 0) {
            return output;
        }

        boolean isLastLine = false;
        int currWord = 0;
        while (true) {
            isLastLine = checkIfLastLine(words, currWord, maxWidth);
            if (isLastLine) {
                generateLastLine(output, words, currWord, maxWidth);
                break;
            }

            WordsInLine wordsInLine = getNumWordsInLine(words, currWord, maxWidth);
            String line = generateLine(wordsInLine, maxWidth);
            output.add(line);

            currWord += wordsInLine.words.size();
        }

        return output;
    }

    private static String generateLine(WordsInLine wordsInLine, int maxWidth) {
        StringBuilder line = new StringBuilder();
        for (String word: wordsInLine.words) {
            line.append(word);
            line.append(" ");
        }

        String lineText = line.toString();
        if (lineText.length() >= maxWidth) {
            lineText = lineText.substring(0, maxWidth);
        }

        return lineText;
    }

    private static boolean checkIfLastLine(String[] words, int currWord, int maxWidth) {
        boolean isLastLine = false;

        int wordsLength = 0;
        for (int i = currWord; i < words.length; i++) {
            wordsLength += words[i].length() + 1; // + 1 for space
        }

        wordsLength--; // Last space is not needed to fit on line

        if (wordsLength <= maxWidth) {
            isLastLine = true;
        }

        return isLastLine;
    }

    private static WordsInLine getNumWordsInLine(String[] words, int currWord, int maxWidth) {
        WordsInLine wordsInLine = new WordsInLine();
        while(currWord < words.length) {
            String word = words[currWord];
            if (wordsInLine.wordsLength + word.length() <= maxWidth) { // + 1 for space
                wordsInLine.words.add(word);
                wordsInLine.wordsLength += word.length();

                if (wordsInLine.wordsLength < maxWidth) {
                    wordsInLine.wordsLength++; // Add space only if not last word in line
                }

                currWord++;
            } else {
                break; // line is full
            }
        }

        return wordsInLine;
    }

    private static class WordsInLine {
        public List<String> words;
        public int wordsLength;

        public WordsInLine() {
            this.words = new ArrayList<>();
            this.wordsLength = 0;
        }
    }

    private static void generateLastLine(List<String> output, String[] words, int currWord, int maxWidth) {
        StringBuilder lastLine = new StringBuilder();
        for (int i = currWord; i < words.length; i++) {
            lastLine.append(words[currWord]);
            lastLine.append(" ");
        }

        String lastLineText = lastLine.toString();
        if (lastLineText.length() > maxWidth) {
            lastLineText = lastLineText.substring(0, maxWidth);
        } else {
            lastLineText = lastLineText + " ".repeat(maxWidth - lastLineText.length());
        }

        output.add(lastLineText);
    }

}
