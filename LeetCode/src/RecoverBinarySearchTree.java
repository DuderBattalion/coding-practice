import com.leetcode.util.DebugUtil;
import com.leetcode.util.TreeNode;

public class RecoverBinarySearchTree {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);

        t1.left = t3;
        t3.right = t2;

        recoverTree(t1);
        DebugUtil.printTree(t1);
    }

    public static void recoverTree(TreeNode root) {
        recoverTreeRecursive(root, new InvalidNodes());
    }

    private static void recoverTreeRecursive(TreeNode root, InvalidNodes invalidNodes) {
        if (root == null) {
            return;
        }

        // Check Root
        if (root.val < root.left.val || root.val > root.right.val) {
            invalidNodes.setInvalidNode(root);
        }

        if (invalidNodes.areBothInvalidNodesFound()) {
            swapNodeVals(invalidNodes);
            return;
        }

        // Check left
        if (root.left != null && root.left.val > root.val) {
            invalidNodes.setInvalidNode(root.left);
        }

        if (invalidNodes.areBothInvalidNodesFound()) {
            swapNodeVals(invalidNodes);
            return;
        }

        // Check right
        if (root.right != null && root.right.val < root.val) {
            invalidNodes.setInvalidNode(root.right);
        }

        if (invalidNodes.areBothInvalidNodesFound()) {
            swapNodeVals(invalidNodes);
            return;
        }

        recoverTreeRecursive(root.left, invalidNodes);
        recoverTreeRecursive(root.right, invalidNodes);
    }

    private static class InvalidNodes {
        public TreeNode firstNode;
        public TreeNode secondNode;

        public boolean areBothInvalidNodesFound() {
            return firstNode != null && secondNode != null;
        }

        public void setInvalidNode(TreeNode node) {
            if (firstNode == null) {
                firstNode = node;
            } else if (secondNode == null) {
                secondNode = node;
            } else {
                throw new RuntimeException("[Error]: Both nodes are already defined.");
            }
        }
    }

    private static void swapNodeVals(InvalidNodes invalidNodes) {
        int tempVal = invalidNodes.firstNode.val;
        invalidNodes.firstNode.val = invalidNodes.secondNode.val;
        invalidNodes.secondNode.val = tempVal;
    }

}
