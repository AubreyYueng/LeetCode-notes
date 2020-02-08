package dynamicprogramming.hard;

/**
 * Created by Yiyun On 2020/2/7 23:08
 *
 * 1092. Shortest Common Supersequence
 * https://leetcode.com/problems/shortest-common-supersequence/
 */
public class ShortestCommonSupersequence {

    public String shortestCommonSupersequence(String str1, String str2) {
        // first find LCS
        // then build the string reversely or we don't know how to figure out the right path from top left to bottom right
        int row = str1.length();
        int col = str2.length();
        int[][] dp = new int[row+1][col+1];
        for (int i = 1; i <= row; i++) {
            char ch1 = str1.charAt(i-1);
            for (int j = 1; j <= col; j++) {
                char ch2 = str2.charAt(j-1);
                if (ch1 == ch2)
                    dp[i][j] = dp[i-1][j-1]+1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = row - 1, j = col - 1;       // here we use i,j for actual index
        while (i >= 0 || j >= 0) {
            // true iff one is true and the other is false
            // same meaning as if (i < 0 && j >= 0 || j < 0 && i >= 0)
            if (i < 0 ^ j < 0) { // only one string reaches left end.
                char c = i < 0 ? str2.charAt(j--) : str1.charAt(i--); // remaining chars in the other string.
                sb.append(c);
            }else if (str1.charAt(i) == str2.charAt(j)) { // common char in LCS.
                sb.append(str1.charAt(i)); // append the char of either s1 or s2.
                --i; --j;
            }else { // the char is not in LCS.
                char c = dp[i][j + 1] > dp[i + 1][j] ? str1.charAt(i--) : str2.charAt(j--); // the char corresponding to larger dp value.
                sb.append(c);
            }
        }
        return sb.reverse().toString();

    }

}
