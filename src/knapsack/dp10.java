package knapsack;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/2/1 17:16
 * https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
 */
public class dp10 {

    // find out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal
    // to W. You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).
    int val[] = new int[]{60, 100, 120};    // value of item
    int wt[] = new int[]{10, 20, 30};       // weight of item
    int  W = 50;        // the capacity of knapsack
    // solution 220


    // only consider subsets whose total weight is less than W(50)
    // for all such subsets, pick the maximum value subset
    public int knapsack(int W, int wt[], int val[]) {
        // K(n, W): maximum weight gain by n items and W capacity
        // K(n, W) = max(
        //      K(n-1, W),          nth excluded, (also when not enough capacity)
        //      K(n-1, W-w_i)+v_i   nth included
        // )

        int n = wt.length;
        int[][] res = new int[n+1][W+1];
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < W+1; j++)
                res[i][j] = 0;
        }
        for (int i = 1; i < n+1; i++)
            res[1][wt[i-1]] = val[i-1];


        for (int i = 1; i < n+1; i++) {
            int w = wt[i-1];
            int v = val[i-1];
            for (int j = 1; j < W+1; j++) {
                int r1 = res[i-1][j];
                int rest = j-w;
                if (rest < 0)
                    res[i][j] = r1;
                else {
                    int r2 = res[i-1][rest];
                    res[i][j] = Math.max(r1, r2+v);
                }
            }
        }

        return res[n][W];
    }

    @Test
    public void case1() {
        assertEquals(220, knapsack(
                50,
                new int[]{10, 20, 30},
                new int[]{60, 100, 120}));
    }

}
