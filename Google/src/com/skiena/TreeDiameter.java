package com.skiena;

import com.util.BinaryTree;

/**
 * Diameter of a tree is the longest path between u and v, (u,v) being nodes in a tree.
 */
public class TreeDiameter {

  public static void main(String[] args) {
    BinaryTree node1 = new BinaryTree(1);
    BinaryTree node2 = new BinaryTree(1);
    BinaryTree node3 = new BinaryTree(1);
    BinaryTree node4 = new BinaryTree(1);
    BinaryTree node5 = new BinaryTree(1);
    BinaryTree node6 = new BinaryTree(1);
    BinaryTree node7 = new BinaryTree(1);
    BinaryTree node8 = new BinaryTree(1);
    BinaryTree node9 = new BinaryTree(1);

    buildTestTree(node1, node2, node3, node4, node5, node6, node7, node8, node9);

    PathStruct path = new PathStruct();
    PathStruct diameter = getDiameter(node1, path);
    System.out.println("Diameter: " + diameter.maxPath);
  }

  private static void buildTestTree(BinaryTree node1, BinaryTree node2,
                                    BinaryTree node3, BinaryTree node4,
                                    BinaryTree node5, BinaryTree node6,
                                    BinaryTree node7, BinaryTree node8,
                                    BinaryTree node9) {
    node1.left = node2;
    node1.right = node3;

    node2.left = node4;
    node4.left = node6;
    node6.left = node9;
    node2.right = node5;

    node5.left = node7;
    node7.left = node8;
    node8.left = node9;
  }

  /**
   * Either node is part of longest path (longestPath), or longest path lies in
   * one of it's subtrees (maxPath).
   */
  private static PathStruct getDiameter(BinaryTree node, PathStruct path) {
    if (node == null) {
      return path;
    }

    int leftPath = getLongestPath(node.left);
    int rightPath = getLongestPath(node.right);
    int longestPath = Math.max(leftPath, rightPath);

    PathStruct nodePath = new PathStruct();

    nodePath.longestPath = Math.max(longestPath, path.maxPath);
    nodePath.maxPath = Math.max(leftPath + 1 + rightPath, path.maxPath);

    return nodePath;
  }

  private static class PathStruct {
    // Max diameter seen yet, could be a part of deeper subtree
    // and not related to this node
    public int maxPath;

    // Max of longest path in left or right subtree from this node
    public int longestPath;

  }

  /**
   * Longest path in subtree is the max of the deepest path found in
   * the left or the right child. Only one can be recursively taken
   * at a time as a path.
   */
  private static int getLongestPath(BinaryTree node) {
    if (node == null) {
      return 0;
    }

    return Math.max(getLongestPath(node.left) + 1, getLongestPath(node.right) + 1);
  }
}
