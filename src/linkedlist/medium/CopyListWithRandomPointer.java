package linkedlist.medium;

import linkedlist.Node;

/**
 * Created by Yiyun On 2020/3/3 17:03
 *
 * 138. Copy List with Random Pointer
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyListWithRandomPointer {

    public Node copyRandomList(Node head) {
        if (head == null)
            return null;
        // We can maintain an oldNode->newNode map with O(N) space,
        // but here's a O(1) space solution:
        // we just use the value of original node to create the cloned copy,
        // interweaving with the original linkedlist,
        // modify the pointer directions then remove all original nodes in the end
        Node ptr = head;

        while(ptr != null) {
            Node newNode = new Node(ptr.val);
            newNode.next = ptr.next;
            ptr.next = newNode;
            ptr = ptr.next.next;
        }

        ptr = head;
        while(ptr != null) {
            // WARN!!! wrong to say "ptr.next.random = ptr.random", because random has also to be new
            ptr.next.random = (ptr.random==null) ? null: ptr.random.next;
            ptr = ptr.next.next;
        }

        Node newHead = head.next;
        ptr = head;
        while(ptr != null) {
            Node nextOriginal = ptr.next.next;
            if(nextOriginal != null) {
                ptr.next.next = nextOriginal.next;
            }
            ptr.next = nextOriginal;
            ptr = nextOriginal;
        }

        return newHead;
    }

}
