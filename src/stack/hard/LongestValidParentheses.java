package stack.hard;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/6/9 23:52
 *
 * 32. Longest Valid Parentheses
 */
public class LongestValidParentheses {

    // Approach 1: Stack
    // I think it's similar to monotonic stack from ) to (.
    public int longestValidParentheses(String s) {
        int res = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        // because all items could be popped while processing is not finished,
        // thus we need to subtract the very beginning
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (c == ')' && stack.peek()!=-1 && s.charAt(stack.peek()) == '(') {
                stack.pop();
                res = Math.max(res, i-stack.peek());
            } else {
                stack.push(i);
            }
        }

        return res;
    }

    @Test
    public void case1() {
        assertEquals(2, longestValidParentheses("(()"));
    }

    @Test
    public void case2() {
        assertEquals(4, longestValidParentheses(")()())"));
    }

}
