package string.easy;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yiyun On 2019/11/21 22:19
 *
 * 20. Valid Parentheses
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 * Input: "()"
 * Output: true
 *
 * Example 2:
 * Input: "()[]{}"
 * Output: true
 *
 * Example 3:
 * Input: "(]"
 * Output: false
 *
 * Example 4:
 * Input: "([)]"
 * Output: false
 *
 * Example 5:
 * Input: "{[]}"
 * Output: true
 */
public class ValidParentheses {
    
    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character curr = s.charAt(i);
            if (map.containsKey(curr))
                stack.push(curr);
            else {
                Character prev = stack.isEmpty() ? 'x' : stack.pop();
                if (!curr.equals(map.get(prev)))
                    return false;
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void case1() {
        assertTrue(isValid("()"));
    }

    @Test
    public void case2() {
        assertTrue(isValid("()[]{}"));
    }

    @Test
    public void case3() {
        assertFalse(isValid("(]"));
    }

    @Test
    public void case4() {
        assertFalse(isValid("([)]"));
    }

    @Test
    public void case5() {
        assertTrue(isValid("{[]}"));
    }

    @Test
    public void case6() {
        assertFalse(isValid("]"));
    }
}
