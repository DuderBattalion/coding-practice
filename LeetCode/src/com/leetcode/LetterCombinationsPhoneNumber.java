package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsPhoneNumber {

  public static void main(String[] args) {
    List<String> results = letterCombinations("23");
    results.forEach(result -> System.out.print(result + ", "));
  }

  public static List<String> letterCombinations(String digits) {
    List<String> results = new ArrayList<>();

    if (digits.length() == 0) {
      return results;
    }

    Map<Integer, String> phoneMap = createPhoneMap();

    generateLetterCombos(digits, "", phoneMap, results);

    return results;
  }

  private static void generateLetterCombos(String digits, String letterCombo,
                                    Map<Integer, String> phoneMap, List<String> results) {
    if (digits.length() == 0) {
      results.add(letterCombo);
      return;
    }

    int digit = Integer.parseInt(String.valueOf(digits.charAt(0)));
    digits = digits.substring(1, digits.length());

    String digitChars = phoneMap.get(digit);
    for (int i = 0; i < digitChars.length(); i++) {
      generateLetterCombos(digits, letterCombo + digitChars.charAt(i),
          phoneMap, results);
    }
  }

  private static Map<Integer, String> createPhoneMap() {
    Map<Integer, String> map = new HashMap<>();

    String[] chars = new String[8];
    chars[0] = "abc";
    chars[1] = "def";
    chars[2] = "ghi";
    chars[3] = "jkl";
    chars[4] = "mno";
    chars[5] = "pqrs";
    chars[6] = "tuv";
    chars[7] = "wxyz";

    int i = 2;
    for (String token: chars) {
      map.put(i, token);
      i++;
    }

    return map;
  }

}
