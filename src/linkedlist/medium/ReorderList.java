package linkedlist.medium;

import linkedlist.ListNode;

/**
 * Created by Yiyun On 2020/7/2 02:00
 *
 * 143. Reorder List
 */
public class ReorderList {

    public void reorderList(ListNode head) {
        if (head == null) return;

        // 1. find middle
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 2. reverse second part
        ListNode prev = null, curr = slow, tmp = null;
        while (curr != null) {
            tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        // 3. merge with the first part
        ListNode first = head, second = prev;
        while (second.next != null) {
            tmp = first.next;
            first.next = second;
            first = tmp;

            tmp = second.next;
            second.next = first;
            second = tmp;
        }
    }

}
