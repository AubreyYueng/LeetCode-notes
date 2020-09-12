package dynamicprogramming.medium;

import java.util.Arrays;

/**
 * Created by Yiyun On 2020/2/6 17:02
 *
 * 516. Longest Palindromic Subsequence
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 */
public class LongestPalindromicSubsequence {

    // this dp is looped by distance, thus we can do a bottom-up dp.
    // and the state dp[i, j] does not refer to the palindrome length starts at [i] and ends at [j],
    // BUT the longest palindrome length between [i] and [j].
    // For bottom-up, we can do the analysis by distance=0,1,2... and each of which has 2 cases: eq/neq.
    public int longestPalindrome_review20200912(String s) {
        if (s == null) return 0;
        int n = s.length();
        if (n <= 1) return n;

        // dp[i] for len of longest palindrome between [i] and [i+d]
        // d=0,1,2... thus dp[] is updated accordingly.
        int[] prevDp = new int[n];
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int dist = 1; dist < n; dist++) {
            System.out.println("dist: " + (dist-1) + ", dp: " + Arrays.toString(dp));

            int[] dpOfCurrDist = new int[n];
            for (int i = 0; i < n - dist; i++) {
                char chI = s.charAt(i);
                char chJ = s.charAt(i + dist);
                if (chI == chJ)
                    dpOfCurrDist[i] = prevDp[i+1] + 2;
                else
                    dpOfCurrDist[i] = Math.max(dp[i], dp[i+1]);
            }
            prevDp = dp;
            dp = dpOfCurrDist;
        }

        return Math.max(dp[0], dp[1]);
    }

    public int longestPalindromeSubseq(String s) {
        // f(i, j): longest len between palindrome subseq from index i to j
        // f(i, j) = f(i+1, j-1) + 2;           if (ch_i == ch_j)
        //         = Max(f(i+1, j), f(i, j-1)); if (ch_i != ch_j)
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
