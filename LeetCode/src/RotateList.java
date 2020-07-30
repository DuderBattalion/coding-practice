import com.leetcode.util.ListNode;

public class RotateList {
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

        ListNode rotatedList = rotateRight(node1, 2);

        while (rotatedList != null) {
            System.out.print(rotatedList.val + " ");
            rotatedList = rotatedList.next;
        }
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        RotatedListProperties tailProps = getTailNode(head);
        if (tailProps.tail == head) {
            return head;
        }

        // On each rotate pass:
        // 1) Move tail to head
        // 2) Assign head pointer to newly moved tail (at the head now)
        // 3) Recalculate tail
        for (int i = 0; i < k; i++) {
            tailProps.tail.next = head;
            head = tailProps.tail;
            tailProps.penultimateTail.next = null; // break circular link

            tailProps = getTailNode(head);
        }

        return head;
    }

    private static RotatedListProperties getTailNode(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode tail = head;
        ListNode penultimateTail = head;
        while (tail.next != null) {
            penultimateTail = tail;
            tail = tail.next;
        }

        return new RotatedListProperties(tail, penultimateTail);
    }

    private static class RotatedListProperties {
        public ListNode tail;
        public ListNode penultimateTail;

        public RotatedListProperties(ListNode tail, ListNode penultimateTail) {
            this.tail = tail;
            this.penultimateTail = penultimateTail;
        }
    }
}
