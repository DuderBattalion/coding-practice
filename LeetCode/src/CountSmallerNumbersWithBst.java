import com.leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class CountSmallerNumbersWithBst {
    public static void main(String[] args) {

    }

    /**
     * Similar to using a TreeSet in Java. But we implement the BST
     * on our own here.
     */
    public static List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }

        EnhancedTreeNode root = createBst(nums);
        return getNumberCounts(root);
    }

//    private static List<Integer> getNumberCounts(EnhancedTreeNode root) {
//        PriorityQueue<Integer> smallerNumberCounts = new PriorityQueue<>();
//
//        populateNumberCounts(root, smallerNumberCounts);
//    }

//    private static void populateNumberCounts(EnhancedTreeNode node, PriorityQueue<Integer> smallerNumberCounts) {
//        if (node == null) {
//            return;
//        }
//
//        smallerNumberCounts.add(node.smallNumCount);
//
//    }

    private static List<Integer> createBst(int[] nums) {
        List<Integer> smallerNumberCounts = new ArrayList<>();

//        int i = nums.length - 1;
//        EnhancedTreeNode root = null;
//        while (i >= 0) {
//            if (root == null) {
//                root = new EnhancedTreeNode(nums[i], 0);
//            } else {
//                insertNode(nums[i], root);
//            }
//
//            i--;
//        }

        for (int i = nums.length - 1; i >= 0; i--) {

        }

        return root;
    }

    private static EnhancedTreeNode insertNode(int num, EnhancedTreeNode root) {
        if (root == null) {
            return null;
        }

        return recursiveInsertNode(num, root, 0);
    }

    private static EnhancedTreeNode recursiveInsertNode(int num, EnhancedTreeNode node, int count) {
        if (node == null) {
            return new EnhancedTreeNode(num, count);
        }

        if (num <= node.val) {
            node.left = recursiveInsertNode(num, node.left, count);
        } else {
            node.right = recursiveInsertNode(num, node.right, count + 1);
        }

        return node;
    }

    public static class EnhancedTreeNode {
        public int smallNumCount;
        public int val;
        public EnhancedTreeNode left;
        public EnhancedTreeNode right;

        public EnhancedTreeNode(int value, int count) {
            this.smallNumCount = count;
            this.val = value;
            this.left = null;
            this.right = null;
        }
    }
}
