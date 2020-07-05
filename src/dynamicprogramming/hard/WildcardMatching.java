package dynamicprogramming.hard;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yiyun On 2020/7/4 16:30
 *
 * 44. Wildcard Matching
 */
public class WildcardMatching {

    /**
     state: boolean[p_len+1][s_len+1]. [i][i]: match s[:i]p[:j]
     func:
        1. p[i]='?' or p[i]=s[j]: [i][j] = [i-1][j-1]
        2. p[i]='*' [i][j] = [i-1][j] || [i-1][j-1], then set [i][j-1->len-1]=[i][j]
        3. otherwise, false
     init: [0][0]=true; if p[i]='*', [i][0]=[i-1][0]
     */
    public boolean isMatch(String s, String p) {
        int p_len = p.length();
        int s_len = s.length();
        boolean[] prev = new boolean[s_len+1];
        prev[0] = true;

        for (int i = 1; i < p_len+1; i++) {
            char p_i = p.charAt(i-1);
            boolean[] curr = new boolean[s_len+1];
            if (p_i == '*') curr[0]=prev[0];

            for (int j = 1; j < s_len+1; j++) {
                char s_j = s.charAt(j-1);
                if (p_i == '?' || p_i == s_j)
                    curr[j] = prev[j-1];

                else if (p_i == '*') {
                    curr[j] = prev[j] || prev[j-1];
                    if (curr[j]) {
                        for (int k = j+1; k < s_len+1; k++) {
                            curr[k] = true;
                        }
                        break;
                    }
                }
            }
            prev = curr;
        }
        return prev[s_len];
    }

    @Test
    public void case1() {
        assertFalse(isMatch("aa", "a"));
    }

    @Test
    public void case2() {
        assertTrue(isMatch("aa", "*"));
    }

    @Test
    public void case3() {
        assertFalse(isMatch("cb", "?a"));
    }

    @Test
    public void case4() {
        assertTrue(isMatch("adceb", "*a*b"));
    }

    @Test
    public void case5() {
        assertFalse(isMatch("aab", "c*a*b*"));
    }

}
