package _2021.pre_codes;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2021/7/6 05:19
 */
public class pre_6 {

    private static int gold[][]= { {1, 3, 1, 5},
            {2, 2, 4, 1},
            {5, 0, 2, 3},
            {0, 6, 1, 2} };

    int m = 4, n = 4;

    int getMaxGold(int gold[][],
                   int m, int n)
    {
        int C[][] = new int[m][n];

        // init: last column as itself
        for (int i = 0; i < m; i++) {
            C[i][n-1] = gold[i][n-1];
        }

        // from top to bottom: last column to first column
        for (int j = n-2; j >= 0; j--) {
            for (int i = 0; i < m; i++) {
                int rUp = (i==0) ? 0 : C[i-1][j+1];
                int r = C[i][j+1];
                int rDown = (i==m-1) ? 0 : C[i+1][j+1];
                C[i][j] = gold[i][j] + Math.max(Math.max(rUp, r), rDown);
            }
        }

        // find the max of first column: max(C[][0])
        int res = C[0][0];
        for (int i = 1; i < m; i++)
            res = Math.max(res, C[i][0]);

        return res;
    }

    int getMaxGold_1(int[][] gold,
                     int m, int n)
    {
        int[] C = new int[m];

        // init: last column as itself
        for (int i = 0; i < m; i++) {
            C[i] = gold[i][n-1];
        }

        // from top to bottom: last column to first column
        for (int j = n-2; j >= 0; j--) {
            int[] tmp = new int[m];
            for (int i = 0; i < m; i++) {
                int rUp = (i==0) ? 0 : C[i-1];
                int r = C[i];
                int rDown = (i==m-1) ? 0 : C[i+1];
                tmp[i] = gold[i][j] + Math.max(Math.max(rUp, r), rDown);
            }
            C = tmp;
        }

        // find the max of first column: max(C[][0])
        int res = C[0];
        for (int i = 1; i < m; i++)
            res = Math.max(res, C[i]);

        return res;
    }

    int getMaxGold_2(int gold[][], int m, int n) {
        int[][] cache = new int[m][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cache[i], -1);
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            res = Math.max(dfs_1(gold, i, 0, cache), res);
        }
        return res;
    }

    int dfs_1(int gold[][], int i, int j, int[][] cache) {
        if (i < 0 || i >= m || j < 0 || j >= n)
            return 0;
        if (j == n-1)
            return gold[i][j];
        if (cache[i][j] != -1)
            return cache[i][j];

        int res = gold[i][j] + Math.max(
                Math.max(dfs_1(gold, i-1, j+1, cache), dfs_1(gold, i, j+1, cache)),
                dfs_1(gold, i+1, j+1, cache));
        cache[i][j] = res;
        return res;
    }

    @Test
    public void case1() {
        assertEquals(16, getMaxGold_2(gold, m, n));
    }

    public String reversePrefix(String word, char ch) {
        int i = word.indexOf(ch);
        if (i == -1)
            return word;
        return new StringBuilder(word.substring(0, ch+1)).reverse().toString() + word.substring(i);
    }
}
