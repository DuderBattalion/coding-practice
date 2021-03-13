package com.leetcode.util;

/**
 * See: https://www.hackerearth.com/practice/notes/disjoint-set-union-union-find/
 */
public class UnionFindOptimized {
    int[] components;
    int[] size;

    public UnionFindOptimized(int n) {
        components = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            components[i] = i;
            size[i] = 1;
        }
    }

    private int root(int i) {
        while (components[i] != i) {
            i = components[i];
        }

        return i;
    }

    public boolean find(int a, int b) {
        return root(a) == root(b);
    }

    // Weighted union - move smaller subset into larger subset
    public void union(int a, int b) {
        int rootA = root(a);
        int rootB = root(b);

        // Set smaller subset as child of larger
        if (size[rootA] < size[rootB]) {
            components[rootA] = components[rootB];
            size[rootB] += size[rootA];
        } else {
            components[rootB] = components[rootA];
            size[rootA] += size[rootB];
        }
    }
}
