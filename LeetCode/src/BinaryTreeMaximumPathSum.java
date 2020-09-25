import com.leetcode.util.TreeNode;

public class BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(-10);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        System.out.println(maxPathSum(node1));

//        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(-2);
//        TreeNode node3 = new TreeNode(-3);
//        TreeNode node4 = new TreeNode(1);
//        TreeNode node5 = new TreeNode(3);
//        TreeNode node6 = new TreeNode(-2);
//        TreeNode node7 = new TreeNode(-1);
//
//        node1.left = node2;
//        node1.right = node3;
//        node2.left = node4;
//        node2.right = node5;
//        node3.left = node6;
//        node6.left = node7;
//
//        System.out.println(maxPathSum(node1));

//        TreeNode node1 = new TreeNode(-3);
//        System.out.println(maxPathSum(node1));

//        TreeNode node1 = new TreeNode(5);
//        TreeNode node2 = new TreeNode(4);
//        TreeNode node3 = new TreeNode(8);
//        TreeNode node4 = new TreeNode(11);
//        TreeNode node5 = new TreeNode(13);
//        TreeNode node6 = new TreeNode(4);
//        TreeNode node7 = new TreeNode(7);
//        TreeNode node8 = new TreeNode(2);
//        TreeNode node9 = new TreeNode(1);
//
//        node1.left = node2;
//        node1.right = node3;
//        node2.left = node4;
//        node3.left = node5;
//        node3.right = node6;
//        node4.left = node7;
//        node4.right = node8;
//        node6.left = node9;
//
//        System.out.println(maxPathSum(node1));
    }

    /**
     * Algorithm:
     * At each node, we calculate the local sum as:
     * root + recursive leftSum + recursive rightSum
     * If left or right sums are negative, we can ignore them.
     * If the local sum is more than the maxSum calculated, update maxSum.
     *
     * However, when recursing up the value, we can only choose the
     * root val + (one of the branches - either left or right).
     *
     * Do entire recursion to calculate max path sum.
     */
    public static int maxPathSum(TreeNode root) {
        Result result = new Result();
        result.maxSum = Integer.MIN_VALUE;

        maxPathSum(root, result);

        return result.maxSum;
    }

    public static int maxPathSum(TreeNode root, Result result) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        int leftSum = maxPathSum(root.left, result);
        int rightSum = maxPathSum(root.right, result);

        if (leftSum > 0 && rightSum > 0) {
            int localSum = root.val + leftSum + rightSum;
            if (localSum > result.maxSum) {
                result.maxSum = localSum;
            }
        }

        // When recursing up, we can choose just root, or
        // root plus either the left or the right path
        int newSum = root.val;
        if (leftSum > 0 || rightSum > 0) {
            newSum += Math.max(leftSum, rightSum);
        }

        if (newSum > result.maxSum) {
            result.maxSum = newSum;
        }

        return newSum;
    }

    private static class Result {
        public int maxSum;
    }
}
