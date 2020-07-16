package greedy.easy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/15 20:35
 *
 * 122. Best Time to Buy and Sell Stock II
 */
public class BestTimeToBuyAndSellStockII {

    // yes[i]: max profit if HAVE item at i
    // no[i]: max profit if HAVE NO item at i
    // yes[i] = max(no[i-1]-price[i], yes[i-1])
    // no[i] = max(yes[i-1]+price[i], no[i-1])
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;

        int n = prices.length;
        int yes[] = new int[n];
        int no[] = new int[n];

        yes[0] = -prices[0];
        no[0] = 0;
        for (int i = 1; i < n; i++) {
            int price = prices[i];
            yes[i] = Math.max(no[i-1]-price, yes[i-1]);
            no[i] = Math.max(yes[i-1]+price, no[i-1]);
        }

        return Math.max(yes[n-1], no[n-1]);
    }

    @Test
    public void case1() {
        assertEquals(7, maxProfit(new int[]{7,1,5,3,6,4}));
    }

    @Test
    public void case2() {
        assertEquals(4, maxProfit(new int[]{1,2,3,4,5}));
    }

    @Test
    public void case3() {
        assertEquals(0, maxProfit(new int[]{7,6,4,3,1}));
    }

}
