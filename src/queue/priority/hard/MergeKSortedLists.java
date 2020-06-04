package queue.priority.hard;

import linkedlist.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Yiyun On 2020/5/22 23:55
 *
 * 23. Merge k Sorted Lists
 */
public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        // The head of this queue is the least element with respect to the specified ordering. If multiple elements
        // are tied for least value, the head is one of those elements — ties are broken arbitrarily

        // Here's the optimized one by one version using PQ
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparing(ln -> ln.val));
        for (ListNode l : lists) {
            if (l != null)
                pq.add(l);
        }

        // Remember this way of initialization to simplify everything!!!
        ListNode head = new ListNode(0);
        ListNode curr = head;
        while (!pq.isEmpty()) {
            // actually we don't need extra space here, simply we set tmp = pq.poll() is also ok.
            ListNode peek = pq.poll();
            curr.next = new ListNode(peek.val);
            curr = curr.next;

            if (peek.next != null)
                pq.add(peek.next);
        }

        return head.next;
    }

}
