package tree.easy;

import org.junit.Test;
import tree.TreeNode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static tree.TreeNode.genTree;

/**
 * Created by Yiyun On 2019/7/27 21:26
 *
 * 101. Symmetric Tree
 *
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * But the following [1,2,2,null,3,null,3] is not:
 *   1
 *  / \
 * 2   2
 *  \   \
 *  3    3
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 */
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return helper(root.left, root.right);
    }

    private boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left != null && right != null && left.val == right.val)
            return helper(left.left, right.right) && helper(left.right, right.left);
        return false;
    }

    @Test
    public void case1() {
        assertTrue(isSymmetric(genTree(1,2,2,3,4,4,3)));
    }

    @Test
    public void case2() {
        assertFalse(isSymmetric(genTree(1,2,2,null,3,null,3)));
    }

}
