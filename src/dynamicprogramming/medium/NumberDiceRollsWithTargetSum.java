package dynamicprogramming.medium;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/2/3 09:16
 *
 * 1155. Number of Dice Rolls With Target Sum
 * https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
 */
public class NumberDiceRollsWithTargetSum {

    public int numRollsToTarget(int d, int f, int target) {
        // f(d, t) = f(d-1, 1) + f(d-1, 2)+...+f(d-1,i)+...+f(d-1,t)
        int MOD = 1000000007;
        if(target < d || target > f*d) return 0;
        int[][] dp = new int[d+1][target+1];
        dp[0][0] = 1;
        for(int i = 1; i <= d; i++) {
            for(int j = 1; j <= f; j++) {
                for(int k = i-1; k <= f*(i-1) && j+k <= target; k++) {
                    dp[i][j+k] = (dp[i][j+k] + dp[i-1][k]) % MOD;
                }
            }
        }
        return dp[d][target];
    }

    @Test
    public void case1() {
        assertEquals(1, numRollsToTarget(1,6,3));
    }

    @Test
    public void case2() {
        assertEquals(6, numRollsToTarget(2,6,7));
    }

    @Test
    public void case3() {
        assertEquals(1, numRollsToTarget(2,5,10));
    }

    @Test
    public void case4() {
        assertEquals(0, numRollsToTarget(1,2,3));
    }

    @Test
    public void case5() {
        assertEquals(222616187, numRollsToTarget(30,30,500));
    }

}
