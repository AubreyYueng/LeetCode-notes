package tree.medium;

import tree.TreeNode;

/**
 * Created by Yiyun On 2020/7/15 21:24
 *
 * 129. Sum Root to Leaf Numbers
 */
public class SumRootToLeafNumbers {

    // Here we use pre-order traversal for the convenience of constructing number from root to leaf,
    // which is done by dfs: root->dfs(left)->dfs(right)
    public int sumNumbers(TreeNode root) {
        Helper h = new Helper();
        h.dfs(0, root);
        return h.res;
    }

    private static class Helper {
        int res;

        private void dfs(int currSum, TreeNode currRoot) {
            if (currRoot == null) return;

            currSum=currSum*10+currRoot.val;
            if (currRoot.left == null && currRoot.right == null) {
                res += currSum;
                return;
            }

            dfs(currSum, currRoot.left);
            dfs(currSum, currRoot.right);
        }
    }


}
