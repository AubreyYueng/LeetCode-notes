package dynamicprogramming.hard;

import java.util.Arrays;

/**
 * Created by Yiyun On 2020/2/10 13:56
 * 188. Best Time to Buy and Sell Stock IV
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv
 */
public class BestTimeBuySellStockIV {

    public int maxProfit(int k, int[] prices) {
        // hold(i), cash(i)
        // initialize:
        //      hold(0) = max(hold(0), -p);
        // we start to iterate from 1
        // hold(i) = max(hold(i), cash(i-1)-p)
        // cash(i) = max(cash(i), hold(i)+p)
        // from the observation above it's all about pre and next, so we don't need to maintain an array
        if(prices == null || prices.length == 0 || k == 0)
            return 0;
        int len = prices.length;
        if (k >= len / 2)
            return quickSolve(prices);  // add a "quickSolve" function to tackle some corner cases to avoid TLE

        int negSum = 0;
        for(int p: prices) negSum-=p;

        int[] hold = new int[k];
        int[] cash = new int[k];
        Arrays.fill(hold, negSum);
        for(int p: prices) {
            hold[0] = Math.max(hold[0], -p);
            cash[0] = Math.max(cash[0], hold[0]+p);
            for (int i = 1; i < k; i++) {
                hold[i] = Math.max(hold[i], cash[i-1]-p);
                cash[i] = Math.max(cash[i], hold[i]+p);
            }
        }

        return cash[k-1];

    }

    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }

}
