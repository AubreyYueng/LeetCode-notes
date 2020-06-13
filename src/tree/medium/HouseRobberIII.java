package tree.medium;

import tree.TreeNode;

/**
 * Created by Yiyun On 2020/6/13 07:20
 *
 * 337. House Robber III
 */
public class HouseRobberIII {

    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    // res[0]: no rub
    // res[1]: rub
    private int[] dfs(TreeNode root) {
        int[] res = new int[2];
        if (root == null)
            return res;

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        res[1] = root.val + left[0] + right[0];
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return res;
    }

}
