package bfs.medium;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yiyun On 2020/7/1 22:01
 *
 * 103. Binary Tree Zigzag Level Order Traversal
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // natural order: add value like stack, remove value like queue
        LinkedList<TreeNode> dual = new LinkedList<>();
        // reverse order
        LinkedList<TreeNode> stack = new LinkedList<>();

        List<List<Integer>> res = new LinkedList<>();

        dual.add(root);

        while (!dual.isEmpty() || !stack.isEmpty()) {
            List<Integer> natural = new LinkedList<>();
            List<Integer> reverse = new LinkedList<>();

            while (!dual.isEmpty()) {
                TreeNode curr = dual.remove();
                if (curr != null) {
                    natural.add(curr.val);
                    stack.push(curr.left);
                    stack.push(curr.right);
                }
            }

            while (!stack.isEmpty()) {
                TreeNode curr = stack.pop();
                if (curr != null) {
                    reverse.add(curr.val);
                    dual.push(curr.right);
                    dual.push(curr.left);
                }
            }

            if (!natural.isEmpty())
                res.add(natural);
            if (!reverse.isEmpty())
                res.add(reverse);
        }

        return res;
    }

}
