package linkedlist.easy;

import linkedlist.ListNode;

import java.util.LinkedList;

/**
 * Created by Yiyun On 2020/6/10 17:03
 *
 * 234. Palindrome Linked List
 */
public class PalindromeLinkedList {

    // Note that Palindrome includes not only (a b b a) but also (a b c b a)
    // It's a two-pass solution because of size querying(for eliminating the central element)
    public boolean isPalindrome(ListNode head) {
        int size = listNodeSize(head);
        int central = size % 2 == 0 ? -1 : size / 2;

        LinkedList<Integer> stack = new LinkedList<>();
        ListNode ptr = head;
        int i = 0;
        while (ptr != null) {
            if (i != central) {
                if (!stack.isEmpty() && stack.peek() == ptr.val) {
                    stack.pop();
                } else
                    stack.push(ptr.val);
            }
            ptr = ptr.next;
            i++;
        }

        return stack.isEmpty();
    }

    private int listNodeSize(ListNode head) {
        int size = 0;
        ListNode ptr = head;
        while (ptr != null) {
            size++;
            ptr = ptr.next;
        }
        return size;
    }

}
