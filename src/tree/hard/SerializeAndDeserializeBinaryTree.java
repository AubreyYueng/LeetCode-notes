package tree.hard;

import org.junit.Test;
import tree.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/6/3 21:29
 *
 * 297. Serialize and Deserialize Binary Tree
 */
public class SerializeAndDeserializeBinaryTree {
    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.deserialize(codec.serialize(root));

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> res = new LinkedList<>();
        dfs(root, res);
        return String.join(",", res);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<String> src = new LinkedList<>(Arrays.asList(data.split(",")));
        return des(src);
    }

    private void dfs(TreeNode head, List<String> res) {
        if (head == null) {
            res.add(null);
            return;
        }
        res.add(String.valueOf(head.val));
        dfs(head.left, res);
        dfs(head.right, res);
    }

    private TreeNode des(LinkedList<String> src) {
        if (src.isEmpty())
            return null;
        String str = src.pop();
        if ("null".equals(str))
            return null;

        TreeNode cur = new TreeNode(Integer.valueOf(str));
        cur.left = des(src);
        cur.right = des(src);
        return cur;
    }

    @Test
    public void case1_ser() {
        assertEquals("1,2,null,null,3,4,null,null,5,null,null", serialize(TreeNode.genTree(1,2,3,null,null,4,5)));
    }

    @Test
    public void case1() {
        String str = "1,2,null,null,3,4,null,null,5,null,null";
        assertEquals(str, serialize(deserialize(str)));
    }
}
