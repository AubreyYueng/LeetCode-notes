package dynamicprogramming.medium;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/2/1 15:46
 *
 * 1049. Last Stone Weight II
 * https://leetcode.com/problems/last-stone-weight-ii/
 */
public class LastStoneWeightII {

    public int lastStoneWeightII(int[] stones) {
        // !!!The essentiality always lies in finding some mathematical insight of the problem first!!!:

        // this problems equals to finding the minimum difference between
        // the total of two subsets
        // diff = S-2*S2 AND diff >= 0(i.e. S2 <= S/2),
        // so, we need to find the max S2,
        // which becomes a classical 0-1 knapsack problem
        int sum = Arrays.stream(stones).reduce((a,b)->a+b).getAsInt();
        int n = stones.length;
        // the minimum value when
        int limit = (int)Math.floor(sum/2.0);
        // res[i][j]: the maximum total when there i stones and limit j;
        // res[i][j] = Math.max(
        //              res[i-1][j],           without i (also when no capacity)
        //              res[i-1][j-s[i]]+s[i]  with i
        //             );
        int[][] res = new int[n+1][limit+1];
        for(int i = 0; i < n+1; i++)
            for(int j = 0; j < limit+1; j++)
                res[i][j] = 0;
        for(int i = 0; i < n; i++) {
            int s = stones[i];
            if (s <= i)     // Here's only place that needs attention
                res[1][stones[i]] = stones[i];
        }

        for(int i = 1; i < n+1; i++) {
            int s = stones[i-1];
            for (int j = 1; j < limit+1; j++) {
                int r1 = res[i-1][j];
                int rest = j-s;
                if (rest < 0)       // no capacity
                    res[i][j] = r1;
                else {
                    int r2 = res[i-1][rest];
                    res[i][j] = Math.max(r1, r2+s);
                }
            }
        }

        return sum - 2 * res[n][limit];

    }

    @Test
    public void case1() {
        assertEquals(1, lastStoneWeightII(new int[]{2,7,4,1,8,1}));
    }

    @Test
    public void case2() {
        assertEquals(1, lastStoneWeightII(new int[]{1, 2}));
    }

}
