package linkedlist.easy;

import linkedlist.ListNode;
import org.junit.Test;

import static java.lang.System.out;
import static linkedlist.ListNode.genNode;

/**
 * Created by Yiyun On 2019/7/16 21:15
 *
 * 83. Remove Duplicates from Sorted List
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * Example 1:
 * Input: 1->1->2
 * Output: 1->2
 *
 * Example 2:
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 */
public class RemoveDuplicatesFromSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        int lastVal = head.val;
        ListNode current = head;
        while (current.next != null) {
            ListNode next = current.next;
            if (next.val == lastVal) {
                current.next = current.next.next;
            } else {
                lastVal = next.val;
                current = next;
            }
        }

        return head;
    }

    @Test
    public void case1() {
        out.println(deleteDuplicates(genNode(new int[]{1,1,2})));
    }

    @Test
    public void case2() {
        out.println(deleteDuplicates(genNode(new int[]{1,1,2,3,3})));
    }

}
