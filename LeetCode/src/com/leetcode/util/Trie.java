package com.leetcode.util;

/**
 * Referenced from GeeoksOfGeeks:
 * https://www.geeksforgeeks.org/trie-insert-and-search/
 */
public class Trie {
    final int ALPHABET_SIZE = 26;
    TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        boolean isEndOfWord;

        TrieNode() {
            isEndOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                children[i] = null;
            }
        }

        /**
         * Insert each character of key into trie - starting from the root.
         * If character exists as TrieNode, keep moving. If not, create a new TrieNode.
         */
        void insert(String key) {
            TrieNode node = root;
            for (Character token: key.toCharArray()) {
                int charIndex = token - 'a';
                if (root.children[charIndex] == null) {
                    root.children[charIndex] = new TrieNode();
                }

                node = root.children[charIndex];
            }

            node.isEndOfWord = true;
        }

        boolean search(String key) {
            boolean isFound = true;

            TrieNode node = root;
            for (Character token: key.toCharArray()) {
                int charIndex = token - 'a';
                if (root.children[charIndex] == null) {
                    isFound = false;
                    break;
                }

                node = root.children[charIndex];
            }

            return isFound && node != null && node.isEndOfWord;
        }
    }
}
