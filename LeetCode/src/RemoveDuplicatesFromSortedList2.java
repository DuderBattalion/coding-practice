import com.leetcode.util.ListNode;

import java.util.List;

public class RemoveDuplicatesFromSortedList2 {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        ListNode list2 = new ListNode(2);
        ListNode list3 = new ListNode(3);
        ListNode list4 = new ListNode(3);
        ListNode list5 = new ListNode(4);
        ListNode list6 = new ListNode(4);
        ListNode list7 = new ListNode(5);

        list1.next = list2;
        list2.next = list3;
        list3.next = list4;
        list4.next = list5;
        list5.next = list6;
        list6.next = list7;
        list7.next = null;

        // Test case 2
//        ListNode list1 = new ListNode(1);
//        ListNode list2 = new ListNode(1);
//        ListNode list3 = new ListNode(1);
//        ListNode list4 = new ListNode(2);
//        ListNode list5 = new ListNode(3);
//
//        list1.next = list2;
//        list2.next = list3;
//        list3.next = list4;
//        list4.next = list5;
//        list5.next = null;

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
            ListNode nextNode = node.next;
            boolean isNextNodeDuplicate = isNodeDuplicate(nextNode);

            // Skip ALL adjacent duplicate nodes
            while (isNextNodeDuplicate) {
                ListNode nextNextNode = findNextNode(nextNode);
                isNextNodeDuplicate = isNodeDuplicate(nextNextNode);
                if (!isNextNodeDuplicate) {
                    node.next = nextNextNode;
                    break;
                } else {
                    nextNode = nextNextNode;
                }
            }

            node = node.next;
        }

        return dummy.next;
    }

    private static boolean isNodeDuplicate(ListNode node) {
        if (node == null || node.next == null) {
            return false;
        }

        return node.val == node.next.val;
    }

    private static ListNode findNextNode(ListNode node) {
        if (node == null) {
            return null;
        }

        int val = node.val;
        while (node != null && val == node.val) {
            node = node.next;
        }

        return node;
    }
}
