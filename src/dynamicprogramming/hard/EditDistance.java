package dynamicprogramming.hard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/10/7 20:06
 *
 * 72. Edit Distance
 *
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 * You have the following 3 operations permitted on a word:
 * Insert a character
 * Delete a character
 * Replace a character
 *
 * Example 1:
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 *
 * Example 2:
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 *
 * TODO: suppose D[i][j]: distance between word1[i] and word2[j]
 * TODO: if word1[i]==word2[j], just add 1 more character
 * TODO:    D[i][j] = MIN(D[i-1][j]+1, D[i][j-1]+1, D[i-1][j-1])
 * TODO: if word1[i]!=word2[j], replace the last character
 * TODO:    D[i][j] = MIN(D[i-1][j]+1, D[i][j-1]+1, D[i-1][j-1]+1)
 * TODO: base case would be: D[i][0] = i, D[0][j] = j
 */
public class EditDistance {

    // bottom-up DP
    // state: [s_len+1][t_len+1]: [i][j] is minDist of s[:i] => t[:j]
    // init: [0][j] = j, [i][0] = i
    // func: [i][j] is the MIN over the following
    //    1) delete:  [i-1][j] + 1
    //    2) insert:  [i][j-1] + 1
    //    3) replace: [i-1][j-1] + (s[i]==s[j] ? 0 : 1)
    // ret: [s_len][t_len]
    // optimize space: According to the func, only the previous status matters. Thus we use 1-d DP
    public int minDistance_review20200704(String word1, String word2) {
        int sLen= word1.length();
        int tLen = word2.length();
        int[] prev = new int[tLen+1];
        int[] curr = new int[tLen+1];

        // init [0][j]
        for (int i = 0; i <= tLen; i++) prev[i] = i;

        for (int i = 1; i <= sLen; i++) {
            // init [i][0]
            curr[0] = i;
            char chS = word1.charAt(i-1);

            for (int j = 1; j <= tLen; j++) {
                int delete = prev[j] + 1;
                int insert = curr[j-1] + 1;
                int replace = prev[j-1] + (chS==word2.charAt(j-1) ? 0 : 1);
                curr[j] = Math.min(delete, Math.min(insert, replace));
            }

            prev = curr;
            curr = new int[tLen+1];
        }
        return prev[tLen];
    }



    public int minDistance_review20200208(String word1, String word2) {
        // dp(i, j): min distance to make first i chars of word1 to become first j chars of word2
        // dp(i, j) = min(
        //              dp(i-1,j-1);  if word1[i]==word2[j], then i++,j++
        //              dp(i-1,j-1)+1; replace, then i++,j++
        //              dp(i-1,j-1)+1; delete, then i++
        //              dp(i-1,j-1)+1; insert, then j++
        //            )
        // the index operation is too complex above, we might switch our thoughts to:
        // dp(i, j) = min(
        //                  dp(i-1,j)+1,                    need to delete ch_i
        //                  dp(i,j-1)+1,                    need to insert a ch_j
        //                  dp(i-1,j-1)+(ch_i==ch_j?0:1)    replace if equal
        //               )
        int row = word1.length();
        int col = word2.length();
        int[] dp = new int[col+1];
        for(int j = 0; j <= col; j++) dp[j]=j;  // Be careful about the initilization

        for (int i = 1; i <= row; i++) {
            int[] curr = new int[col+1];
            curr[0] = i;                        // Be careful about the initilization
            char chi = word1.charAt(i-1);
            for (int j = 1; j <= col; j++) {
                char chj = word2.charAt(j-1);
                curr[j] = Math.min(
                        Math.min(
                                dp[j]+1,
                                curr[j-1]+1
                        ),
                        dp[j-1]+(chi==chj?0:1)
                );
            }
            dp = curr;
        }
        return dp[col];
    }

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int d[][] = new int[m+1][n+1];
        for (int i = 0; i <= m; i++) {
            d[i][0] = i;
        }
        for (int i = 0; i <= n; i++) {
            d[0][i] = i;
        }

        for (int i = 1; i <= m; i++) {       // TODO: NOTICE here starts from 1, and '<=' m
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1))
                    d[i][j] = d[i-1][j-1];
                else {
                    d[i][j] = 1 + Math.min(Math.min(d[i][j-1], d[i-1][j]), d[i-1][j-1]);
                }
            }
        }

        return d[m][n];
    }

    @Test
    public void case1() {
        assertEquals(3, minDistance_review20200704("horse", "ros"));
    }

    @Test
    public void case2() {
        assertEquals(5, minDistance_review20200704("intention", "execution"));
    }
}
