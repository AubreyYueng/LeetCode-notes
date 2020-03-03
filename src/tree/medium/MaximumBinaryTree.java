package tree.medium;

import tree.TreeNode;

import java.util.Stack;

/**
 * Created by Yiyun On 2020/3/3 15:25
 *
 * 654. Maximum Binary Tree
 * https://leetcode.com/problems/maximum-binary-tree/
 */
public class MaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        // It's actually a Cartesian Tree.
        // The description is like shit, explanation:
        // given [3, 2, 1, 6, 0, 5], then 6 is root, [3, 2, 1] form the left sub maximum tree, [0, 5] form the sub maximum tree
        if (nums == null || nums.length == 0)
            return null;
        // Don't look at the solution which requires O(n^2) time complexity
        // O(N) solution: https://leetcode.com/problems/maximum-binary-tree/discuss/106146/C++-O(N)-solution
        // We use a stack to keep some (not all) tree nodes and ensure a decreasing order
        Stack<TreeNode> stack = new Stack<>();
        for(int n: nums) {
            TreeNode node = new TreeNode(n);
            while(!stack.isEmpty() && stack.peek().val < n)
                node.left = stack.pop();

            if (!stack.isEmpty())
                stack.peek().right = node;

            stack.push(node);
        }

        // if we use LinkedList, then we don't need to pop one by one now.
        TreeNode ans = stack.pop();
        while (!stack.isEmpty())
            ans = stack.pop();

        return ans;
    }

}
