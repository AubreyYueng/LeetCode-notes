package linkedlist.hard;

import linkedlist.ListNode;

/**
 * Created by Yiyun On 2020/6/25 00:50
 *
 * 25. Reverse Nodes in k-Group
 */
public class ReverseNodesInKGroup {

    // Requirements:
    // 1. may not alter the value of nodes;
    // 2. constant space
    // Time: O(N), since we process each node exactly twice.
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1)
            return head;

        ListNode prevTail = null;
        ListNode res = head;
        ListNode currSt = head;
        ListNode nextSt = head;
        int i = k;
        // i: steps that nextSt still needs to move
        while (i >= 0) {
            if (i == 0) {
                // means nextSt already in its right place
                ListNode newHead = reverse(nextSt, currSt);
                if (prevTail == null)
                    res = newHead;
                else
                    prevTail.next = newHead;

                prevTail = currSt;
                currSt = nextSt;
                i = k;
            }

            if (nextSt == null)
                break;

            // nextSt keeps moving
            nextSt = nextSt.next;
            i--;
        }

        return res;
    }

    private ListNode reverse(ListNode last, ListNode curr) {
        ListNode prev = last;
        while (curr.next != last) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        curr.next = prev;
        return curr;
    }

}
