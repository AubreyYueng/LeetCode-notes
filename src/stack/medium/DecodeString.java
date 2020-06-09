package stack.medium;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/6/9 01:44
 *
 * 394. Decode String
 */
public class DecodeString {

    public String decodeString(String s) {
        LinkedList<String> stack = new LinkedList<>();
        int len = s.length();

        for (int i = 0; i < len; i++) {
            Character c = s.charAt(i);
            if (c == ']') {
                stack.push(decodeSubStr(stack));
            } else
                stack.push(String.valueOf(c));
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.insert(0, stack.pop());
        }
        return res.toString();
    }

    private boolean digit(String s) {
        if (s == null)
            return false;
        Character c = s.charAt(0);
        return c >= '0' && c <= '9';
    }

    // when encounter ]
    private String decodeSubStr(LinkedList<String> stack) {
        StringBuilder sub = new StringBuilder();
        while (!stack.peek().equals("[")) {
            sub.insert(0, stack.pop());
        }

        stack.pop();    // pop "["

        StringBuilder timesStr = new StringBuilder();
        while (digit(stack.peek())) {
            timesStr.insert(0, stack.pop());
        }

        int times = Integer.parseInt(timesStr.toString());
        String subStr = sub.toString();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < times; i++) {
            res.append(subStr);
        }
        return res.toString();
    }

    @Test
    public void case1() {
        assertEquals("aaabcbc", decodeString("3[a]2[bc]"));
    }

    @Test
    public void case2() {
        assertEquals("accaccacc", decodeString("3[a2[c]]"));
    }

    @Test
    public void case3() {
        assertEquals("abcabccdcdcdef", decodeString("2[abc]3[cd]ef"));
    }

    @Test
    public void case4() {
        assertEquals("abccdcdcdxyz", decodeString("abc3[cd]xyz"));
    }
}
