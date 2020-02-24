package tree.medium;

import tree.TreeNode;

import java.util.*;

/**
 * Created by Yiyun On 2020/2/24 17:07
 *
 * 199. Binary Tree Right Side View
 * https://leetcode.com/problems/binary-tree-right-side-view/
 */
public class BinaryTreeRightSideView {

    // Also consider the BFS next time(using queue)
    public List<Integer> rightSideView(TreeNode root) {
        int max_depth = -1;
        Map<Integer, Integer> depthVal = new HashMap<>();

        Stack<TreeNode> candidates = new Stack<>();
        Stack<Integer> relatedDepth = new Stack<>();
        candidates.add(root);
        relatedDepth.add(0);

        while(!candidates.isEmpty()) {
            TreeNode candidate = candidates.pop();
            int depth = relatedDepth.pop();
            if (candidate == null) continue;

            depthVal.putIfAbsent(depth, candidate.val);

            max_depth = Math.max(depth, max_depth);
            // Be careful about the order
            candidates.add(candidate.left);
            candidates.add(candidate.right);
            relatedDepth.add(depth+1);
            relatedDepth.add(depth+1);
        }

        List<Integer> ans = new LinkedList<>();
        for(int i = 0; i <= max_depth; i++) {
            ans.add(depthVal.get(i));
        }

        return ans;
    }

}
