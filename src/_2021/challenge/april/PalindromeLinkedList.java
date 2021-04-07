package _2021.challenge.april;

import linkedlist.ListNode;

/**
 * Created by Yiyun On 2021/4/7 07:31
 * Palindrome Linked List
 */
public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        ListNode mid = findMid(head);
        System.out.println(mid.val);
        ListNode last = reverseHalfNRetLast(mid);
        return checkFirstNLast(head, last);
    }

    ListNode findMid(ListNode head) {
        ListNode curr = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            curr = curr.next;
            fast = fast.next.next;
        }
        return curr;
    }

    ListNode reverseHalfNRetLast(ListNode mid) {
        ListNode prev = mid;
        ListNode curr = mid.next;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    boolean checkFirstNLast(ListNode first, ListNode last) {
        while (first.next != last && first != last) {
            if (first.val != last.val)
                return false;

            first = first.next;
            last = last.next;
        }
        return first.val == last.val;
    }

}
