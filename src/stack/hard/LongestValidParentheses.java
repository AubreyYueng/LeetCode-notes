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

    // Approach 3: without extra space(copied from solution)
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

    // Approach 2: DP (I think it's more complicated than Approach 2 using stack,
    // solving in stack even without seeing the solution...)
    // 1. state: dp[s_len], dp [i] represents the maxLen the valid subStr ends with s[i],
    // thus all dp[i] is 0 if s[i] is '('.  NOTE, it's NOT the maxLen of first N characters
    // 2. init: all 0
    // 3. func:
    // if s[i] = '(', dp[i] = 0
    // else:
    //    if s[i-1] = '(', dp[i] = dp[i-2]+2.
    //    else if s[i-1-dp[i-1]] = '(', dp[i] = dp[i-2-dp[i-1]] + dp[i-1] + 2
    // 4. return:  max_len compared during iteration
    public int longestValidParentheses_dp(String s) {
        s = "))"+s;
        int[] dp = new int[s.length()];
        int res = 0;
        for (int i = 2; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                continue;

            if (s.charAt(i-1)=='(') {
                dp[i] = dp[i-2]+2;
            } else if (s.charAt(i-1-dp[i-1]) == '(') {
                dp[i] = dp[i - 1] + dp[i-2-dp[i-1]] + 2;
            }

            res = Math.max(res, dp[i]);
        }

        return res;
    }

    // Approach 1: Stack
    // I think it's similar to monotonic stack from ) to (.
    public int longestValidParentheses_stack(String s) {
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
