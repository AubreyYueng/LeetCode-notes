package twopointers.easy;

import linkedlist.ListNode;

/**
 * Created by Yiyun On 2020/6/13 02:47
 *
 * 141. Linked List Cycle
 */
public class LinkedListCycle {

    // If we use Hash Table it costs O(n) space,
    // If we use Two Pointers it costs time O(N+K)=O(n) where K is at most for cyclic length
    public boolean hasCycle(ListNode head) {
        // Copied from Solution
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

}
