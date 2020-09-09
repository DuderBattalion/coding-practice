import com.leetcode.util.DebugUtil;
import com.leetcode.util.TreeNode;
import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class RecoverBinarySearchTree {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);

        // Test case 1
        t1.left = t3;
        t3.right = t2;

//        recoverTree(t1);
        traverse(t1, null, null, new TreeNode(Integer.MIN_VALUE));
        DebugUtil.printTree(t1);

//        // Test case 2
//        t3.left = t1;
//        t3.right = t4;
//        t4.left = t2;

//        recoverTree(t3);
//        DebugUtil.printTree(t3);
    }

    public static void recoverTree(TreeNode root) {
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

    private static void traverse(TreeNode root, TreeNode firstElement, TreeNode secondElement, TreeNode prevElement) {

        if (root == null)
            return;

        traverse(root.left, firstElement, secondElement, prevElement);

        // Start of "do some business",
        // If first element has not been found, assign it to prevElement (refer to 6 in the example above)
        if (firstElement == null && prevElement.val >= root.val) {
            firstElement = prevElement;
        }

        // If first element is found, assign the second element to the root (refer to 2 in the example above)
        if (firstElement != null && prevElement.val >= root.val) {
            secondElement = root;
        }
        prevElement = root;

        // End of "do some business"

        traverse(root.right, firstElement, secondElement, prevElement);
    }
}
