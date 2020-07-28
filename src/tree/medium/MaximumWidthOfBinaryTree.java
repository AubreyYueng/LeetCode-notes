package tree.medium;

import javafx.util.Pair;
import org.junit.Test;
import tree.TreeNode;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static tree.TreeNode.genTree;

/**
 * Created by Yiyun On 2020/3/3 14:35
 *
 * 662. Maximum Width of Binary Tree
 * https://leetcode.com/problems/maximum-width-of-binary-tree/
 */
public class MaximumWidthOfBinaryTree {

    // BFS
    public int widthOfBinaryTree_review20200727(TreeNode root) {
        return new Helper_review20200727(root).max;
    }

    private static class Helper_review20200727 {
        int currLevel;
        int currLPos;
        int currRPos;
        int max;

        LinkedList<NodeInfo> queue = new LinkedList<>();

        public Helper_review20200727(TreeNode root) {
            bfs(root);
        }

        private void bfs(TreeNode root) {
            if (root == null) return;
            queue.offer(new NodeInfo(root, 0, 0));

            while (!queue.isEmpty()) {
                NodeInfo curr = queue.pop();

                // start of next level
                if (curr.level != currLevel) {
                    currLPos = curr.pos;
                    currLevel = curr.level;
                }
                currRPos = curr.pos;

                TreeNode node = curr.node;
                int nextLevel = currLevel+1;
                if (node.left != null) queue.offer(new NodeInfo(node.left, nextLevel, curr.pos*2));
                if (node.right != null) queue.offer(new NodeInfo(node.right, nextLevel, curr.pos*2+1));

                max = Math.max(max, currRPos-currLPos+1);
            }
        }
    }

    private static class NodeInfo {
        TreeNode node;
        int level;
        int pos;

        NodeInfo(TreeNode node, int level, int pos) {
            this.node = node;
            this.level = level;
            this.pos = pos;
        }
    }

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

    @Test
    public void case1() {
        TreeNode root = genTree(1, 3, 2, 5, 3, null, 9);
        assertEquals(4, widthOfBinaryTree_review20200727(root));
    }

}
