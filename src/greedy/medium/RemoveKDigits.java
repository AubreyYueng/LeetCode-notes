package greedy.medium;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/2 19:50
 *
 * 402. Remove K Digits
 */
public class RemoveKDigits {

    public String removeKdigits(String num, int k) {
        // for A = 1axxx, B = 1bxxx, if the digits a > b, then A > B
        LinkedList<Character> stack = new LinkedList<>();

        for (Character c : num.toCharArray()) {
            while (k > 0 && !stack.isEmpty() && stack.peek() > c) {
                k--;
                stack.pop();
            }
            stack.push(c);
        }

        // This case is easily being ignored: k > number of removed chars in the stack
        while (k-- > 0) {
            stack.pop();
        }

        if (stack.isEmpty())
            return "0";

        // Be careful: could be multiple 0's at the front
        StringBuilder sb= new StringBuilder();
        boolean start = false;
        while (!stack.isEmpty()) {
            Character c = stack.removeLast();
            if (!start) {
                if (c == '0')
                    continue;
                start = true;
            }
            sb.append(c);
        }

        String res = sb.toString();
        return res.isEmpty() ? "0" : res;
    }

    @Test
    public void case1() {
        assertEquals("200", removeKdigits("10200", 1));
    }

}
