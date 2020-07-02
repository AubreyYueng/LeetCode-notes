package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yiyun On 2020/7/1 22:46
 *
 * 113. Path Sum II
 */
public class PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null)
            return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();
        dfs(res, root, sum, new ArrayList<>());
        return res;
    }

    private void dfs(List<List<Integer>> res, TreeNode root, int sum, List<Integer> tmp) {
        int val = root.val;

        tmp.add(val);

        if (root.left == null && root.right == null) {
            if (val == sum)
                res.add(tmp);
            return;
        }

        if (root.left != null)
            dfs(res, root.left, sum-val, new ArrayList<>(tmp));
        if (root.right != null)
            dfs(res, root.right, sum-val, new ArrayList<>(tmp));
    }



}
