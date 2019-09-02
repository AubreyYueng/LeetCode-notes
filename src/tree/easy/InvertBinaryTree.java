package tree.easy;

import org.junit.Test;
import tree.TreeNode;
import tree.medium.BinaryTreeLevelOrderTraversal;

import java.util.Collection;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static tree.TreeNode.genTree;

/**
 * Created by Yiyun On 2019/7/28 05:18
 *
 * 226. Invert Binary Tree
 *
 * Example:
 * Input:
 *       4
 *     /   \
 *    2     7
 *   / \   / \
 *  1   3 6   9
 *
 * Output:
 *       4
 *     /   \
 *    7     2
 *   / \   / \
 *  9   6 3   1
 * Trivia:
 * This problem was inspired by this original tweet by Max Howell:
 *
 * Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a
 * whiteboard so f*** off.
 */
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;

        TreeNode tmp = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = tmp;
        return root;
    }

    @Test
    public void case1() {
        TreeNode ans = invertTree(genTree(4,2,7,1,3,6,9));
        assertEquals(
                asList(4,7,2,9,6,3,1),
                new BinaryTreeLevelOrderTraversal().levelOrder(ans).stream().flatMap(Collection::stream).collect(toList())
        );
    }

}
