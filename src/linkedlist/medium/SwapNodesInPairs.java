package linkedlist.medium;

import linkedlist.ListNode;
import org.junit.Test;

import static java.lang.System.out;
import static linkedlist.ListNode.genNode;
import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/7/16 22:04
 *
 * 24. Swap Nodes in Pairs
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * Example:
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 */
public class SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode newHead = head.next;
        ListNode lastSecond = null;

        ListNode first = head;
        ListNode second = first.next;
        while (first != null) {
            if (first.next != null) {
                first.next = second.next;
                second.next = first;
            } else {
                second = first;
            }
            if (lastSecond != null) lastSecond.next = second;
            lastSecond = first;

            first = first.next;
            second = first == null ? null : first.next;
        }

        return newHead;
    }

    @Test
    public void case1() {
        assertEquals("(2,1)=>(1,0)=>(5,3)=>(3,2)=>(6,4)=>", (swapPairs(genNode(1,2,3,5,6))));
    }

}
