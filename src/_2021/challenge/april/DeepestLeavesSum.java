package _2021.challenge.april;

import tree.TreeNode;

import java.util.LinkedList;

/**
 * Created by Yiyun On 2021/4/11 16:01
 *
 * 1302. Deepest Leaves Sum
 */
public class DeepestLeavesSum {

    public int deepestLeavesSum(TreeNode root) {
        LinkedList<Info> q = new LinkedList<>();
        q.offer(new Info(root, 0));
        int res = 0;

        int currLevel = 0;
        while (!q.isEmpty()) {
            res = 0;
            while (!q.isEmpty() && q.peek().level == currLevel) {
                TreeNode node = q.poll().node;
                res += node.val;
                if (node.left != null)
                    q.offer(new Info(node.left, currLevel+1));
                if (node.right != null)
                    q.offer(new Info(node.right, currLevel+1));
            }

            if (!q.isEmpty())
                currLevel = q.peek().level;
        }

        return res;
    }

    private static class Info {
        TreeNode node;
        int level;

        private Info(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

}
