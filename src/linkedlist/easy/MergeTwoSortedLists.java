package linkedlist.easy;


import linkedlist.ListNode;
import org.junit.Test;

import static java.lang.System.out;

/**
 * Created by Yiyun on 2019/7/4 17:03
 *
 * No. 21 Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 *
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 *
 * TODO: 1. consider equal case
 * TODO: 2. consider l1.length and l2.length: so there is the "if (l1.next == null) l1.next = l2"
 * TODO: 3. mergeTwoLists_1 and mergeTwoLists_2 are cleaner solutions
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
        if (l1.next == null) {
            l1.next = l2;
            return;
        }

        if (l2 == null)
            return;

        if (l1.next.val <= l2.val)
            merge(l1.next, l2);
        else {
            ListNode newL2 = l1.next;
            l1.next = l2;
            merge(l1, newL2);
        }
    }

    /** l: left; r: right; {l1},{l2}
     * (l1->2->l4),(r1->3->r4)
     * l1 -> (2->l4),(r1->3->r4)
     *       r1 -> (2->l4),(3->r4)
     *             2 -> (l4),(3->r4)
     *                  3 -> (l4),(r4)
     *                       l4 -> null,(r4)
     *                             r4
     */
    public ListNode mergeTwoLists_1(ListNode l1, ListNode l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val){
            l1.next = mergeTwoLists_1(l1.next, l2);
            return l1;
        } else{
            l2.next = mergeTwoLists_1(l1, l2.next);
            return l2;
        }
    }

    public ListNode mergeTwoLists_2(ListNode l1, ListNode l2){
        if (l1 == null) return l2;
        if(l2 == null) return l1;
        if (l1.val > l2.val)
            return mergeTwoLists_2(l2, l1);
        l1.next = mergeTwoLists_2(l1.next, l2);
        return l1;
    }

    @Test
    public void case1() {
        ListNode listNode = mergeTwoLists_2(
                new ListNode(1, new ListNode(2, new ListNode(4))),
                new ListNode(1, new ListNode(3, new ListNode(4)))
        );
        out.println(listNode.toStr());
    }


}
