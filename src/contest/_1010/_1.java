package contest._1010;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/10/10 23:08
 *
 * 5535. Maximum Nesting Depth of the Parentheses
 */
public class _1 {

    public int maxDepth(String s) {
        int res = 0;
        int l = 0;
        int r = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                l++;
                r = 0;
            }
            else if (c == ')') {
                r++;
            }

            if (l >= r) {
                res = Math.max(res, r);
            }
        }

        return res;
    }

    @Test
    public void case1() {
        assertEquals(3, maxDepth("(1+(2*3)+((8)/4))+1"));
    }

    @Test
    public void case2() {
        assertEquals(3, maxDepth("(1)+((2))+(((3)))"));
    }

}
