package com.leetcode;

public class LongestCommonPrefix {

  public static void main(String[] args) {
    String[] strs = { "flower","flow","flight" };
    System.out.println(longestCommonPrefix(strs));
  }

  public static String longestCommonPrefix(String[] strs) {
    String lcp = "";

    if (strs.length == 0) {
      return lcp;
    }

    if(strs.length == 1) {
      return strs[0];
    }

    String shortStr, longStr;
    int prefixLen;
    int minPrefixLen = Integer.MAX_VALUE;

    for (int i = 0; i < strs.length - 1; i++) {
      prefixLen = 0;
      if (strs[i].length() < strs[i+1].length()) {
        shortStr = strs[i];
        longStr = strs[i+1];
      } else {
        shortStr = strs[i+1];
        longStr = strs[i];
      }

      while (prefixLen < shortStr.length()
          && strs[i].charAt(prefixLen) == strs[i+1].charAt(prefixLen)) {
        prefixLen++;
      }

      if (prefixLen < minPrefixLen) {
        minPrefixLen = prefixLen;
        lcp = strs[i].substring(0, minPrefixLen);
      }
    }

    return lcp;
  }
}
