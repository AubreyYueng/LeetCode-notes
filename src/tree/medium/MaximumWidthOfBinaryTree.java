package tree.medium;

import javafx.util.Pair;
import tree.TreeNode;

import java.util.LinkedList;

/**
 * Created by Yiyun On 2020/3/3 14:35
 *
 * 662. Maximum Width of Binary Tree
 * https://leetcode.com/problems/maximum-width-of-binary-tree/
 */
public class MaximumWidthOfBinaryTree {

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        // BFS
        // (node, depth, pos)
        LinkedList<Pair<TreeNode, Pair<Integer, Integer>>> queue = new LinkedList<>();
        int depth = 1;
        queue.add(new Pair<>(root, new Pair<>(depth, 0)));
        int ans = 0;
        int leftPos = 0;
        int rightPos = 0;
        // you don't know if next node have children, so you can't simply count in every level
        while (!queue.isEmpty()) {
            Pair<TreeNode, Pair<Integer, Integer>> curr = queue.poll();
            TreeNode n = curr.getKey();
            if (n == null)
                continue;

            int currDepth = curr.getValue().getKey();
            int currPos = curr.getValue().getValue();

            if (depth != currDepth) {
                depth = currDepth;
                ans = Math.max(ans, rightPos-leftPos+1);
                leftPos = currPos;
            }
            rightPos = currPos;

            queue.add(new Pair<>(n.left, new Pair<>(depth+1, currPos*2)));
            queue.add(new Pair<>(n.right, new Pair<>(depth+1, currPos*2+1)));
        }

        // if we want to return ans directly we need to update ans in every interation,
        // or update ans while depth != currDepth even popped node is null
        return Math.max(ans, rightPos-leftPos+1);
    }

}
