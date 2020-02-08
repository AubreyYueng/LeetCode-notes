package dynamicprogramming.medium;

/**
 * Created by Yiyun On 2020/2/6 17:02
 *
 * 516. Longest Palindromic Subsequence
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 */
public class LongestPalindromicSubsequence {

    public int longestPalindromeSubseq(String s) {
        // f(i, j): longest len between palindrome subseq from index i to j
        // f(i, j) = f(i+1, j-1) + 2;           if (ch_i == ch_j)
        //         = Max(f(i+1, k), f(i, j-1)); if (ch_i != ch_j)
        //         = 1;                         if (i == j)
        int len = s.length();
        int[][]dp = new int[len][len];
        for (int d = 0; d < len; d++) {
            dp[d][d] = 1;
        }

        for (int d = 1; d < len; d++) {     // d for distance
            for (int i = 0; i < len-d; i++) {
                int j = i+d;
                char chi = s.charAt(i);
                char chj = s.charAt(j);
                if (chi == chj)
                    dp[i][j] = dp[i+1][j-1]+2;
                else
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
            }
        }

        return dp[0][len-1];
    }

}
