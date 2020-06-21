import com.leetcode.util.ListNode;

import java.util.ArrayList;
import java.util.List;

public class ReverseKGroup {
    public static void main(String[] args) {
        // Test Case - 1
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        ListNode output = reverseKGroup(node1, 1);
        while (output != null) {
            System.out.print(output.val + ",");
            output = output.next;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        head = dummy;

        ListNode runner = head;
        while (runner != null) {
            // runner.next - 1st node is always considered dummy
            List<ListNode> listNodes = getKNodes(runner.next, k);
            if (listNodes.size() < k) {
                break;
            }

            ListNode lastKNode = listNodes.get(listNodes.size() - 1);
            ListNode nextKNode = lastKNode.next;

            // Reverse nodes
            for (int i = listNodes.size() - 1; i > 0; i--) {
                ListNode node = listNodes.get(i);
                ListNode nextNode = listNodes.get(i-1);
                node.next = nextNode;
            }

            // Link previous list with head or reversed nodes
            // Note - after reversing, lastKNode is now head of reversed nodes
            runner.next = lastKNode;

            // Link reversed nodes with rest of original list
            // Note - after reversing, index 0 is now tail of reveresed nodes
            ListNode kNodesTail = listNodes.get(0);
            kNodesTail.next = nextKNode;

            runner = kNodesTail;
        }

        return dummy.next;
    }

    private static List<ListNode> getKNodes(ListNode runner, int k) {
        List<ListNode> nodes = new ArrayList<>();
        while (runner != null && k > 0) {
            nodes.add(runner);
            runner = runner.next;
            k--;
        }

        return nodes;
    }
}
