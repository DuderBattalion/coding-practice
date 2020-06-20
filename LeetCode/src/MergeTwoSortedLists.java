public class MergeTwoSortedLists {

    public static void main(String[] args) {
//        // Test Case - 1
//        ListNode l1_1 = new ListNode(1);
//        ListNode l1_2 = new ListNode(2);
//        ListNode l1_3 = new ListNode(4);
//
//        l1_1.next = l1_2;
//        l1_2.next = l1_3;
//        l1_3.next = null;
//
//        ListNode l2_1 = new ListNode(1);
//        ListNode l2_2 = new ListNode(3);
//        ListNode l2_3 = new ListNode(4);
//
//        l2_1.next = l2_2;
//        l2_2.next = l2_3;
//        l2_3.next = null;

//        ListNode newList = mergeTwoLists(l1_1, l2_1);

//        // Test case - 2
//        ListNode newList = mergeTwoLists(null, null);

        // Test case - 3
        ListNode l1_1 = new ListNode(1);
        ListNode l1_2 = new ListNode(2);
        ListNode l1_3 = new ListNode(4);

        l1_1.next = l1_2;
        l1_2.next = l1_3;
        l1_3.next = null;

        ListNode newList = mergeTwoLists(l1_1, null);

        while (newList != null) {
            System.out.print(newList.val + ",");
            newList = newList.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        // Empty node at head to make code more intuitive
        ListNode newList = new ListNode();
        ListNode newListHead = newList;

        while (l1 != null || l2 != null) {
            if (l1 == null) {
                newList.next = l2;
                break;
            }

            if (l2 == null) {
                newList.next = l1;
                break;
            }

            if (l1.val < l2.val) {
                newList.next = l1;
                l1 = l1.next;
            } else {
                newList.next = l2;
                l2 = l2.next;
            }

            newList = newList.next;
        }

        return newListHead.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
