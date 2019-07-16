package linkedlist;

import linkedlist.medium.LinkedListCycleII;

/**
 * Created by Yiyun On 2019/7/14 22:52
 */
public class ListNode {

    public int val;
    public int index;
    public ListNode next;

    public ListNode(int x, int index) {
        val = x;
        this.index = index;
        next = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("(").append(val).append(",").append(index).append(")").append("=>");
        ListNode next = this.next;
        int i = 0;
        while (next != null && i < 15) {
            sb.append("(").append(next.val).append(",").append(next.index).append(")").append("=>");
            next = next.next;
            i++;
        }
        return sb.toString();
    }

    public static ListNode genNode(int... nums) {
        if (nums.length == 0)
            return null;

        ListNode node = new ListNode(nums[0], 0);
        ListNode next = node;

        for (int i = 1; i < nums.length; i++) {
            next.next = new ListNode(nums[i], i);
            next = next.next;
        }

        return node;
    }
}
