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
 * Created by Yiyun On 2019/7/27 21:29
 *
 * 94. Binary Tree Inorder Traversal
 *
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * Example:
 * Input: [1,null,2,3]
 *   1
 *   \
 *    2
 *   /
 *  3
 * Output: [1,3,2]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 * TODO: ring a bell about the definition of "inorder traversal"
 * TODO: Approach 3: Morris Traversal
 */
public class BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }

        return res;
    }

    @Test
    public void case1() {
        assertEquals(asList(1,3,2).toString(), inorderTraversal(genTree(1,null,2,3)).toString());
    }

}
