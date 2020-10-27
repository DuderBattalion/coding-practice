import com.leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SerializeBinaryTree {
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder data = new StringBuilder();
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        serializeNode(root, data);

        List<TreeNode> nextLevel = new ArrayList<>();
        StringBuilder nextLevelString = new StringBuilder();
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.remove();
            if (node != null) {
                if (node.left != null) {
                    nextLevel.add(node.left);
                }

                if (node.right != null) {
                    nextLevel.add(node.right);
                }

                serializeNode(node.left, nextLevelString);
                serializeNode(node.right, nextLevelString);
            }

            if (!nextLevel.isEmpty() && nodes.isEmpty()) {
                nodes.addAll(nextLevel);
                data.append(nextLevelString);

                nextLevel.clear();
                nextLevelString.setLength(0);
            }
        }

        return data.substring(0, data.length() - 1); // Remove trailing comma
    }

    private void serializeNode(TreeNode node, StringBuilder data) {
        String nodeVal = (node == null) ? "null" : String.valueOf(node.val);
        data.append(nodeVal).append(",");
    }

    public TreeNode deserialize(String data) {
        String[] nodeVals = data.split(",");
        if (nodeVals.length == 1 && nodeVals[0].isEmpty()) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(nodeVals[0]));

        Queue<DeserializeQueueNode> deserializeQueue = new LinkedList<>();
        DeserializeQueueNode deserializeQueueNode = new DeserializeQueueNode(root);
        deserializeQueue.add(deserializeQueueNode);

        int deserializeIndex = 1, level = 1;
        while (!deserializeQueue.isEmpty()) {
            for (int i = 0; i < Math.pow(2, level); i+=2) {
                DeserializeQueueNode queueNode = deserializeQueue.remove();
                if (queueNode.node == null) {
                    continue;
                }

                if (deserializeIndex >= nodeVals.length) {
                    break;
                }

                TreeNode left = parseTreeNode(nodeVals[deserializeIndex]);
                if (left != null) {
                    deserializeQueue.add(new DeserializeQueueNode(left));
                }

                deserializeIndex++;

                if (deserializeIndex >= nodeVals.length) {
                    break;
                }

                TreeNode right = parseTreeNode(nodeVals[deserializeIndex]);
                if (right != null) {
                    deserializeQueue.add(new DeserializeQueueNode(right));
                }

                deserializeIndex++;

                queueNode.node.left = left;
                queueNode.node.right = right;
            }

            level++;
        }

        return root;
    }

    private static class DeserializeQueueNode {
        public TreeNode node;

        public DeserializeQueueNode(TreeNode node) {
            this.node = node;
        }
    }

    private static TreeNode parseTreeNode(String nodeVal) {
        if (nodeVal.equals("null")) {
            return null;
        }

        return new TreeNode(Integer.parseInt(nodeVal));
    }
}
