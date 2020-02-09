package dynamicprogramming.medium;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/2/8 20:15
 * 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return "";
        // f(i, j): if palindromic from index i to j
        // f(i, j) = true if i == j
        //         = f(i+1, j-1) && (chi==chj || distance == 1)
        int len = s.length();
        int st = 0;
        int ed = 0;

        boolean[][] dp = new boolean[len][len];
        for (int d = 0; d < len; d++) dp[d][d] = true;

        for (int d = 1; d < len; d++) {
            for (int i = 0; i < len-d; i++) {
                int j = i+d;
                dp[i][j] = s.charAt(i) == s.charAt(j) && (dp[i+1][j-1] || d == 1);  // must be careful about the d==1
                if (dp[i][j]) {
                    st = i;
                    ed = j;
                }
            }
        }

        return s.substring(st, ed+1);   // be careful about the +1 here
    }

    @Test
    public void case1() {
        assertEquals("aba", longestPalindrome("babad"));
    }

    @Test
    public void case2() {
        assertEquals("bb", longestPalindrome("cbbd"));
    }
}
