package tree.easy;

import tree.TreeNode;

/**
 * Created by Yiyun On 2020/7/29 21:19
 *
 * 100. Same Tree
 */
public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p != null && q != null) {
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return p == null && q == null;
    }

}
