package dynamicprogramming.medium;

/**
 * Created by Yiyun On 2020/2/9 23:30
 *
 * 309. Best Time to Buy and Sell Stock with Cooldown
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 */
public class BestTimeBuySellStockCooldown {

    public int maxProfit(int[] prices) {
        // hold(i): the max profit if we still have stock at the end of i-th days;
        // cash(i): the max profit if we sell all the stocks at the end of i-th days;
        // hold(i) = max(
        //             cash(i-2)-p_i,   buy at i-th days
        //             hold(i-1)        still have stock at previous day
        //          )
        // cash(i) = max(
        //             hold(i-1)+p_i,   sell at i-th days(can not buy next day after sell but can sell next day after buy)
        //             cash(i-1)        don't have stock at previous day
        //          )
        // to save the space, we can use b0, b1, b2, etc.., the I chose not to do that in the following codes
        if (prices == null || prices.length == 0)
            return 0;

        int len = prices.length;
        int[] hold = new int[len+1];
        int[] cash = new int[len+1];
        hold[0] = 0;
        cash[0] = 0;
        hold[1] = -prices[0];
        cash[1] = 0;

        for (int i = 1; i < len; i++) {
            int idx = i+1;
            int p = prices[i];
            hold[idx] = Math.max(cash[idx-2]-p, hold[idx-1]);
            cash[idx] = Math.max(hold[idx-1]+p, cash[idx-1]);
        }

        return cash[len];
    }

}
