package com.leetcode.util;

import java.util.LinkedList;
import java.util.Queue;

public class DebugUtil {
    public static void printTree(TreeNode node) {
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(node);

        while (!nodes.isEmpty()) {
            TreeNode currNode = nodes.remove();
            if (currNode == null) {
                System.out.print(" null ");
                continue;
            }

            System.out.print(currNode.val + " ");

            nodes.add(currNode.left);
            nodes.add(currNode.right);
        }
    }

    public static void debugDpCache(int[][] dp, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(dp[i][j] + " ");
            }

            System.out.println();
        }
    }
}
