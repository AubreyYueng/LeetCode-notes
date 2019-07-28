package tree.medium;

import tree.TreeNode;

import java.util.*;

/**
 * Created by Yiyun On 2019/7/27 21:38
 *
 * 236. Lowest Common Ancestor of a Binary Tree
 *
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as
 * the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 *
 * Note:
 * All of the nodes' values will be unique.
 * p and q are different and both values will exist in the binary tree.
 *
 * TODO: iterative is more intuitive and it needs revising
 */
public class LowestCommonAncestorOfABinaryTree {

    private TreeNode LCA = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        traverse(root, p, q);
        return LCA;
    }

    public boolean traverse(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) // If reached the end of a branch, return false.
            return false;
        // Left Recursion. If left recursion returns true, set left = 1 else 0
        int left = traverse(root.left, p, q) ? 1 : 0;
        // Right Recursion
        int right = traverse(root.right, p, q) ? 1 : 0;
        // If the current node is one of p or q
        int self = (root.val == p.val || root.val == q.val ? 1 : 0);

        // If any two of the flags left, right or mid become True
        if (left + right + self == 2) {
            LCA = root;
            return true;
        }

        // Return true if any one of the three bool values is True.
        return left + right + self > 0;
    }

    public TreeNode lowestCommonAncestorIteratively(TreeNode root, TreeNode p, TreeNode q) {

        // Stack for tree traversal
        Deque<TreeNode> stack = new ArrayDeque<>();

        // HashMap for parent pointers
        Map<TreeNode, TreeNode> parent = new HashMap<>();

        parent.put(root, null);
        stack.push(root);

        // Iterate until we find both the nodes p and q
        while (!parent.containsKey(p) || !parent.containsKey(q)) {

            TreeNode node = stack.pop();

            // While traversing the tree, keep saving the parent pointers.
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }

        // Ancestors set() for node p.
        Set<TreeNode> ancestors = new HashSet<>();

        // Process all ancestors for node p using parent pointers.
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }

        // The first ancestor of q which appears in
        // p's ancestor set() is their lowest common ancestor.
        while (!ancestors.contains(q))
            q = parent.get(q);
        return q;
    }

}
