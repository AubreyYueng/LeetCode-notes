package tree.easy;

import tree.TreeNode;

/**
 * Created by Yiyun On 2020/6/12 23:23
 *
 * 437. Path Sum III
 */
public class PathSumIII {

    // path starts from any node
    public int pathSum(TreeNode root, int sum) {
        if (root == null)
            return 0;

        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    // node could be negative, thus we can't stop searching when sum==0
    private int pathSumFrom(TreeNode root, int sum) {
        if (root == null)
            return 0;

        int res = sum == root.val ? 1 : 0;

        sum -= root.val;
        return res + pathSumFrom(root.left, sum) + pathSumFrom(root.right, sum);
    }

}
