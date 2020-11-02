import com.leetcode.util.TreeNode;

public class Driver {
    public static void main(String[] args) {
        TreeNode root = null;
        testNode(root);

        if (root == null) {
            System.out.println("Root is null");
        } else {
            System.out.println(root.val);
        }
    }

    private static void testNode(TreeNode node) {
        node = new TreeNode(5);
    }
}
