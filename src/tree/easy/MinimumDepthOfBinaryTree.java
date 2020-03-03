package tree.easy;

import javafx.util.Pair;
import tree.TreeNode;

import java.util.LinkedList;

/**
 * Created by Yiyun On 2020/3/3 02:01
 *
 * 111. Minimum Depth of Binary Tree
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 */
public class MinimumDepthOfBinaryTree {

    // When using recursion, analyze the space complexity
    public int minDepth(TreeNode root) {
        // bfs
        if (root == null)
            return 0;
        // we can use Map<TreeNode, Integer> to store the depth(see 994. Rotting Oranges)
        // but we can also use queue of pair fo combine queue with map of depth together
        LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        int depth = 1;
        queue.add(new Pair<>(root, depth));
        while (!queue.isEmpty()) {
            // the difference between poll and remove:
            // poll: return null if no element
            // remove: throws NoSuchElementException when no element
            Pair<TreeNode, Integer> curr = queue.poll();
            TreeNode h = curr.getKey();
            depth = curr.getValue();
            if (h.left == null && h.right == null)
                return depth;
            depth++;
            if (h.left != null)
                queue.add(new Pair<>(h.left, depth));
            if (h.right != null)
                queue.add(new Pair<>(h.right, depth));

        }
        return depth;

    }
}