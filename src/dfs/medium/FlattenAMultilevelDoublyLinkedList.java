package dfs.medium;

import org.junit.Test;

/**
 * Created by Yiyun On 2020/7/27 20:30
 *
 * 430. Flatten a Multilevel Doubly Linked List
 */
public class FlattenAMultilevelDoublyLinkedList {

    public Node flatten(Node head) {
        if (head == null) return null;
        dfs(head);
        return head;
    }

    private Node dfs(Node node) {
        if (node.next == null && node.child == null) return node;
        Node oldNext = node.next;
        Node oldChd = node.child;

        if (oldChd != null) {
            // build double link between node and its child
            node.next = node.child;
            node.child.prev = node;
            node.child = null;

            Node leafOfChd = dfs(oldChd);
            // build double link between leaf of its child and oldNext
            if (oldNext != null) {
                leafOfChd.next = oldNext;
                oldNext.prev = leafOfChd;
            }
        }

        // processing oldNext
        return oldNext != null ? dfs(oldNext) : node;
    }

    private static class Node {
        int val;
        Node prev;
        Node next;
        Node child;

        Node() {}

        static Node gen(Integer... vals) {
            Node head = new Node(vals[0]);
            Node prev = head;
            for (int i = 1; i < vals.length; i++) {
                Integer v = vals[i];
                Node curr = new Node(v);
                curr.prev = prev;
                prev.next = curr;
                prev = curr;
            }
            return head;
        }

        Node(int val) {
            this.val = val;
        }
    };

    @Test
    // Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
    // Output: [1,2,3,7,8,11,12,9,10,4,5,6]
    public void case1() {
        Node head = Node.gen(1,2,3,4,5,6);
        Node chd1 = Node.gen(7,8,9,10);
        Node chd2 = Node.gen(11, 12);

        chd1.next.child = chd2;
        head.next.next.child = chd1;
        flatten(head);
    }

    @Test
    public void case2() {
        Node head = Node.gen(1);
        head.child = Node.gen(2);
        head.child.child = Node.gen(3);
        flatten(head);
    }

}
