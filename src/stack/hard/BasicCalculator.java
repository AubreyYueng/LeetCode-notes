package stack.hard;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/17 18:54
 *
 * 224. Basic Calculator
 */
public class BasicCalculator {

    // may contain ()+-, empty space, and digits.
    public int calculate(String s) {
        if (s == null || s.isEmpty())
            return 0;
        return new Helper(s).res;
    }

    private static class Helper {
        String s;
        LinkedList<Object> stack = new LinkedList<>();  // could only be int or String
        int res = 0;

        Helper(String s) {
            this.s = s;
            processString();
        }
        
        private void processString() {
            int n = s.length();
            int i = 0;

            while (i < n) {
                char ch = s.charAt(i);

                // when encounter digit
                int num;
                if ((num = digit(ch)) >= 0) {
                    int digit;
                    int j = i+1;
                    while (j < n && (digit=digit(s.charAt(j))) >= 0) {
                        num=num*10+digit;
                        j++;
                    }

                    stack.push(sign(num));
                    i = j;
                    continue;
                }

                // when encounter non-digit
                num = 0;
                if (ch == ')') {    // sum over all numbers inside parentheses
                    Object val = stack.pop();
                    while (!(val instanceof String)) {  // add to num until popped val is a String(must be "(")
                        num += (int)val;
                        val = stack.pop();
                    }
                    stack.push(sign(num));
                } else if (ch != ' '){
                    stack.push(String.valueOf(ch));
                }

                i++;
            }

            getResult();
        }

        // return digit value if digit, or -1
        private int digit(char ch) {
            if (Character.isDigit(ch))
                return ch - '0';
            return -1;
        }

        // query sign String in stack and mount at num.
        private int sign(int num) {
            if (!stack.isEmpty()) {     // query the sign of num
                String sign = (String) stack.pop();
                if (sign.equals("-")) num *= -1;
                else if (!sign.equals("+")) stack.push(sign); // neither + nor -, push back to stack
            }
            return num;
        }

        private void getResult() {
            while (!stack.isEmpty()) {
                res += (int)stack.pop();
            }
        }
    }

    @Test
    public void case1() {
        assertEquals(2, calculate("1 + 1"));
    }

    @Test
    public void case2() {
        assertEquals(3, calculate(" 2-1 + 2 "));
    }

    @Test
    public void case3() {
        assertEquals(23, calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    @Test
    public void case4() {
        assertEquals(0, calculate("0"));
    }

    @Test
    public void case5() {
        assertEquals(30, calculate("  30"));
    }

}
