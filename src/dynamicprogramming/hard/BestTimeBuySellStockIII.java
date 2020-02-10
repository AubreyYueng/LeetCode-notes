package dynamicprogramming.hard;

/**
 * Created by Yiyun On 2020/2/10 00:16
 * 123. Best Time to Buy and Sell Stock III
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 */
public class BestTimeBuySellStockIII {

    public int maxProfit(int[] prices) {
        // hold(i, 0): max profit if we still have stock at i-th day within first transaction
        // cash(i, 0): max profit if we don't have stock at i-th day within first transaction
        // also define: hold(i, 1), cash(i, 1);
        // So,
        // hold(i, 0) = max(hold(i, 0), -p)
        // cash(i, 0) = max(cash(i, 0), hold(i, 0)+p)
        // hold(i, 1) = max(hold(i, 0), cash(i, 0)-p)
        // cash(i, 1) = max(cash(i, 1), hold(i, 1)+p)
        // we define them as hold0, cash0, hold1, cash1 for convenience
        if (prices == null || prices.length == 0)
            return 0;

        int hold0 = Integer.MIN_VALUE;
        int cash0 = 0;
        int hold1 = Integer.MIN_VALUE;
        int cash1 = 0;
        for (int p: prices) {
            hold0 = Math.max(hold0, -p);
            cash0 = Math.max(cash0, hold0+p);
            hold1 = Math.max(hold1, cash0-p);
            cash1 = Math.max(cash1, hold1+p);
        }

        return cash1;
    }

}
