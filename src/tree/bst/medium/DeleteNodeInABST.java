package tree.bst.medium;

import tree.TreeNode;

/**
 * Created by Yiyun On 2020/7/21 22:32
 *
 * 450. Delete Node in a BST
 */
public class DeleteNodeInABST {

    // Following codes are mostly learned from LC Solution
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        // delete from the right subtree
        if (key > root.val) root.right = deleteNode(root.right, key);
        // delete from the left subtree
        else if (key < root.val) root.left = deleteNode(root.left, key);
        // delete the current node
        else {
            // node is a leaf
            if (root.left == null && root.right == null) root = null;
            else {  // node is not a leaf
                // has a right child
                if (root.right != null) {
                    root.val = successor(root);
                    root.right = deleteNode(root.right, root.val);  // delete successor
                }
                // has a left child
                else {
                    root.val = predecessor(root);
                    root.left = deleteNode(root.left, root.val);    // delete predecessor
                }
            }
        }
        return root;
    }

    private int successor(TreeNode root) {
        root = root.right;
        while (root.left != null) root = root.left;
        return root.val;
    }

    private int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) root = root.right;
        return root.val;
    }

}
