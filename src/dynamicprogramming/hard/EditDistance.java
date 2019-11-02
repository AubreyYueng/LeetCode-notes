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
        assertEquals(3, minDistance("horse", "ros"));
    }

    @Test
    public void case2() {
        assertEquals(5, minDistance("intention", "execution"));
    }
}
