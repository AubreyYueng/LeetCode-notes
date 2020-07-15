package linkedlist.medium;

import linkedlist.ListNode;
import org.junit.Test;

/**
 * Created by Yiyun On 2020/7/14 18:50
 *
 * 82. Remove Duplicates from Sorted List II
 */
public class RemoveDuplicatesFromSortedListII {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode dumbHead = new ListNode(0);
        dumbHead.next = head;

        ListNode pre = dumbHead;
        ListNode curr = pre.next;
        while (curr != null) {
            int currVal = curr.val;
            ListNode next = curr.next;
            boolean deleted = false;
            while (next != null && next.val == currVal) {
                next = next.next;
                pre.next = next;
                deleted = true;
            }
            if (!deleted) pre = pre.next;
            curr = next;
        }
        return dumbHead.next;
    }

    @Test
    public void case1() {
        System.out.println(deleteDuplicates(ListNode.genNode(1, 2, 3, 3, 4, 4, 5)));
    }

    @Test
    public void case2() {
        System.out.println(deleteDuplicates(ListNode.genNode(1, 1, 1, 2, 3)));
    }

}
