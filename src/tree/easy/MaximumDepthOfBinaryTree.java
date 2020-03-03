package tree.easy;

import javafx.util.Pair;
import tree.TreeNode;

import java.util.LinkedList;

/**
 * Created by Yiyun On 2020/3/3 12:13
 *
 * 104. Maximum Depth of Binary Tree
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */
public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        // bfs
        if (root == null)
            return 0;
        LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        int depth = 1;
        queue.add(new Pair<>(root, depth));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> curr = queue.poll();

            TreeNode h = curr.getKey();
            if (h == null)
                continue;   // or we verify before add to queue like in '111. Minimum Depth of Binary Tree'

            depth = curr.getValue();
            if (h.left == null && h.right == null)
                continue;       // <- the only difference between minDepth and maxDepth
            depth++;
            queue.add(new Pair<>(h.left, depth));
            queue.add(new Pair<>(h.right, depth));

        }
        return depth;
    }

}
