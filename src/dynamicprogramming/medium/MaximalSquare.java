package dynamicprogramming.medium;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/2/2 10:02
 *
 * 221. Maximal Square
 * https://leetcode.com/problems/maximal-square/
 */
public class MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        // !!!The essentiality is in finding the relation among a index with its neighbor indices!!!

        // f(i,j) for side lenth of a maximal square whose bottom right corner cell index (i, j)
        // for every 1, update f(i,j) = min(f(i-1,j), f(i-1,j-1), f(i,j-1)) + 1

        // From the observation of the equation above, it's the current row and the previous row that matters, so let f(j) stands for side length of col j of current row
        // f(j) = min(old_f(j), old_f(j-1), f(j-1))

        if (matrix == null || matrix.length == 0) return 0;

        int max = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[] sideLen = new int[col+1];     // col+1: so that we don't need to process index is 0;
        int oldPrev = 0;

        for(int i = 1; i <= row; i++) {
            char[] currRow = matrix[i-1];   // here we don't create a currSideLen, just update the original sideLen

            for (int j = 1; j <= col; j++) {
                char curr = currRow[j-1];
                int old = sideLen[j];

                if (curr == '1') {
                    sideLen[j] = Math.min(
                            oldPrev,
                            Math.min(old, sideLen[j-1])
                    ) + 1;
                    max = Math.max(max, sideLen[j]);
                } else
                    sideLen[j] = 0;

                oldPrev = old;
            }
        }

        return max * max;
    }

    @Test
    public void case1() {
        assertEquals(4, maximalSquare(new char[][]{
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        }));
    }

}
