package linkedlist.easy;

import linkedlist.ListNode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/7/17 17:06
 *
 * 206. Reverse a singly linked list
 *
 * Example:
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 *
 * Follow up:
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 *
 * TODO: NOTE to implement it either iteratively or recursively
 */
public class ReverseLinkedList {

    /**
     * Iteratively
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTmp;
        }
        return prev;
    }

    /**
     * Assume that the rest of the list had already been reversed, now how do I reverse the front part?
     * Let's assume the list is: n1 → … → nk-1 → nk → nk+1 → … → nm → Ø
     * Assume from node nk+1 to nm had been reversed and you are at node nk.
     * n1 → … → nk-1 → nk → nk+1 ← … ← nm
     * We want nk+1’s next node to point to nk.
     * So,
     * nk.next.next = nk;
     * Be very careful that n1's next must point to Ø. If you forget about this, your linked list has a cycle in it. This bug could be caught if you test your code with a linked list of size 2.
     */
    public ListNode recursivelyReverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode p = recursivelyReverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    @Test
    public void iterativelyCase1() {
        assertEquals("(5,4)=>(4,3)=>(3,2)=>(2,1)=>(1,0)=>", reverseList(ListNode.genNode(1, 2, 3, 4, 5)).toString());
    }

    @Test
    public void recursivelyCase1() {
        assertEquals("(5,4)=>(4,3)=>(3,2)=>(2,1)=>(1,0)=>", recursivelyReverseList(ListNode.genNode(1, 2, 3, 4, 5)).toString());
    }
}
