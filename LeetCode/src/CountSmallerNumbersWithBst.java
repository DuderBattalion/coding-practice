import com.leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class CountSmallerNumbersWithBst {
    public static void main(String[] args) {
//        int[] nums = { 5, 2, 6, 1 };
        int[] nums = { 5, 2, 2, 6, 1 };

        List<Integer> output = countSmaller(nums);
        for (int num: output) {
            System.out.print(num + ", ");
        }
    }

    /**
     * Similar to using a TreeSet in Java. But we implement the BST
     * on our own here.
     *
     * Algorithm
     * Calculate smaller numbers on right while inserting nodes into BST.
     * Insert nodes from back to front.
     * Helper data structure to help with duplicate numbers.
     */
    public static List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }

        List<Integer> output = createBst(nums);
        Collections.reverse(output);

        return output;
    }

    private static List<Integer> createBst(int[] nums) {
        List<Integer> smallerNumberCounts = new ArrayList<>();

        EnhancedTreeNode root = null;
        for (int i = nums.length - 1; i >= 0; i--) {
            root = recursiveInsertNode(nums[i], root, 0, smallerNumberCounts);
        }

        return smallerNumberCounts;
    }

    private static EnhancedTreeNode recursiveInsertNode(int num, EnhancedTreeNode root,
                                                        int count,
                                                        List<Integer> smallerNumbersCount) {
        if (root == null) {
            smallerNumbersCount.add(count);
            return new EnhancedTreeNode(num, count, 1);
        }

        if (num < root.val) {
            root.left = recursiveInsertNode(num, root.left, count, smallerNumbersCount);
        } else if (num > root.val){
            root.right = recursiveInsertNode(num, root.right,
                    count + root.numCount, smallerNumbersCount);
        } else {
            root.numCount++;
        }

        return root;
    }

    public static class EnhancedTreeNode {
        public int smallNumCount;
        public int numCount;
        public int val;
        public EnhancedTreeNode left;
        public EnhancedTreeNode right;

        public EnhancedTreeNode(int value, int count, int numCount) {
            this.smallNumCount = count;
            this.numCount = numCount;

            this.val = value;
            this.left = null;
            this.right = null;
        }
    }
}
