package linkedlist.medium;

import linkedlist.ListNode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/7/17 17:08
 *
 * 92. Reverse Linked List II
 * Reverse a linked list from position m to n. Do it in one-pass.
 * Note: 1 ≤ m ≤ n ≤ length of list.

 * Example:
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 *
 * TODO: NOTE to implement it either iteratively or recursively
 */
public class ReverseLinkedListII {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode prevSt = null;
        ListNode st = head;
        for (int i = 0; i < m-1; i++) {
            prevSt = st;
            st = st.next;
        }

        ListNode prev = st;
        ListNode curr = st.next;
        for (int i = 0; i < n - m; i++) {
            ListNode nextTmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTmp;
        }

        st.next = curr;
        if (prevSt != null) {
            prevSt.next = prev;
            return head;
        }
        return prev;
    }

    @Test
    public void iterativelyCase1() {
        assertEquals(
                "(1,0)=>(4,3)=>(3,2)=>(2,1)=>(5,4)=>",
                reverseBetween(ListNode.genNode(1,2,3,4,5), 2, 4).toString());
    }

    @Test
    public void iterativelyCase2() {
        assertEquals(
                "(5,1)=>(3,0)=>",
                reverseBetween(ListNode.genNode(3,5), 1, 2).toString());
    }

}
