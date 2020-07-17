package tree.easy;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yiyun On 2020/7/16 22:31
 *
 * 257. Binary Tree Paths
 */
public class BinaryTreePaths {

    // Q: Given a binary tree, return all root-to-leaf paths.
    public List<String> binaryTreePaths(TreeNode root) {
        DFSHelper h = new DFSHelper();
        h.dfs(root, new ArrayList<>());
        return h.res;
    }

    private static class DFSHelper {
        List<String> res = new ArrayList<>();

        void dfs(TreeNode root, List<String> tmp) {
            if (root == null)
                return;

            tmp.add(String.valueOf(root.val));
            if (root.left == null && root.right == null)
                res.add(String.join("->", tmp));
            else {
                dfs(root.left, new ArrayList<>(tmp));
                dfs(root.right, new ArrayList<>(tmp));
            }
        }
    }

}
