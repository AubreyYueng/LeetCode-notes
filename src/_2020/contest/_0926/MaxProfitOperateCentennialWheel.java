package _2020.contest._0926;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/9/26 23:20
 *
 * 1599. Maximum Profit of Operating a Centennial Wheel
 */
public class MaxProfitOperateCentennialWheel {

    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        if (runningCost / boardingCost  > 4) return -1;
        int n = customers.length;

        int wait = 0;
        int currProfit = 0;
        int currMove = 0;

        int maxProfit = 0;
        int moveOfMaxProfit = 0;

        int i = 0;
        while (currMove == 0 || (wait > 0 || i < n)) {
            int curr = wait + (i < n ? customers[i++] : 0);
            int boarding = Math.min(curr, 4);
            wait = Math.max(0, curr - boarding);

            currProfit += (boarding * boardingCost) - runningCost;
            currMove++;

            if (currProfit > maxProfit) {
                maxProfit = currProfit;
                moveOfMaxProfit = currMove;
            }
        }

        return moveOfMaxProfit <= 0 ? -1 : moveOfMaxProfit;
    }

    @Test
    public void case1() {
        assertEquals(3, minOperationsMaxProfit(new int[]{8, 3}, 5, 6));
    }

    @Test
    public void case2() {
        assertEquals(7, minOperationsMaxProfit(new int[]{10, 9, 6}, 6, 4));
    }

    @Test
    public void case3() {
        assertEquals(-1, minOperationsMaxProfit(new int[]{3,4,0,5,1}, 1, 92));
    }

    @Test
    public void case4() {
        assertEquals(9, minOperationsMaxProfit(new int[]{10,10,6,4,7}, 3, 8));
    }

    @Test
    public void case5() {
        assertEquals(-1, minOperationsMaxProfit(new int[]{
                27,17,2,36,28,30,35,45,27,9,26,16,35,42,30,7,22,49,35,7,11,47,36,16,9,50,6,29,47,16,24,1,15,1,38,18,13,11,29,31,12,47,50,14,47,8,15,16,8,49,41,17,1,7,19,43,33,22,41,23,6,5,31,16,39,49,12,18,13,38,28,26,20,12,17,28,25,1,32,38,9,32,6,44,36,43,42,36,33,8,12,17,28,21,8,11,6,28,37,19,14,12,33,1,41,35,12,39,10,42,1,37,31,13,43,18,48,1,47,34,20,37,43,30,35,30,3,36,46,12,35,47,33,29,9,34,26,33,36,32,32,17,48,8,10,24,8,13,40,30,34,35,17,39,49,40,43,39,40,29,19,25,42,0,29,34,23,13,35,6,2,19,40,26,36,47,11,21,25,6,14,46,47,23,1,35,22,30,18,50,31,33,8,19,23,4,40,12,18,5,31,1,1,24,21,28,19,9,32,39,3,41,37,19,4,29,16,39,6,12,24,2,40,31,2,33,49,43,12,43,44,37,17,30,14,0,48,38,35,23,7,25,35,46,45,28,8,46,10,23,16,33,13,40,39,45,40,13,7,3,37,45,24,4,2,10,49,3,9,0,35,42,5,34,5,6,27,24,25,49,40,25,3,31,16,19,34,15,22,6,43,2,1,8,41,42,3,42,36,8,4,44,10,3,24,33,34,31,0,10,17,26,41,44,50,8,7,47,15,7,26,13,47,7,27,18,36,12,33,27,43,28,43,32,3,43,15,16,32,46,11,34,8,6,16,14,8,19,13,9,11,17,28,38,43,2,9,5,45
        }, 6, 29));
    }

}
