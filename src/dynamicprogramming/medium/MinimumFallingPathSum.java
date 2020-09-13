package dynamicprogramming.medium;

import java.util.Arrays;

/**
 * Created by Yiyun On 2020/1/28 11:08
 *
 * 931. Minimum Falling Path Sum
 * https://leetcode.com/problems/minimum-falling-path-sum/
 */
public class MinimumFallingPathSum {

    // dp[i, j]: min falling path sum of each cell limited in the first i rows
    public int minFallingPathSum_review20200912(int[][] A) {
        if (A == null || A.length == 0) return 0;
        int m = A.length;
        int n = A[0].length;

        int[] dp = Arrays.copyOf(A[0], n);
        for (int i = 1; i < m; i++) {
            int[] currDp = Arrays.copyOf(A[i], n);
            for (int j = 0; j < n; j++) {
                int tmp = dp[j];
                if (j > 0) tmp = Math.min(tmp, dp[j-1]);
                if (j < n-1) tmp = Math.min(tmp, dp[j+1]);
                currDp[j] += tmp;
            }
            dp = currDp;
        }
        return Arrays.stream(dp).min().getAsInt();
    }

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
