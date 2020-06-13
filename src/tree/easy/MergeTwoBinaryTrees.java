package tree.easy;

import tree.TreeNode;

/**
 * Created by Yiyun On 2020/6/13 06:03
 *
 * 617. Merge Two Binary Trees
 */
public class MergeTwoBinaryTrees {

    // similar to 114. Flatten Binary Tree to Linked List
    // divide and conquer: 0. end condition 1. merge left 2. merge right 3. combine
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        if (t2 == null)
            return t1;

        t1.val+=t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

}
