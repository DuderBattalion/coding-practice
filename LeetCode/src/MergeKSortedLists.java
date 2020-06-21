import com.leetcode.util.ListNode;

import java.util.PriorityQueue;

public class MergeKSortedLists {
    public static void main(String[] args) {
        ListNode l1_1 = new ListNode(1);
        ListNode l1_2 = new ListNode(4);
        ListNode l1_3 = new ListNode(5);

        l1_1.next = l1_2;
        l1_2.next = l1_3;
        l1_3.next = null;

        ListNode l2_1 = new ListNode(1);
        ListNode l2_2 = new ListNode(3);
        ListNode l2_3 = new ListNode(4);

        l2_1.next = l2_2;
        l2_2.next = l2_3;
        l2_3.next = null;

        ListNode l3_1 = new ListNode(2);
        ListNode l3_2 = new ListNode(6);

        l3_1.next = l3_2;
        l3_2.next = null;

        ListNode[] lists = { l1_1, l2_1, l3_1 };

        ListNode output = mergeKLists(lists);
        while (output != null) {
            System.out.print(output.val + ",");
            output = output.next;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        for (ListNode list: lists) {
            while (list != null) {
                minHeap.add(list.val);
                list = list.next;
            }
        }

        ListNode mergeListHead = new ListNode();
        ListNode mergeList = mergeListHead;

        while (!minHeap.isEmpty()) {
            ListNode node = new ListNode();
            node.val = minHeap.poll();
            node.next = null;

            mergeList.next = node;
            mergeList = node;
        }

        return mergeListHead.next;
    }
}
