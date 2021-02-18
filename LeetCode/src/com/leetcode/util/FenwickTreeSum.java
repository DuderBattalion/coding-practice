package com.leetcode.util;

public class FenwickTreeSum {
    int[] tree;
    int treeSize;

    public FenwickTreeSum(int capacity) {
        tree = new int[capacity];
        treeSize = 0;
    }

    public int getSum(int index) {
        int sum = 0;
        index++; // 1-index'd tree

        while (index > 0) {
            sum += tree[index];
            index -= index & (-index);
        }

        return sum;
    }

    public void update(int index, int val) {
        index++;
        while (index <= treeSize) {
            tree[index] += val;
            index += index & (-index);
        }
    }

    public void initTree(int[] arr) {
        treeSize = arr.length;

        // initialize tree with 0's
        for (int i = 1; i <= treeSize; i++) {
            tree[i] = 0;
        }

        // Store values in tree
        for (int i = 0; i < treeSize; i++) {
            update(i, arr[i]);
        }
    }
}
