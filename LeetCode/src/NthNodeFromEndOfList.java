import java.util.List;

public class NthNodeFromEndOfList {
    public static void main(String[] args) {
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

        ListNode head = removeNthFromEnd(node1, 2);
        while (head != null) {
            System.out.print(head.val + ",");
            head = head.next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode frontRunner = head;
        for (int i = 0; i < n; i++) {
            frontRunner = frontRunner.next;
        }

        // Stops right before nth node
        ListNode slowRunner = head;
        while (frontRunner.next != null) {
            frontRunner = frontRunner.next;
            slowRunner = slowRunner.next;
        }

        // Skip nth node in list and return it
        ListNode nthNode = slowRunner.next;
        slowRunner.next = nthNode.next;

        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
