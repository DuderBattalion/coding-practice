import com.leetcode.util.TreeNode;

public class BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
//        TreeNode node1 = new TreeNode(-10);
//        TreeNode node2 = new TreeNode(9);
//        TreeNode node3 = new TreeNode(20);
//        TreeNode node4 = new TreeNode(15);
//        TreeNode node5 = new TreeNode(7);
//
//        node1.left = node2;
//        node1.right = node3;
//        node3.left = node4;
//        node3.right = node5;
//
//        System.out.println(maxPathSum(node1));

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(-2);
        TreeNode node3 = new TreeNode(-3);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(-2);
        TreeNode node7 = new TreeNode(-1);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node6.left = node7;

        System.out.println(maxPathSum(node1));
    }

    public static int maxPathSum(TreeNode root) {
        Result result = new Result();
        result.maxSum = Integer.MIN_VALUE;

        maxPathSum(root, result);

        return result.maxSum;
    }

    public static int maxPathSum(TreeNode root, Result result) {
        if (root == null) {
            return 0;
        }

        int leftSum = maxPathSum(root.left, result);
        int rightSum = maxPathSum(root.right, result);

        if (leftSum > result.maxSum) {
            result.maxSum = leftSum;
        }

        if (rightSum > result.maxSum) {
            result.maxSum = rightSum;
        }

        // Cases
        // sum = root + left + right
        // sum = root + left
        // sum = root + right

        int newSum = root.val;

        if (leftSum > 0) {
            newSum += leftSum;
        }

        if (rightSum > 0) {
            newSum += rightSum;
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
