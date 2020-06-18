package greedy.hard;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/6/18 17:57
 *
 * 410. Split Array Largest Sum
 */
public class SplitArrayLargestSum {

    // Solution 1: DP, reason: non-after effect, i.e., once the state of a certain stage is determined,
    // it is not affected by the state in the future
    // state: [i][j], minimum largest array sum for splitting nums[0,...,i] into j parts
    // fun: suppose split j-th sub-array at index k,
    //     then array largest sum (k, i, j) = max([k][j-1], nums[k+1]+...+nums[i])
    //     [i][j] = min { {k, i, j} | all possible k }
    // res: [n][m]
    // init: [n+1][m+1], default to maxInt except [0][0]
    //
    // Trick: let sum[i] for sum over first i numbers, then nums[k+1]+...+nums[i] = sum[i] - sum[k]
    public int splitArray(int[] nums, int m) {
        int n = nums.length;

        int[][] dp = new int[n+1][m+1];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        dp[0][0] = 0;

        int[] sum = new int[n+1];
        for (int i = 0; i < n; i++) {
            sum[i+1] = sum[i] + nums[i];
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = j-1; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j-1], sum[i]-sum[k]));
                }
            }
        }
        return dp[n][m];
    }

    @Test
    public void case1() {
        assertEquals(18, splitArray(new int[]{7,2,5,10,8}, 2));
    }

    @Test
    public void case2() {
        assertEquals(2147483647, splitArray(new int[]{1, 2147483647}, 2));
    }

}
