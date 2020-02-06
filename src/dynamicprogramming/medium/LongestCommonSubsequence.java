package dynamicprogramming.medium;

/**
 * Created by Yiyun On 2020/2/6 15:31
 *
 * 1143. Longest Common Subsequence
 * https://leetcode.com/problems/longest-common-subsequence/submissions/
 *
 */
public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        // f(i, j) = max(f(i, j-1), f(i-1, j)); if(text1[i] != text2[j])
        //         = f(i-1, j-1) + 1;           if(text1[i] == text2[j])
        // think about the graph similar to a matrix

        int col = text1.length();
        int row = text2.length();
        int[] res = new int[col+1];
        for (int r = 1; r <= row; r++) {
            char t2 = text2.charAt(r-1);
            int[] curr = new int[col+1];
            for (int c = 1; c <= col; c++) {
                int t1 = text1.charAt(c-1);
                if (t1 != t2)
                    curr[c] = Math.max(curr[c-1], res[c]);
                else
                    curr[c] = res[c-1]+1;
            }

            res = curr;
        }

        return res[col];
    }

}
