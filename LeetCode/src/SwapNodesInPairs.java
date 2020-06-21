import com.leetcode.util.ListNode;

public class SwapNodesInPairs {
    public static void main(String[] args) {
//        // Test Case - 1
//        ListNode l1 = new ListNode(1);
//        ListNode l2 = new ListNode(2);
//        ListNode l3 = new ListNode(3);
//        ListNode l4 = new ListNode(4);
//
//        l1.next = l2;
//        l2.next = l3;
//        l3.next = l4;
//        l4.next = null;

//        // Test Case - 2
//        ListNode l1 = null;

        // Test Case - 3
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);

        l1.next = l2;
        l2.next = null;

        ListNode swappedList = swapPairs(l1);
        while (swappedList != null) {
            System.out.print(swappedList.val + ",");
            swappedList = swappedList.next;
        }
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode a, b, c;
        a = dummy;
        while (a != null) {
            b = a.next;

            if (b != null && b.next != null) {
                c = b.next;
            } else {
                break;
            }

            // Ex: empty (a)->1(b)->2(c)->3->4
            a.next = c;
            b.next = c.next;
            c.next = b;

            a = a.next.next;
        }

        return dummy.next;
    }
}
