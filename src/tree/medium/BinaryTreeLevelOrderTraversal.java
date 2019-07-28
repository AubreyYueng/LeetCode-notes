package tree.medium;

import org.junit.Test;
import tree.TreeNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static tree.TreeNode.genTree;

/**
 * Created by Yiyun On 2019/7/27 21:34
 *
 * 102. Binary Tree Level Order Traversal
 *
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *    3
 *   / \
 *  9  20
 *    /  \
 *   15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return Collections.emptyList();

        List<TreeNode> currentLevel = Collections.singletonList(root);
        List<List<Integer>> res = new LinkedList<>();
        res.add(Collections.singletonList(root.val));

        while (!currentLevel.isEmpty()) {
            List<TreeNode> nextLevel = new LinkedList<>();
            List<Integer> subArr = new LinkedList<>();
            for (TreeNode treeNode : currentLevel) {
                if (treeNode.left != null) {
                    nextLevel.add(treeNode.left);
                    subArr.add(treeNode.left.val);
                }
                if (treeNode.right != null) {
                    nextLevel.add(treeNode.right);
                    subArr.add(treeNode.right.val);
                }
            }
            if (!subArr.isEmpty()) res.add(subArr);
            currentLevel = nextLevel;
        }

        return res;
    }

    @Test
    public void case1() {
        assertEquals("[[3], [9, 20], [15, 7]]", levelOrder(genTree(3,9,20,null,null,15,7)).toString());
    }

    @Test
    public void case2() {
        assertEquals("[[1], [2, 3], [4, 5]]", levelOrder(genTree(1,2,3,4,null,null,5)).toString());
    }

}
