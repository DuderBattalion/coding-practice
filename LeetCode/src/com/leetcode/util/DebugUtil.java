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
}
