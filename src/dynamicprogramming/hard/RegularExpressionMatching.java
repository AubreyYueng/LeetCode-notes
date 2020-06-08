package dynamicprogramming.hard;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yiyun On 2020/6/7 13:19
 *
 * 10. Regular Expression Matching
 */
public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        // state
        boolean[][] dp = new boolean[sLen+1][pLen+1];

        // init
        dp[0][0] = true;
        for (int i = 2; i <= pLen; i++) {
            if (p.charAt(i-1) == '*')
                dp[0][i] = dp[0][i-2];
        }

        // func
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                char curS = s.charAt(i-1);
                char curP = p.charAt(j-1);
                if (curP == '.' || curS == curP)
                    dp[i][j] = dp[i-1][j-1];
                else if (curP == '*') {
                    char prevP = p.charAt(j-2);
                    // general case, same as init
                    dp[i][j] = dp[i][j-2];

                    if (!dp[i][j]) {
                        if (curS == prevP || prevP == '.')
                            dp[i][j] = dp[i-1][j];
                    }
                }
            }
        }
        return dp[sLen][pLen];
    }

    @Test
    public void case1() {
        assertFalse(isMatch("aa", "a"));
    }

    @Test
    public void case2() {
        assertTrue(isMatch("aa", "a*"));
    }

    @Test
    public void case3() {
        assertTrue(isMatch("ab", ".*"));
    }

    @Test
    public void case4() {
        assertTrue(isMatch("aab", "c*a*b*"));
    }

    @Test
    public void case5() {
        assertFalse(isMatch("mississippi", "mis*is*p*."));
    }

    @Test
    public void case6() {
        assertTrue(isMatch("aaa", ".*"));
    }
}
