package tree.hard;

import tree.TreeNode;

/**
 * Created by Yiyun On 2020/6/9 00:12
 *
 * 124. Binary Tree Maximum Path Sum
 */
public class BinaryTreeMaximumPathSum {

    public int maxPathSum(TreeNode root) {
        Helper helper = new Helper(root);
        return helper.max;
    }

    private static class Helper {
        private int max = Integer.MIN_VALUE;

        private Helper(TreeNode root) {
            maxSinglePath(root);
        }

        private int maxSinglePath(TreeNode root) {
            if (root == null) {
                return 0;
            }

            // if maxSinglePath result from left is negative, we won't use it thus 0.
            int fromLeft = Math.max(maxSinglePath(root.left), 0);
            int fromRight = Math.max(maxSinglePath(root.right), 0);
            // Why we don't compare root.val+left or root.val+right, because obviously they <= sum of all
            max = Math.max(max, root.val + fromLeft + fromRight);

            return root.val + Math.max(fromLeft, fromRight);
        }

    }

}
