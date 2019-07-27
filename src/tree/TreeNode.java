package tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yiyun On 2019/7/27 21:22
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode (int x) {
        val = x;
    }

    public static TreeNode genTree(Integer... values) {
        if (values == null || values.length == 0)
            return null;

        TreeNode peek = new TreeNode(values[0]);

        List<TreeNode> currentLevel = Collections.singletonList(peek);
        for (int i = 1; i < values.length; ) {
            List<TreeNode> nextLevel = new LinkedList<>();
            for (TreeNode treeNode : currentLevel) {
                Integer leftVal = values[i];
                Integer rightVal = i+1 < values.length ? values[i+1] : null;
                TreeNode left = leftVal == null ? null : new TreeNode(leftVal);
                TreeNode right = rightVal == null ? null : new TreeNode(rightVal);

                treeNode.left = left;
                treeNode.right = right;

                nextLevel.add(left);
                nextLevel.add(right);

                i+=2;
            }
            currentLevel = nextLevel;
        }

        return peek;
    }
}
