package linkedlist.medium;

import linkedlist.ListNode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/7/22 06:23
 *
 * 148. Sort List
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Example 1:
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 *
 * Example 2:
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 *
 * TODO: merge sort (refer to class MergeTwoSortedLists)
 */
public class SortList {

    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode mid = findMiddle(head);
        ListNode right = sortList(mid.next);
        mid.next = null;
        ListNode left = sortList(head);
        return mergeTwoSortedList(left, right);
    }

    /**
     * TODO: WARN!!! if fast = head, then it will result in dead loop (think about the head pointing to only 2 nodes)
     */
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode mergeTwoSortedList(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if(l2 == null) return l1;
        if (l1.val > l2.val)
            return mergeTwoSortedList(l2, l1);
        l1.next = mergeTwoSortedList(l1.next, l2);
        return l1;
    }

    @Test
    public void case1() {
        assertEquals("-1=>0=>3=>4=>5=>", sortList(ListNode.genNode(-1,5,3,4,0)).toStr());
    }

}
