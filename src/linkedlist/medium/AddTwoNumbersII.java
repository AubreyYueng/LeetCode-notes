package linkedlist.medium;

import linkedlist.ListNode;

import java.util.LinkedList;

/**
 * Created by Yiyun On 2020/7/2 20:44
 *
 * 445. Add Two Numbers II
 */
public class AddTwoNumbersII {

    // Require: can't not reverse/modify the input lists
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Don't try to convert to int/long: could be overflow
        LinkedList<Integer> s1 = toStack(l1);
        LinkedList<Integer> s2 = toStack(l2);

        int s = 0;
        ListNode curr = new ListNode(0);
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (!s1.isEmpty()) s += s1.pop();
            if (!s2.isEmpty()) s += s2.pop();

            curr.val = s % 10;
            s /= 10;
            ListNode head = new ListNode(s);
            head.next = curr;
            curr = head;
        }

        return curr.val == 0 ? curr.next : curr;
    }

    private LinkedList<Integer> toStack(ListNode l) {
        LinkedList<Integer> s = new LinkedList<>();
        while (l != null) {
            s.push(l.val);
            l = l.next;
        }
        return s;
    }

}
