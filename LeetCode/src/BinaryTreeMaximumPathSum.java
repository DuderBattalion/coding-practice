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

        TreeNode node1 = new TreeNode(-10);
//        TreeNode node2 = new TreeNode(9);
//        TreeNode node3 = new TreeNode(20);
//        TreeNode node4 = new TreeNode(15);
//        TreeNode node5 = new TreeNode(7);
//
//        node1.left = node2;
//        node1.right = node3;
//        node3.left = node4;
//        node3.right = node5;

        System.out.println(maxPathSum(node1));
    }

    public static int maxPathSum(TreeNode root) {
        return maxPathSum(root, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public static int maxPathSum(TreeNode root, int sum, int maxSum) {
        if (root == null) {
            if (sum > maxSum) {
                maxSum = sum;
            }

            return maxSum;
        }

        int leftSum = maxPathSum(root.left, Integer.MIN_VALUE, maxSum);
        int rightSum = maxPathSum(root.right, Integer.MIN_VALUE, maxSum);

        if (leftSum > maxSum) {
            maxSum = leftSum;
        }

        if (rightSum > maxSum) {
            maxSum = rightSum;
        }

        // Cases
        // sum = root + left + right
        // sum = root + left
        // sum = root + right

        int newSum = root.val;
        if (sum > 0) {
            newSum += sum;
        }

        if (leftSum > 0) {
            newSum += leftSum;
        }

        if (rightSum > 0) {
            newSum += rightSum;
        }

        if (newSum > maxSum) {
            maxSum = newSum;
        }

        return maxSum;
    }
}
