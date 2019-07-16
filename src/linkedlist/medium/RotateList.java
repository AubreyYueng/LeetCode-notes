package linkedlist.medium;

import linkedlist.ListNode;
import org.junit.Test;

import static java.lang.System.out;
import static linkedlist.ListNode.genNode;
import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/7/16 21:30
 *
 * 61. Rotate List
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.

 * Example 1:
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 *
 * Example 2:
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 */
public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null)
            return head;

        int size = 1;
        ListNode last = head;
        while (last.next != null) {
            size++;
            last = last.next;
        }

        ListNode newTail = head;
        for (int i = 1; i < size - k % size; i++) {
            newTail = newTail.next;
        }
        if (newTail.next == null)
            return head;

        ListNode newHead = newTail.next;
        newTail.next = null;
        last.next = head;

        return newHead;
    }

    @Test
    public void case1() {
        assertEquals("(2,2)=>(0,0)=>(1,1)=>", (rotateRight(genNode(0,1,2),4)).toString());
    }

    @Test
    public void case2() {
        assertEquals("(4,3)=>(5,4)=>(1,0)=>(2,1)=>(3,2)=>", (rotateRight(genNode(1,2,3,4,5),2)).toString());
    }

}
