package easy;


import org.junit.Test;

import static java.lang.System.out;

/**
 * Created by Yiyun on 2019/7/4 17:03
 *
 * No. 21 Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 *
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 *
 * TODO: 1. consider equal case
 * TODO: 2. consider l1.length and l2.length: so there is the "if (l1.next == null) l1.next = l2"
 */
public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l2 == null)
            return l1;
        if (l1 == null)
            return l2;

        if (l1.val > l2.val) {         // make sure l1.val < l2.val
            merge(l2, l1);
            return l2;
        } else {
            merge(l1, l2);
            return l1;
        }
    }

    private void merge(ListNode l1, ListNode l2) {
//        out.print("l1: " );
//        print(l1);
//        out.print(", l2: ");
//        print(l2);
//        out.print(", originalL1: " );
//        print(originalL1);
//        out.print(", originalL2: ");
//        print(originalL2);
//        out.println();

        if (l1.next == null) {
            l1.next = l2;
            return;
        }

        if (l2 == null)
            return;

        int first = l2.val;
        if (l1.next.val <= first)
            merge(l1.next, l2);
        else {
            ListNode newL2 = l1.next;
            l1.next = l2;
            merge(l1, newL2);
        }
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private static void print(ListNode node) {
        ListNode current = node;
        while (current != null) {
            out.print(current.val + "->");
            current = current.next;
        }
    }

    @Test
    public void case1() {
        ListNode listNode = mergeTwoLists(
                new ListNode(1, new ListNode(2, new ListNode(4))),
                new ListNode(1, new ListNode(3, new ListNode(4)))
        );
        print(listNode);
    }


}
