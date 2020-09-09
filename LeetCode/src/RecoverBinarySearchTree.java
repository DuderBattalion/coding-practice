import com.leetcode.util.DebugUtil;
import com.leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class RecoverBinarySearchTree {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);

        // Test case 1
//        t1.left = t3;
//        t3.right = t2;

        // Test case 2
        t3.left = t1;
        t3.right = t4;
        t4.left = t2;

        recoverTree(t3);
        DebugUtil.printTree(t3);
    }

    public static void recoverTree(TreeNode root) {
//        recoverTreeRecursive(root, new InvalidNodes());
        List<TreeNode> output = new ArrayList<>();
        findInvalidNodes(root, null, output);
        if (output.size() != 2) {
            throw new RuntimeException("Error: output size is not 2");
        }

        swapNodeValues(output.get(0), output.get(1));
    }

    /**
     * Algorithm:
     * In-order traversal. Where node val is lower than previous node - invalid node.
     * Record two instances of invalid node and return.
     */
    private static void findInvalidNodes(TreeNode node, TreeNode prevNode, List<TreeNode> invalidNodes) {
        if (node == null){
            return;
        }

        if (invalidNodes.size() > 2) {
            return;
        }

        findInvalidNodes(node.left, prevNode, invalidNodes);

        boolean isInvalid = isInvalidNode(node, prevNode);
        if (invalidNodes.size() == 0 && isInvalid) {
            invalidNodes.add(prevNode);
        }

        if (invalidNodes.size() > 0 && isInvalid) {
            if (invalidNodes.size() == 2) {
                invalidNodes.set(1, node);
            } else {
                invalidNodes.add(node);
            }
        }
        prevNode = node;

        findInvalidNodes(node.right, prevNode, invalidNodes);
    }

    private static boolean isInvalidNode(TreeNode node, TreeNode prevNode) {
        if (prevNode == null) {
            return false;
        }

        return prevNode.val > node.val;
    }

    private static void swapNodeValues(TreeNode first, TreeNode second) {
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }



//    private static void recoverTreeRecursive(TreeNode root, InvalidNodes invalidNodes) {
//        if (root == null) {
//            return;
//        }
//
//        // Check Root
//        if ((root.left != null && root.val < root.left.val)
//                || (root.right != null && root.val > root.right.val)) {
//            invalidNodes.setInvalidNode(root);
//        }
//
//        if (invalidNodes.areBothInvalidNodesFound()) {
//            swapNodeVals(invalidNodes);
//            return;
//        }
//
//        // Check left
//        if (root.left != null && root.left.val > root.val) {
//            invalidNodes.setInvalidNode(root.left);
//        }
//
//        if (invalidNodes.areBothInvalidNodesFound()) {
//            swapNodeVals(invalidNodes);
//            return;
//        }
//
//        // Check right
//        if (root.right != null && root.right.val < root.val) {
//            invalidNodes.setInvalidNode(root.right);
//        }
//
//        if (invalidNodes.areBothInvalidNodesFound()) {
//            swapNodeVals(invalidNodes);
//            return;
//        }
//
//        recoverTreeRecursive(root.left, invalidNodes);
//        recoverTreeRecursive(root.right, invalidNodes);
//    }
//
//    private static class InvalidNodes {
//        public TreeNode firstNode;
//        public TreeNode secondNode;
//
//        public boolean areBothInvalidNodesFound() {
//            return firstNode != null && secondNode != null;
//        }
//
//        public void setInvalidNode(TreeNode node) {
//            if (firstNode == null) {
//                firstNode = node;
//            } else if (secondNode == null) {
//                secondNode = node;
//            } else {
//                throw new RuntimeException("[Error]: Both nodes are already defined.");
//            }
//        }
//    }
//
//    private static void swapNodeVals(InvalidNodes invalidNodes) {
//        int tempVal = invalidNodes.firstNode.val;
//        invalidNodes.firstNode.val = invalidNodes.secondNode.val;
//        invalidNodes.secondNode.val = tempVal;
//    }

}
