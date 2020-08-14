import com.leetcode.util.ListNode;

public class RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        ListNode list2 = new ListNode(1);
        ListNode list3 = new ListNode(2);
        ListNode list4 = new ListNode(3);
        ListNode list5 = new ListNode(3);

        list1.next = list2;
        list2.next = list3;
        list3.next = list4;
        list4.next = list5;
        list5.next = null;

        ListNode node = deleteDuplicates(list1);
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode node = dummy;
        while (node != null) {
            node.next = findNextDistinctNode(node);
            node = node.next;
        }

        return dummy.next;
    }

    private static ListNode findNextDistinctNode(ListNode node) {
        if (node == null) {
            return null;
        }

        int val = node.val;
        node = node.next;
        while (node != null && node.val == val) {
            node = node.next;
        }

        return node;
    }
}
