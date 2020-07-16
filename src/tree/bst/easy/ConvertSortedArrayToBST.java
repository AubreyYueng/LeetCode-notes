package tree.bst.easy;

import tree.TreeNode;

/**
 * Created by Yiyun On 2020/7/14 21:07
 *
 * 108. Convert Sorted Array to Binary Search Tree
 */
public class ConvertSortedArrayToBST {

    // Convert to a height-balanced BST(depth of two subtrees never differs by more than 1)
    public TreeNode sortedArrayToBST(int[] nums) {
        return new Helper(nums).heightBalancedBST(0, nums.length-1);
    }

    private static class Helper {
        int[] nums;

        Helper(int[] nums) {
            this.nums = nums;
        }

        private TreeNode heightBalancedBST(int left, int right) {
            if (left > right) return null;
            // always choose left middle as root
            int mid = (left + right) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = heightBalancedBST(left, mid-1);
            root.right = heightBalancedBST(mid + 1, right);
            return root;
        }
    }

}
