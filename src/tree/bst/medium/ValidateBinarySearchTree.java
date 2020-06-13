package tree.bst.medium;

import org.junit.Test;
import tree.TreeNode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static tree.TreeNode.genTree;

/**
 * Created by Yiyun On 2019/7/27 21:20
 *
 * 98. Validate Binary Search Tree
 *
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 * Example 1:
 *   2
 *  / \
 * 1   3
 * Input: [2,1,3]
 * Output: true
 *
 * Example 2:
 *   5
 *  / \
 * 1   4
 *    / \
 *   3   6
 * Input: [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 *
 * TODO: NOTE the definition of binary search tree: every node in right should be greater,
 * TODO: refer to case5 which is invalid(because 6 < 10): [10,5,15,null,null,6,20]
 */
public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    private boolean helper(TreeNode root, Integer upper, Integer lower) {
        if (root == null)
            return true;

        if (upper != null && root.val >= upper) return false;
        if (lower != null && root.val <= lower) return false;

        return helper(root.left, root.val, lower) && helper(root.right, upper, root.val);
    }

    @Test
    public void case1() {
        assertTrue(isValidBST(genTree(2,1,3)));
    }

    @Test
    public void case2() {
        assertFalse(isValidBST(genTree(5,1,4,null,null,3,6)));
    }

    @Test
    public void case3() {
        assertFalse(isValidBST(genTree(1,1)));
    }

    @Test
    public void case4() {
        assertTrue(isValidBST(genTree(0,-1)));
    }

    @Test
    public void case5() {
        assertFalse(isValidBST(genTree(10,5,15,null,null,6,20)));
    }

}
