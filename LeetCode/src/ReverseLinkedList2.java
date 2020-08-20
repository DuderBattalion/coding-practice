import com.leetcode.util.ListNode;

import java.util.List;

public class ReverseLinkedList2 {
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

        ListNode node = reverseBetween(node1, 2, 4);
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        head = dummy;

        ListNode breakNodeLeft = getNthNode(head, m - 1);
        ListNode breakNodeRight = getNthNode(breakNodeLeft.next, (n - m) + 1);

        ListData reverseListData = createReverseList(breakNodeLeft.next, (n - m) + 1);
        ListNode reverseList = reverseListData.head;
        ListNode reverseListTail = reverseListData.tail;

        breakNodeLeft.next = reverseList;
        reverseListTail.next = breakNodeRight;

        return dummy.next;
    }

    private static ListData createReverseList(ListNode node, int n) {
        ListNode tail = node;
        ListNode head = null;

        while (node != null && n > 0) {
            ListNode nextNode = node.next;
            head = insertInFront(head, node);
            node = nextNode;

            n--;
        }

        return new ListData(head, tail);
    }

    private static ListNode insertInFront(ListNode head, ListNode node) {
        if (node == null) {
            return head;
        }

        node.next = head;
        return node;
    }

    private static ListNode getNthNode(ListNode node, int n) {
        while (node != null && n > 0) {
            node = node.next;
            n--;
        }

        return node;
    }

    public static class ListData {
        ListNode head;
        ListNode tail;

        public ListData(ListNode head, ListNode tail) {
            this.head = head;
            this.tail = tail;
        }
    }

//    public static ListNode reverseBetween(ListNode head, int m, int n) {
//        ListNode dummy = new ListNode();
//        dummy.next = head;
//        head = dummy;
//
//        ListNode reverseListDummy = new ListNode();
//
//        // Stop one before reverse node start (m)
//        ListNode node = head;
//        for (int i = 0; i < (m - 1); i++) {
//            node = node.next;
//        }
//
//        ListNode leftInsertNode = node;
//        ListNode rightInsertNode = null;
//        if (node.next != null) {
//            rightInsertNode = node.next.next;
//        }
//
//        // Add nodes to be reversed in reverse order into a linked list
//        // Think Queue type insertion at head
//        ListNode reverseNodeTail = node.next;
//        ListNode reverseNode = node.next;
//        ListNode reverseListHead = null;
//        for (int i = 0; i < (n - m); i++) {
//            ListNode nextReverseNode = reverseNode.next;
//
//            reverseNode.next = reverseListHead; // Add to reverse list head
//            reverseListHead = reverseNode; // Move reverse list head to newly added node
//            reverseNode = nextReverseNode; // Queue up next node that needs to be reversed
//        }
//
//        // Insert reversed list into the original list
//        leftInsertNode.next = reverseListHead;
//
//        if (reverseNodeTail != null) {
//            reverseNodeTail.next = rightInsertNode;
//        }
//
//        return dummy.next;
//    }
}
