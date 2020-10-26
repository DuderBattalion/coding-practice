import com.leetcode.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeBinaryTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder data = new StringBuilder();
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            TreeNode node = nodes.remove();
            serializeNode(node, data);
            if (node != null) {
                if (node.left != null) {
                    nodes.add(node.left);
                }

                if (node.right != null) {
                    nodes.add(node.right);
                }

                serializeNode(node.left, data);
                serializeNode(node.right, data);
            }
        }

        return data.toString();
    }

    private void serializeNode(TreeNode node, StringBuilder data) {
        String nodeVal = (node == null) ? "null" : String.valueOf(node.val);
        data.append(nodeVal).append(",");
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodeVals = data.split(",");
        if (nodeVals.length == 0) {
            return null;
        }

//        TreeNode root = new TreeNode(Integer.parseInt(nodeVals[0]));
//        Queue<TreeNode> deserializeQueue = new LinkedList<>();
//        deserializeQueue.add(root);

        int readIndex = 0, writeIndex = 1, level = 1;
        while (writeIndex < nodeVals.length) {
            for (int i = 0; i < Math.pow(2, level); i+=2) {
                String readNodeVal = nodeVals[readIndex];
                readIndex++;

                if (readNodeVal.equals("null")) {
                    writeIndex += 2;
                    continue;
                }

                TreeNode node =

                String writeNodeLeftVal = nodeVals[writeIndex];
                writeIndex++;

                String writeNodeRightVal = nodeVals[writeIndex];
                writeIndex++;

                if ()



            }
        }


    }
}
