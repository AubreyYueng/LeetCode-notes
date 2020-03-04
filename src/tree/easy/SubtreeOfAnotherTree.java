package tree.easy;

import tree.TreeNode;

/**
 * Created by Yiyun On 2020/3/3 21:40
 *
 * 572. Subtree of Another Tree
 * https://leetcode.com/problems/subtree-of-another-tree/
 */
public class SubtreeOfAnotherTree {

    // time complexity: O(M*N). worst case: O(M*N)
    // space complexity: O(N). The depth of the recursion tree can go upto n(number of nodes in s)
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return traverse(s, t);
    }

    private boolean traverse(TreeNode s, TreeNode t) {
        return s != null && (equals(s, t) || traverse(s.left, t) || traverse(s.right, t));
    }

    private boolean equals(TreeNode a, TreeNode b) {
        if(a == null && b == null) return true;
        if(a == null || b == null) return false;
        return a.val == b.val && equals(a.left, b.left) && equals(a.right, b.right);
    }

}
