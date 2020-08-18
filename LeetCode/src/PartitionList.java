import com.leetcode.util.ListNode;

public class PartitionList {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(2);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = null;

        ListNode node = partition(node1, 3);
        while (node != null) {
            System.out.print(node.val + ", ");
            node = node.next;
        }
    }

    public static ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }

        ListNode smallerNode = new ListNode();
        ListNode smallerNodesHead = smallerNode;

        ListNode greaterNodesHead = new ListNode();
        greaterNodesHead.next = head;

        ListNode node = greaterNodesHead;
        while (node != null) {
            ListNode nextNode = node.next;
            while (nextNode != null && nextNode.val < x) {
                smallerNode.next = nextNode;

                smallerNode = smallerNode.next;
                nextNode = nextNode.next;
            }

            node.next = nextNode;
            node = node.next;
        }

        smallerNode.next = greaterNodesHead.next; // Skip greater node dummy node
        return smallerNodesHead.next; // Skip smaller node dummy node
    }
}
