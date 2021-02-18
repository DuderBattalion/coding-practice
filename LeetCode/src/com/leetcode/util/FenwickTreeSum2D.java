package com.leetcode.util;

public class FenwickTreeSum2D {
    int rows;
    int cols;
    int[][] tree;

    private int getLsb(int i) {
        return i & (-i);
    }

    public void update(int row, int col, int val) {
        for (int i = row; i <= rows; i += getLsb(i)) {
            for (int j = col; j <= cols; j += getLsb(j)) {
                tree[i][j] += val;
            }
        }
    }

    public int getSum(int row, int col) {
        int sum = 0;
        for (int i = row; i <= rows; i -= getLsb(i)) {
            for (int j = col; j <= cols; j += getLsb(j)) {
                sum += tree[i][j];
            }
        }

        return sum;
    }

    public void initTree(int[][] matrix) {
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int val = matrix[i - 1][j - 1];
                update(i, j, val);
            }
        }
    }
}
