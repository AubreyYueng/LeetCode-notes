package dynamicprogramming.medium;

/**
 * Created by Yiyun On 2020/2/9 00:22
 *
 * 714. Best Time to Buy and Sell Stock with Transaction Fee
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 */
public class BestTimeBuySellStockTransactionFee {

    public int maxProfit(int[] prices, int fee) {
        // hold(i): max profit if still has stock at i-th day
        // cash(i): max profit if has no stock at i-th day
        // if sell at i-th day: cash(i) = max(cash(i), hold(i)+price[i]-fee)
        // if buy at i-th day: hold(i) = max(hold(i), cash(i)-price[i])
        // because only i-th cash & hold in very loop so we don't need arrays.

        if(prices == null || prices.length == 0) return 0;

        int hold = -prices[0];
        int cash = 0;
        for (int i = 1; i < prices.length; i++) {
            int p = prices[i];
            cash = Math.max(cash, hold + p - fee);
            hold = Math.max(hold, cash - p);
        }

        return cash;
    }

}
