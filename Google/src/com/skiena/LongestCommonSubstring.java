package com.skiena;

import com.util.SuffixArray;

/**
 * Find the longest common substrings that are common in two Strings
 */
public class LongestCommonSubstring {

  public static void main(String[] args) {
    String strA = "photography";
    String strB = "tomograph";

    String strAB = strA + "#" + strB + "$"; // Add sentinels
    SuffixArray suffArr = new SuffixArray(strAB);

    String suff;
    int lcp, prevLcp;
    int maxLcp = 0;
    String longestSubStr = "";
    for (int i = 0; i < suffArr.length(); i++) {
      suff = suffArr.select(i);

      // Ignore initial suffixes starting with sentinels. They aren't
      // part of our solution.
      if (suff.startsWith("#") || suff.startsWith("$")) {
        continue;
      }

      prevLcp = suffArr.lcp(i - 1);
      lcp = suffArr.lcp(i);
      if (lcp - prevLcp > maxLcp) {
        maxLcp = lcp - prevLcp;
        longestSubStr = suff.substring(0, lcp - prevLcp);
      }
    }

    System.out.println("Longest Substring: " + longestSubStr);
   }
}
