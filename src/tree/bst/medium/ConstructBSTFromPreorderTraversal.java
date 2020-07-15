package tree.bst.medium;

import tree.TreeNode;

/**
 * Created by Yiyun On 2020/7/14 21:24
 *
 * 1008. Construct Binary Search Tree from Preorder Traversal
 */
public class ConstructBSTFromPreorderTraversal {

    // Similar to LC105.
    // But we replace the in-order(needs O(NlogN) of sorting) with upper/lower limit of each element since it is a BST
    // The following codes are basically learned from LC solution
    public TreeNode bstFromPreorder(int[] preorder) {
        Helper h = new Helper(preorder);
        return h.buildBST(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static class Helper {
        int i;        // index of current preorder value
        int[] preorder;
        int n;

        private Helper(int[] preorder) {
            this.preorder = preorder;
            this.n = preorder.length;
            this.i = 0;
        }

        private TreeNode buildBST(int lower, int upper) {
            if (i == n) return null;

            int val = preorder[i];
            if (val < lower || val > upper) return null;
            i++;

            TreeNode currRoot = new TreeNode(val);
            currRoot.left = buildBST(lower, val);
            currRoot.right = buildBST(val, upper);
            return currRoot;
        }
    }

}
