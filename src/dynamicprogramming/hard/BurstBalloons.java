package dynamicprogramming.hard;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/6/11 16:08
 *
 * 312. Burst Balloons
 */
public class BurstBalloons {

    // Time: O(n^3). Space: O(n^2)
    public int maxCoins(int[] nums) {
        int[] data = preProcess(nums);
        int n = data.length;

        // state: dp[len+2][len+2]; dp[i][j] for max coins gained by bursting between (i,j)
        int[][] dp = new int[n][n];

        // Bottom-up: evaluate dp(i, j) by increased distance
        for (int dis = 2; dis < n; dis++) {
            for (int l = 0; l+dis < n; l++) {
                int r = l+dis;
                // func: (l for left, r for right)
                //     dp[l][r] = Max {nums[l]*nums[i]*nums[r] + dp[l][i] + dp[i][r] | i in (l, r)}
                for (int i = l+1; i < r; i++) {
                    // Suppose i-th balloon lastly burst between l and r.
                    int subCoins = data[l]*data[i]*data[r] + dp[l][i] + dp[i][r];
                    dp[l][r] = Math.max(dp[l][r], subCoins);
                }
            }
        }

        // Note: It's not dp[1][n-1]!!! (n is len of pre-processed data)
        return dp[0][n-1];
    }

    // pre-process: 1. add 1 at the beginning and the end; 2. burst all 0s first
    private int[] preProcess(int[] nums) {
        List<Integer> data = new LinkedList<>();
        data.add(1);
        for (int num : nums) {
            if (num != 0)
                data.add(num);
        }
        data.add(1);
        int[] d = new int[data.size()];
        for (int i = 0; i < data.size(); i++) {
            d[i] = data.get(i);
        }
        return d;
    }

    @Test
    public void case1() {
        assertEquals(167, maxCoins(new int[]{3,1,5,8}));
    }

}
