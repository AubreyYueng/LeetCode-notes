package tree.medium;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Yiyun On 2019/7/27 21:47
 *
 * 173. Binary Search Tree Iterator
 *
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 *
 * Calling next() will return the next smallest number in the BST.
 *
 * Example:
 * tree: [7,3,15,null,null,9,20]
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // return 3
 * iterator.next();    // return 7
 * iterator.hasNext(); // return true
 * iterator.next();    // return 9
 * iterator.hasNext(); // return true
 * iterator.next();    // return 15
 * iterator.hasNext(); // return true
 * iterator.next();    // return 20
 * iterator.hasNext(); // return false
 *
 * Note:
 * next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the
 * BST when next() is called.
 */
public class BSTIterator {

    private static LinkedList<Integer> nodes;

    public BSTIterator(TreeNode root) {
        nodes = new LinkedList<>();
        Stack<TreeNode> inOrderIterator = new Stack<>();

        TreeNode curr = root;
        while (!inOrderIterator.isEmpty() || curr != null) {
            while (curr != null) {
                inOrderIterator.push(curr);
                curr = curr.left;
            }

            curr = inOrderIterator.pop();
            nodes.add(curr.val);
            curr = curr.right;
        }
    }

    /** @return the next smallest number */
    public int next() {
        return nodes.pop();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !nodes.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
