package tree.hard;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Yiyun On 2019/7/28 04:50
 *
 * 145. Binary Tree Postorder Traversal
 *
 * Given a binary tree, return the postorder traversal of its nodes' values.
 *
 * Example:
 * Input: [1,null,2,3]
 *     1
 *      \
 *      2
 *     /
 *    3
 * Output: [3,2,1]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 * TODO: iterative & recursive solutions
 */
public class BinaryTreePostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.empty()){
            root = stack.pop();
            // NOTE always appending at the start, remember root element is the last element in the post order traversal
            list.addFirst(root.val);
            // NOTE LEFT IS PUSHED FIRST SO THAT RIGHT IS AT TOP OF THE STACK
            if(root.left != null) stack.push(root.left);
            if(root.right != null) stack.push(root.right);
        }
        return list;
    }

    public List<Integer> recursivelyPostorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        recursiveHelper(root,list);
        return list;
    }

    private void recursiveHelper(TreeNode root, List<Integer> list){
        if(root==null){
            return;
        }
        recursiveHelper(root.left,list);
        recursiveHelper(root.right,list);
        list.add(root.val);
    }



}
