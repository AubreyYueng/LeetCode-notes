package dynamicprogramming.medium;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/11/1 23:44
 *
 * 518. Coin Change 2
 *
 * You are given coins of different denominations and a total amount of money. Write a function to compute the number
 * of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.
 * Example 1:
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * Example 2:
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 *
 * Example 3:
 * Input: amount = 10, coins = [10]
 * Output: 1
 *
 * Note:
 * You can assume that
 * 0 <= amount <= 5000
 * 1 <= coin <= 5000
 * the number of coins is less than 500
 * the answer is guaranteed to fit into signed 32-bit integer
 *
 * TODO: backtracking can cause Time Limit Exceeded(case 4)
 * TODO: backtracking + memorization:
 * TODO:    (https://leetcode.com/problems/coin-change-2/discuss/388906/DFS-%2B-memoization)
 * TODO: use DP
 */
public class CoinChange2 {

    public int change_backtracking(int amount, int[] coins) {
        Arrays.sort(coins);
        int[] res = new int[]{0};
        dfs(res, 0, coins, amount);
        return res[0];
    }

    private void dfs(int[] res, int st, int[] coins, int restAmt) {
//        System.out.println("restAmt: " + restAmt + ", st: " + st + ", res: " + res[0]);
        if (restAmt <= 0) {
            if (restAmt == 0)
                res[0]++;
//            System.out.println();
            return;
        }

        for (int i = st; i < coins.length; i++) {
            dfs(res, i, coins, restAmt-coins[i]);
        }

    }

    public int change_dp(int amount, int[] coins) {
        int[] f = new int[amount + 1];
        f[0] = 1;   // TODO: here we can't pre-set f[coin]=1, refer to the meaning of f[i] below carefully

        for (int coin : coins) {    // TODO: f[i] for ways to construct i, using only ITERATED COIN
            for (int i = coin; i <= amount; i++) {
                f[i] += f[i-coin];
            }
        }

        return f[amount];
    }

    @Test
    public void case1() {
        assertEquals(4, change_backtracking(5, new int[]{1,2,5}));
    }

    @Test
    public void case2() {
        assertEquals(0, change_backtracking(3, new int[]{2}));
    }

    @Test
    public void case3() {
        assertEquals(1, change_backtracking(10, new int[]{10}));
    }

    /*@Test
    public void case4() {
        assertEquals(35502874, change_backtracking(500, new int[]{3,5,7,8,9,10,11}));
    }*/

    @Test
    public void case5() {
        assertEquals(4, change_dp(5, new int[]{1,2,5}));
    }

    @Test
    public void case6() {
        assertEquals(0, change_dp(3, new int[]{2}));
    }

    @Test
    public void case7() {
        assertEquals(1, change_dp(10, new int[]{10}));
    }

    @Test
    public void case8() {
        assertEquals(35502874, change_dp(500, new int[]{3,5,7,8,9,10,11}));
    }

}
