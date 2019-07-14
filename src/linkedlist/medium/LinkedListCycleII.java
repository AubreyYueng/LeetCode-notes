package linkedlist.medium;

import linkedlist.ListNode;
import org.junit.Test;

import static java.lang.System.out;
import static org.junit.Assert.assertNull;

/**
 * Created by Yiyun On 2019/7/14 16:39
 *
 * 142. Linked List Cycle II
 *
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed)
 * in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 *
 * Note: Do not modify the linked list.
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 *
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: tail connects to node index 0
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 *
 * Example 3:
 * Input: head = [1], pos = -1
 * Output: no cycle
 * Explanation: There is no cycle in the linked list.
 *
 * Follow-up:
 * Can you solve it without using extra space?
 *
 * TODO: Floyd's Loop detection algorithm, also see binary.search.hard.FindDuplicateNumber
 */
public class LinkedListCycleII {

    public ListNode detectCycle(ListNode head) {
//        out.println("head: " + head);
//        out.println();

        if (head == null)
            return null;

        // find intersection
        ListNode slow = head;
        ListNode fast = head;
        do {
            if (fast == null || fast.next == null)
                return null;

            slow = slow.next;
            fast = fast.next.next;
//            out.println("slow: " + slow + ", fast: " + fast);

            if (fast == null)
                return null;

        } while (slow != fast);

//        out.println("slow: " + slow + ", fast: " + fast);
        if (slow != fast)
            return null;

//        out.println();

        // find entrance
        ListNode ptr1 = head;
        ListNode ptr2 = slow;
        while (ptr1 != ptr2) {
//            out.println("ptr1: " + ptr1 + ", ptr2: " + ptr2);
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
//        out.println("ptr1: " + ptr1 + ", ptr2: " + ptr2);
        return ptr1;
    }

    private ListNode genNode(int[] nums, int pos) {
        if (nums.length == 0)
            return null;

        ListNode node = new ListNode(nums[0], 0);
        ListNode next = node;

        ListNode tail = null;

        for (int i = 1; i < nums.length; i++) {
            next.next = new ListNode(nums[i], i);
            next = next.next;

            if (i == pos)
                tail = next;
        }

        next.next = tail;
        return node;
    }

    @Test
    public void case1() {
        assertNull(detectCycle(genNode(new int[]{1,2}, -1)));
    }

    @Test
    public void case2() {
        out.println(detectCycle(genNode(new int[]{
                -1,-7,7,-4,19,6,-9,-5,-2,-5}, 7)).toString());
    }

    @Test
    public void case3() {
        out.println(detectCycle(genNode(new int[]{
                3,2,0,-4}, 1)).toString());
    }
}
