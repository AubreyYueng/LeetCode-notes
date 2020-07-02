package bfs.medium;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yiyun On 2020/7/2 19:26
 *
 * 107. Binary Tree Level Order Traversal II
 */
public class BinaryTreeLevelOrderTraversalII {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null)
            return res;

        LinkedList<TreeNode> first = new LinkedList<>();
        LinkedList<TreeNode> second = new LinkedList<>();
        first.add(root);

        while (!first.isEmpty() || !second.isEmpty()) {
            res.addFirst(process(first, second));
            res.addFirst(process(second, first));
        }

        res.removeIf(List::isEmpty);
        return res;
    }

    private List<Integer> process(LinkedList<TreeNode> removed, LinkedList<TreeNode> added) {
        List<Integer> items = new LinkedList<>();
        while (!removed.isEmpty()) {
            TreeNode node = removed.remove();
            items.add(node.val);

            if (node.left != null)
                added.add(node.left);
            if (node.right != null)
                added.add(node.right);
        }

        return items;
    }

}
