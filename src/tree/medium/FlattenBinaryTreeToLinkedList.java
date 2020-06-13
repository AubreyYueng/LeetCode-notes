package tree.medium;

import tree.TreeNode;

/**
 * Created by Yiyun On 2020/6/13 05:15
 *
 * 114. Flatten Binary Tree to Linked List
 */
public class FlattenBinaryTreeToLinkedList {

    // Requires in-place
    // Divide and conquer: 0. end condition 1. flatten left 2. flatten right 3. combine
    public void flatten(TreeNode root) {
        if (root == null)
            return;

        flatten(root.left);
        flatten(root.right);

        if (root.left != null) {
            TreeNode left = root.left;
            TreeNode right = root.right;
            root.left = null;
            root.right = left;

            TreeNode leftLast = left;
            while (leftLast.right != null) {
                leftLast = leftLast.right;
            }
            leftLast.right = right;
        }
    }

}
