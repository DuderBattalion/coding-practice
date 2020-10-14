package com.leetcode.util;

/**
 * Referenced from GeeoksOfGeeks:
 * https://www.geeksforgeeks.org/trie-insert-and-search/
 */
public class Trie {
    final int ALPHABET_SIZE = 26;
    TrieNode root;

    public Trie() {
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
    }

    /**
     * Insert each character of key into trie - starting from the root.
     * If character exists as TrieNode, keep moving. If not, create a new TrieNode.
     */
    public void insert(String key) {
        TrieNode node = root;
        for (Character token: key.toCharArray()) {
            int charIndex = token - 'a';
            if (node.children[charIndex] == null) {
                node.children[charIndex] = new TrieNode();
            }

            node = node.children[charIndex];
        }

        node.isEndOfWord = true;
    }

    public boolean searchPrefix(String key) {
//        boolean isFound = true;
//
//        TrieNode node = root;
//        for (Character token: key.toCharArray()) {
//            int charIndex = token - 'a';
//            if (node.children[charIndex] == null) {
//                isFound = false;
//                break;
//            }
//
//            node = node.children[charIndex];
//        }

        TrieNode node = findNode(key);
        return (node != null);
    }

    private TrieNode findNode(String key) {
        TrieNode node = root;
        for (Character token: key.toCharArray()) {
            int charIndex = token - 'a';
            if (node.children[charIndex] == null) {
                node = null;
                break;
            }

            node = node.children[charIndex];
        }

        return node;
    }

    public boolean search(String key) {
//        boolean isFound = true;
//
//        TrieNode node = root;
//        for (Character token: key.toCharArray()) {
//            int charIndex = token - 'a';
//            if (node.children[charIndex] == null) {
//                isFound = false;
//                break;
//            }
//
//            node = node.children[charIndex];
//        }

        TrieNode node = findNode(key);
        return node != null && node.isEndOfWord;
    }
}
