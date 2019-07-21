package linkedlist.medium;

import linkedlist.ListNode;
import org.junit.Test;

import static java.lang.System.out;
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

    public ListNode revisedRecursiveReverseBetween(ListNode head, int m, int n) {
        new ReviseRecursive(head, m, n).reverse(head);
        return head;
    }

    private static class ReviseRecursive {
        private ListNode low;
        private int threshold;
        private int m;
        private int n;

        ReviseRecursive(ListNode low, int m, int n) {
            this.low = low;
            this.m = m;
            this.n = n;
            this.threshold = n - (m + n + 1) / 2 + 1;
        }

        private void reverse(ListNode high) {
            revisedRecursiveReverseBetween(high, m, n);
        }

        /**
         * TODO: re-think about: if threshold is not set, what will happen
         */
        private void revisedRecursiveReverseBetween(ListNode high, int m, int n) {
            if (n == 1) return;

            if (m > 1) {
                low = low.next;
                m--;
            }
            if (n > 1) {
                high = high.next;
                n--;
            }

            revisedRecursiveReverseBetween(high, m, n);
            if (n <= threshold) {
                int valueLowTmp = low.val;
                low.val = high.val;
                high.val = valueLowTmp;
                this.low = this.low.next;
            }
        }

    }

    /**
     * Leet Code Recursive Solution
     */
    public ListNode recursivelyReverseBetween(ListNode head, int m, int n) {
        new RecursiveSample(head).recurseAndReverse(head, m, n);
        return head;
    }

    private static class RecursiveSample {
        // Object level variables since we need the changes
        // to persist across recursive calls and Java is pass by value.
        private boolean stop;
        private ListNode left;

        RecursiveSample(ListNode head) {
            this.left = head;
        }

        private void recurseAndReverse(ListNode right, int m, int n) {
            out.println("left: " + left.toStr() + ", right: " + right.toStr() + ", m: " + m + ", n: " + n);

            // base case. Don't proceed any further
            if (n == 1) return;

            // Keep moving the right pointer one step forward until (n == 1)
            right = right.next;

            // Keep moving left pointer to the right until we reach the proper node
            // from where the reversal is to start.
            if (m > 1) this.left = this.left.next;
            // Recurse with m and n reduced.
            this.recurseAndReverse(right, m - 1, n - 1);
            // In case both the pointers cross each other or become equal, we
            // stop i.e. don't swap data any further. We are done reversing at this
            // point.
            if (this.left == right || right.next == this.left)
                this.stop = true;
            // Until the boolean stop is false, swap data between the two pointers
            if (!this.stop) {
                out.println("in --- left: " + left.toStr() + ", right: " + right.toStr() + ", m: " + m + ", n: " + n);
                int t = this.left.val;
                this.left.val = right.val;
                right.val = t;

                out.println("out --- left: " + left.toStr() + ", right: " + right.toStr() + ", m: " + m + ", n: " + n);

                // Move left one step to the right.
                // The right pointer moves one step back via backtracking.
                this.left = this.left.next;
            }
            out.println();
        }
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

    @Test
    public void recursivelySampleCase1() {
        assertEquals(
                "7=>9=>8=>1=>10=>2=>6=>",
                revisedRecursiveReverseBetween(ListNode.genNode(7,9,2,10,1,8,6), 3, 6).toStr());
    }

    @Test
    public void recursivelyRevisedCase1() {
        assertEquals(
                "7=>9=>8=>1=>10=>2=>6=>",
                recursivelyReverseBetween(ListNode.genNode(7,9,2,10,1,8,6), 3, 6).toStr());
    }

}
