package com.leetcode.util;

public class RabinKarp {
    private int prime = 101;

    public int search(String text, String pattern) {
        int patternLength = pattern.length();

        char[] textChars = text.toCharArray();
        char[] patternChars = pattern.toCharArray();

        long patternHash = initHash(patternChars, pattern.length());
        long substringHash = initHash(textChars, pattern.length());

        int matchIndex = -1;
        for (int i = 0; i < text.length() - pattern.length(); i++) {
            if (patternHash == substringHash && isEqual(patternChars, textChars, i)) {
                matchIndex = i;
                break;
            }

            substringHash = rollingHash(textChars, substringHash, i, patternLength);
        }

        return matchIndex;
    }

    private long initHash(char[] chars, int size) {
        long hash = 0;
        for (int i = 0; i < size; i++) {
            hash += chars[i] * Math.pow(prime, i);
        }

        return hash;
    }

    private boolean isEqual(char[] pattern, char[] text, int start) {
        boolean isEqual = true;
        for (int i = start; i < start + pattern.length; i++) {
            if (pattern[i - start] != text[i]) {
                isEqual = false;
                break;
            }
        }

        return isEqual;
    }

    private long rollingHash(char[] textChars, long oldHash, int oldIndex, int patternLength) {
        int newIndex = oldIndex + patternLength;

        long newHash = oldHash - textChars[oldIndex];
        newHash = newHash / prime;
        newHash += textChars[newIndex] * Math.pow(prime, patternLength - 1);

        return newHash;
    }
}
