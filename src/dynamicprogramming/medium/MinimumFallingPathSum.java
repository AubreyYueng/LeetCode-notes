package dynamicprogramming.medium;

import java.util.Arrays;

/**
 * Created by Yiyun On 2020/1/28 11:08
 *
 * 931. Minimum Falling Path Sum
 * https://leetcode.com/problems/minimum-falling-path-sum/
 */
public class MinimumFallingPathSum {

    public int minFallingPathSum(int[][] A) {
        int row = A.length;
        int col = A[0].length;

        // initialize
        int[][] arr = new int[row][col];
        for (int[] r : arr) {
            for (int i = 0; i < col; i++) {
                r[i] = 101;
            }
        }
        for (int i = 0; i < col; i++) {
            arr[0][i] = A[0][i];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int pre = arr[i-1][j];
                if (j > 0) pre = Math.min(pre, arr[i-1][j-1]);
                if (j < col-1) pre = Math.min(pre, arr[i-1][j+1]);
                arr[i][j] = pre + A[i][j];
            }
        }

        int res = arr[row-1][0];
        for (int c : arr[row-1])
            res = Math.min(res, c);

        return res;
    }

}
