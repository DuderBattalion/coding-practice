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

    // String s = "I lik   to   xplor   univ rs ";
    String s = "I lik  to  xplor  univ rs ";
    // SuffixArray validWordSuffs = createSuffArrFromDict(dict);

    printValidSentences("", "", 0, s, dict);
  }

  // TODO - Performance enhancement. Instead of set, store in hashtable
  // Key: first char, Val: String
  // That way, we can check prefixes by only getting firstChar matching words
  private static void initDict(Set<String> dict) {
    dict.add("I");
    dict.add("like");
    dict.add("to");
    dict.add("toe");
    dict.add("explore");
    dict.add("universe");
    dict.add("rse");
  }

  // private static SuffixArray createSuffArrFromDict(Set<String> dict) {
  //   List<String> sentinels = Arrays.asList("!", "@", "#", "$", "%", "^", "&");
  //
  //   StringBuilder buf = new StringBuilder();
  //   Iterator<String> dictIter = dict.iterator();
  //   String word;
  //
  //   int i = 0;
  //   while (dictIter.hasNext()) {
  //     word = dictIter.next();
  //     buf.append(word);
  //     buf.append(sentinels.get(i));
  //     i++;
  //   }
  //
  //   return new SuffixArray(buf.toString());
  // }

  private static void printValidSentences(String sentence, String currWord, int i,
                                          String s, Set<String> dict) {
    if (i == s.length()) {
      sentence += currWord;
      System.out.println(sentence);
      return;
    }

    NextWord nextWord = getNextWord(s, i);

    // NextWord index can be ' ' or 'e'
    // ' ' means current word is complete
    // 'e' means current word is a prefix

    // If ' ', then check i reset current word and keep recursing.
    // If 'e', then check if currWord + nextWord + 'e' is a valid prefix. If so,
    // then keep going, else return.

    // Case ' ' - Word complete
    // Check if current word exists in the dict.
    // If so, reset currentWord and continue, else return.
    String completeWord = currWord + nextWord.word;
    if (dict.contains(completeWord)) {
      printValidSentences(sentence + completeWord + " ", "",
            nextWord.index + 1, s, dict);
    }

    // Case 'e' - Word prefix
    // If prefix is valid, then continue, else return
    String nextWordPrefix = currWord + nextWord.word + "e";
    if (isValidPrefix(nextWordPrefix, dict)) {
      printValidSentences(sentence,
          nextWordPrefix, nextWord.index + 1, s, dict);
    }
  }


  /**
   * Skips ahead to next index with space.
   * @return Next word collected from start to next space, and returns
   * next space index.
   */
  private static NextWord getNextWord(String s, int start) {
    NextWord nextWord = new NextWord();

    int i = getNextSpace(s, start);
    if (i < 0) {
      return nextWord;
    }

    nextWord.word = s.substring(start, i);
    nextWord.index = i;
    return nextWord;
  }

  /**
   * Reads ahead from start index until it encounters a space
   * The space can be a word terminator, or a faulty 'e'
   * @return index of next space
   */
  private static int getNextSpace(String s, int start) {
    int i;
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

  /**
   * Checks if the prefix being built is headed in the right direction.
   * If prefix is part of a valid word, return true else false.
   */
  private static boolean isValidPrefix(String prefix, Set<String> dict) {
    boolean isPrefix = false;
    for (String validWord : dict) {
      if (validWord.contains(prefix)) {
        isPrefix = true;
        break;
      }
    }

    return isPrefix;
  }

}
