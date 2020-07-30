import com.leetcode.util.ListNode;

public class RotateList {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(0);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);

//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = null;

        node1.next = node2;
        node2.next = node3;
        node3.next = null;

        ListNode rotatedList = rotateRight(node1, 4);

        while (rotatedList != null) {
            System.out.print(rotatedList.val + " ");
            rotatedList = rotatedList.next;
        }
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        RotatedListProperties tailProps = getTailNode(head);
        if (tailProps.tail == head) {
            return head;
        }

        // On each rotate pass:
        // 1) Move tail to head
        // 2) Assign head pointer to newly moved tail (at the head now)
        // 3) Recalculate tail
        for (int i = 0; i < (k % tailProps.listLength); i++) {
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
        int count = 1;
        while (tail.next != null) {
            penultimateTail = tail;
            tail = tail.next;
            count++;
        }

        return new RotatedListProperties(tail, penultimateTail, count);
    }

    private static class RotatedListProperties {
        public ListNode tail;
        public ListNode penultimateTail;
        public int listLength;

        public RotatedListProperties(ListNode tail, ListNode penultimateTail, int length) {
            this.tail = tail;
            this.penultimateTail = penultimateTail;
            this.listLength = length;
        }
    }
}
