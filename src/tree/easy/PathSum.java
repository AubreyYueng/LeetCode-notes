package tree.easy;

import tree.TreeNode;

import static org.junit.Assert.assertFalse;

/**
 * Created by Yiyun On 2020/6/8 22:57
 *
 * 112. Path Sum
 */
public class PathSum {

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;

        sum -= root.val;
        if (root.left == null && root.right == null)
            return sum == 0;
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }



}
