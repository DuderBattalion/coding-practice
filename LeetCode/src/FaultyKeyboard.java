import com.leetcode.util.SuffixArray;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Problem Statement:
 * Input: s = "I lik   to   xplor   univ rs ", dictionary  = {"like", "explore", "toe", "universe", "rse"}
 * Output:
 * ["I like to explore universe",
 * "I like toe xplor  universe",
 * "I like to explore univ rse",
 * "I like toe xplor  univ rse"]
 */
public class FaultyKeyboard {
  public static void main(String[] args) {
    Set<String> dict = new HashSet<>();
    initDict(dict);

    String s = "I lik   to   xplor   univ rs ";
    SuffixArray validWordSuffs = createSuffArrFromDict(dict);

    printValidSentences("", "", 0, s, validWordSuffs);
  }

  private static void initDict(Set<String> dict) {
    dict.add("like");
    dict.add("explore");
    dict.add("toe");
    dict.add("universe");
    dict.add("rse");
  }

  private static SuffixArray createSuffArrFromDict(Set<String> dict) {
    List<String> sentinels = Arrays.asList("!", "@", "#", "$", "%", "^", "&");

    StringBuilder buf = new StringBuilder();
    Iterator<String> dictIter = dict.iterator();
    String word;

    int i = 0;
    while (dictIter.hasNext()) {
      word = dictIter.next();
      buf.append(word);
      buf.append(sentinels.get(i));
      i++;
    }

    return new SuffixArray(buf.toString());
  }

  private static void printValidSentences(String sentence, String currWord, int i,
                                          String s, SuffixArray suffs) {
    if (i == s.length()) {
      System.out.println(sentence);
      return;
    }

    NextWord nextWord = getNextWord(s, i);

    // NextWord index can be ' ' or 'e'
    // If ' ', then currWord + nextWord needs to be a valid word
    // If 'e', then recurse over currWord + nextWord + 'e'

    // Case ' '
    // If currWord + nextWord is a valid prefix, keep going, Else, return.
    nextWord.word = currWord + nextWord.word;
    if (isValidPrefix(nextWord.word, suffs)) {
      printValidSentences(sentence + nextWord.word, "",
          nextWord.index + 1, s, suffs);
    } else {
      return;
    }

    // Case 'e'
    printValidSentences(sentence + currWord + nextWord.word + 'e',
        nextWord.word + 'e', i + 1, s, suffs);
  }


  private static NextWord getNextWord(String s, int start) {
    NextWord nextWord = new NextWord();

    int i = getNextSpace(s, start);
    if (i < 0) {
      return nextWord;
    }

    nextWord.word = s.substring(start, i);
    return nextWord;
  }

  /**
   * Reads ahead from start index until it encounters a space
   * The space can be a word terminator, or a faulty 'e'
   * @return index of next space
   */
  private static int getNextSpace(String s, int start) {
    int i = -1;
    for (i = start; i < s.length(); i++) {
      if (s.charAt(i) == ' ') {
        break;
      }
    }

    return i;
  }

  private static class NextWord {
    public int index;
    public String word;

    public NextWord() {
      index = -1;
      word = "";
    }
  }

  private static boolean isValidPrefix(String word, SuffixArray suffs) {
    boolean isPrefix = false;
    String suff;
    for (int i = 0; i < suffs.length(); i++) {
      suff = suffs.suffixes[i].toString();
      if (suff.contains(word)) {
        isPrefix = true;
        break;
      }
    }

    return isPrefix;
  }

}
