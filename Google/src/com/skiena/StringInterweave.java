package com.skiena;

/**
 * Given two strings. Find all permutations of interweaving characters, keeping sequence order intact.
 */
public class StringInterweave {

  public static void main(String[] args) {
    String a = "abc";
    String b = "def";

    printInterweavings(a, b, "");
  }

  private static void printInterweavings(String a, String b, String result) {
    if(a.isEmpty()) {
     result += b;
      System.out.println(result);
      return;
    } else if (b.isEmpty()) {
      result += a;
      System.out.println(result);
      return;
    }

    char[] aChars = a.toCharArray();
    char[] bChars = b.toCharArray();

    // Print first char from a and then b
    printInterweavings(removeFirstChar(a),
       removeFirstChar(b),
        result + aChars[0] + bChars[0]);

    // Print first char from b and then a
    printInterweavings(removeFirstChar(a),
        removeFirstChar(b),
        result + bChars[0] + aChars[0]);
  }

  private static String removeFirstChar(String s) {
    if (s.length() <= 1) {
      return "";
    } else {
      return s.substring(1, s.length());
    }
  }
}
