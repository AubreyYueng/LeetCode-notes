package linkedlist.medium;

import linkedlist.ListNode;
import org.junit.Test;

import static linkedlist.ListNode.genNode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Yiyun On 2019/7/14 22:50
 *
 * 19. Remove Nth Node From End of List
 *
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 * Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 *
 * Note:
 * Given n will always be valid.
 *
 * Follow up:
 * Could you do this in one pass?
 *
 * TODO: Two pointers, NOTE when fast is null(case2 & case3)
 */
public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        if (fast == null)
            return head.next;

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return head;
    }

    @Test
    public void case1() {
        assertEquals(
                "(1,0)=>(2,1)=>(3,2)=>(5,4)=>",
                (removeNthFromEnd(genNode(new int[]{1,2,3,4,5}), 2).toString())
        );
    }

    @Test
    public void case2() {
        assertNull(
                removeNthFromEnd(genNode(new int[]{1}), 1)
        );
    }

    @Test
    public void case3() {
        assertEquals(
                "(2,1)=>",
                (removeNthFromEnd(genNode(new int[]{1,2}), 2).toString())
        );
    }

}
