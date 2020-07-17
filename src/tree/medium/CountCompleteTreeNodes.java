package tree.medium;

import tree.TreeNode;

/**
 * Created by Yiyun On 2020/7/16 21:55
 *
 * 222. Count Complete Tree Nodes
 */
public class CountCompleteTreeNodes {

    // Question: given a complete binary tree, count the number of nodes
    // The following codes are copied from LC Solution
    public int countNodes(TreeNode root) {
        return root == null ? 0 : 1 + countNodes(root.right) + countNodes(root.left);
    }

}
