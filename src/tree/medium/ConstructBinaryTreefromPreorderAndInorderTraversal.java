package tree.medium;

import tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yiyun On 2019/7/27 21:32
 *
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 *
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *   3
 *  / \
 * 9  20
 *   /  \
 *  15   7
 *
 *  TODO pre: root,     2, 4, 7,     3, 5, 6, 8
 *                  |left (3 nodes)|right (4 nodes)|
 *  TODO in:     4,7, 2,        root,     5, 3, 8, 6
 *           |left (3 nodes)|          |right (4 nodes)|
 */
public class ConstructBinaryTreefromPreorderAndInorderTraversal {

    // TODO  Time: O(N)
    public TreeNode buildTree_review20200714(int[] preorder, int[] inorder) {
        Helper_review20200714 helper = new Helper_review20200714(preorder, inorder);
        return helper.buildRoot(0, inorder.length-1);
    }

    // Fact: The first element in pre-order list is a root, which splits the in-order list into left and right trees.
    private static class Helper_review20200714 {
        private int[] preorder;
        private int[] inorder;
        private int preIdx;
        private Map<Integer, Integer> idxMap = new HashMap<>();     // keep track of index of value in in-order list

        private Helper_review20200714(int[] preorder, int[] inorder) {
            this.preorder = preorder;
            this.inorder = inorder;
            for (int i = 0; i < this.inorder.length; i++) idxMap.put(inorder[i], i);
        }

        // The following codes are written based on LC solution
        // left and right are indices in in-order list, rootIdx is the current index in pre-order list
        private TreeNode buildRoot(int left, int right) {
            if (left > right)
                return null;

            int rootVal = preorder[preIdx++];
            int inIdx = idxMap.get(rootVal);
            TreeNode currRoot = new TreeNode(rootVal);

            currRoot.left = buildRoot(left, inIdx-1);
            currRoot.right = buildRoot(inIdx+1, right);
            return currRoot;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Helper helper = new Helper(preorder, inorder);
        return helper.dfs(0, preorder.length-1, 0, inorder.length-1);
    }

    private static class Helper {
        private int[] preorder;
        private int[] inorder;
        Map<Integer, Integer> inPositionMap = new HashMap<>();

        Helper(int[] preorder, int[] inorder) {
            this.preorder = preorder;
            this.inorder = inorder;
            for (int i = 0; i < inorder.length; i++) {
                inPositionMap.put(inorder[i], i);
            }
        }

        private TreeNode dfs(int preLower, int preUpper, int inLower, int inUpper) {
            if (preLower > preUpper)
                return null;

            int currRootVal = preorder[preLower];
            TreeNode currRoot = new TreeNode(currRootVal);

            int inCurrRootPosition = inPositionMap.get(currRootVal);
            int leftNodesCnt = inCurrRootPosition - inLower;
            int leftPreUpper = preLower+leftNodesCnt;

            currRoot.left = dfs(preLower+1, leftPreUpper, inLower, inCurrRootPosition-1);
            currRoot.right = dfs(leftPreUpper+1, preUpper, inCurrRootPosition+1, inUpper);
            return currRoot;
        }

    }

}
