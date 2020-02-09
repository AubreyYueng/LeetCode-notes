package dynamicprogramming.hard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/2/8 19:20
 *
 * 115. Distinct Subsequences
 * https://leetcode.com/problems/distinct-subsequences/
 */
public class DistinctSubsequences {

    public int numDistinct(String s, String t) {
        // So basically it's the variation of Edit Distance with only delete operation
        // set i for index of s, and j for index of t
        // f(0, j) = 1; initilization
        // f(i, j) = (chi==chj ? f(i-1,j-1) : 0) + f(i-1, j)

        int row = s.length();
        int col = t.length();
        int[] dp = new int[col+1];
        dp[0] = 1;          // be careful about the initilization

        for (int i = 1; i <= row; i++) {
            int[] tmp = new int[col+1];
            tmp[0] = 1;     // be careful about the initilization
            char chi = s.charAt(i-1);
            for (int j = 1; j <= col; j++) {
                char chj = t.charAt(j-1);
                tmp[j] = (chi==chj?dp[j-1]:0) + dp[j];
            }
            dp = tmp;
        }

        return dp[col];
    }

    @Test
    public void case1() {
        assertEquals(3, numDistinct("rabbbit", "rabbit"));
    }

}
