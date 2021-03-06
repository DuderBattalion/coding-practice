import java.util.List;

public class NthNodeFromEndOfList {
    public static void main(String[] args) {
//        // Test Case - 1
//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);
//
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = null;
//
//        ListNode head = removeNthFromEnd(node1, 2);

//        // Test case - 2
//        ListNode node1 = new ListNode(1);
//        node1.next = null;
//
//        ListNode head = removeNthFromEnd(node1, 1);

        // Test case - 3
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        node2.next = null;

        ListNode head = removeNthFromEnd(node1, 2);

        while (head != null) {
            System.out.print(head.val + ",");
            head = head.next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode frontRunner = head;

        // Note - n guaranteed to be valid, skipping checks
        // Stops right before nth node
        for (int i = 0; i < n; i++) {
            frontRunner = frontRunner.next;
        }

        // Special case - Nth node from end is head
        // Remove head and return list
        if (frontRunner == null) {
            ListNode oldHead = head;
            head = head.next;
            oldHead.next = null;

            return head;
        }

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
