package tree.medium;

import org.junit.Test;
import tree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static tree.TreeNode.genTree;

/**
 * Created by Yiyun On 2019/7/28 04:48
 *
 * 144. Binary Tree Preorder Traversal
 *
 * Given a binary tree, return the preorder traversal of its nodes' values.
 *
 * Example:
 * Input: [1,null,2,3]
 *     1
 *      \
 *      2
 *     /
 *    3
 * Output: [1,2,3]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreePreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                res.add(curr.val);
                curr = curr.left;
            }

            curr = stack.pop().right;
        }

        return res;
    }

    @Test
    public void case1() {
        assertEquals(asList(1,2,3).toString(), preorderTraversal(genTree(1,null,2,3)).toString());
    }

}
