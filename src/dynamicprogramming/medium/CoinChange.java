package dynamicprogramming.medium;

import java.util.Arrays;

/**
 * Created by Yiyun On 2020/1/27 21:20
 *
 * 322. Coin Change
 * https://leetcode.com/problems/coin-change/
 */
public class CoinChange {

    // TODO: 不用 backtracking 的原因: backtrack 一般是找 all possible combinations, 但这个不需要
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] arr = new int[amount+1];
        Arrays.fill(arr, amount+1);
        arr[0] = 0;
        // f(n) = min({f(x-n) | x in coins and x <= n})

        for (int i = 0; i <= amount; i++) {
            for (int c : coins) {
                if (c <= i) arr[i] = Math.min(arr[i], arr[i - c] + 1);
            }
        }

        return arr[amount] == amount+1 ? -1 : arr[amount];
    }

}
