package string.medium;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/16 23:37
 *
 * 227. Basic Calculator II
 */
public class BasicCalculatorII {

    // The following codes are mostly copied from LC top voted discussion
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        LinkedList<Integer> stack = new LinkedList<>();
        int num = 0;        // current number, reset to 0 when encounter non-digit
        char sign = '+';    // the closest sign to the current digit
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            boolean digit = Character.isDigit(ch);
            if (digit) {
                num = num * 10 + ch - '0';
            }
            if ((!digit && ' '!=ch) || i==n-1) {
                if(sign=='-'){
                    stack.push(-num);
                }
                if(sign=='+'){
                    stack.push(num);
                }
                if(sign=='*'){
                    stack.push(stack.pop()*num);
                }
                if(sign=='/'){
                    stack.push(stack.pop()/num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }
        int re = 0;
        for(int i:stack) re += i;
        return re;
    }

    @Test
    public void case1() {
        assertEquals(7, calculate("3+2*2"));
    }

    @Test
    public void case2() {
        assertEquals(1, calculate(" 3/2 "));
    }

    @Test
    public void case3() {
        assertEquals(5, calculate(" 3+5 / 2 "));
    }

}
