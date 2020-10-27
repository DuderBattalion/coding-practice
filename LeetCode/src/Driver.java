import com.leetcode.util.TreeNode;

public class Driver {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(3);
//        TreeNode node4 = new TreeNode(4);
//        TreeNode node5 = new TreeNode(5);

        node1.left = node2;
//        node1.right = node3;
//
//        node3.left = node4;
//        node3.right = node5;

        SerializeBinaryTree ser = new SerializeBinaryTree();
        SerializeBinaryTree deser = new SerializeBinaryTree();

        String serializedNodes = ser.serialize(node1);
        System.out.println(serializedNodes);

        TreeNode root = deser.deserialize(serializedNodes);
        System.out.println(ser.serialize(root));
    }
}
