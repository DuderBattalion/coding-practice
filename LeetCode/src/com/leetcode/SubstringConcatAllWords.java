package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SubstringConcatAllWords {
  public static void main(String[] args) {
    String s = "barfoothefoobarman";
    String[] words = { "foo","bar" };

    List<Integer> indices = findSubstring(s, words);
    indices.forEach(index -> {
      System.out.print(index + ",");
    });
  }

  public static List<Integer> findSubstring(String s, String[] words) {
    List<Integer> substringIndices = new ArrayList<>();
    if (words.length == 0) {
      return substringIndices;
    }

    Arrays.sort(words);
    StringBuilder wordsHashBuf = new StringBuilder();
    Arrays.stream(words).forEach(word -> {
      wordsHashBuf.append(word);
      wordsHashBuf.append(",");
    });

    String wordsHash = wordsHashBuf.toString();

    List<String> slidingWindow = new LinkedList<>();
    int windowLength = words[0].length();

    // Number of passes required to process all words
    int numLoops = (words.length - 1) * windowLength;

    int i = 0;
    LinkedList<String> windowWords = new LinkedList<>();
    while (numLoops > 0) {
      if (i == 0) {
        initWindowWords(s, words.length, windowLength, windowWords);
      } else {
        updateWindowWords(i, s, windowWords);
      }

      List<String> sortedWindowWords = new ArrayList<>(windowWords);
      Collections.sort(sortedWindowWords);
      StringBuilder windowWordHash = new StringBuilder();
      sortedWindowWords.forEach(word -> {
        windowWordHash.append(word);
        windowWordHash.append(",");
      });

      if (windowWordHash.toString().equals(wordsHash)) {
        substringIndices.add(i);
      }

      i += windowLength;
      numLoops--;
    }

    return substringIndices;
  }

  private static void initWindowWords(String s, int numWords, int wordLength, LinkedList<String> windowWords) {
    int wordIndex = 0;
    for (int i = 0; i < numWords; i++) {
      windowWords.add(s.substring(wordIndex, wordIndex + wordLength));
      wordIndex += wordLength;
    }
  }

  /**
   * Removes first words in sliding window and appends the next word from string s
   */
  private static void updateWindowWords(int i, String s, LinkedList<String> windowWords) {
    String firstWord = windowWords.pollFirst();
    if (firstWord == null) {
      throw new RuntimeException("Unexpected Error: first word should not be null!");
    }

    // All words are same length, use that to get sliding window length
    windowWords.addLast(s.substring(i, i + firstWord.length()));
  }
}
