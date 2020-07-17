package linkedlist.medium;

import linkedlist.ListNode;

/**
 * Created by Yiyun On 2020/7/16 23:01
 *
 * 328. Odd Even Linked List
 */
public class OddEvenLinkedList {

    // The following codes are mostly copied from LC Solution
    // Not hard, but remember to firstly consider the general cases.
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode odd = head;    // the first node is considered odd
        ListNode even = odd.next;
        ListNode evenH = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenH;
        return head;
    }

}
