package _2020.contest._0801;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/8/1 22:34
 *
 * 1534. Count Good Triplets
 */
public class CountGoodTriplets {

    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length;
        boolean tmp[][] = new boolean[n][n];
        Helper h = new Helper();
        h.countBetween(0, n-1, a, b, c, arr, tmp);
        return h.res;
    }

    private static class Helper {
        int res;

        private void countBetween(int i, int k, int a, int b, int c, int[] arr, boolean[][] tmp) {
            if (i+1 < k) {
                if (tmp[i][k]) return;

                int x = arr[i];
                int z = arr[k];
                if (Math.abs(x - z) <= c) {
                    for (int j = i + 1; j < k; j++) {
                        int y = arr[j];
                        if (Math.abs(x - y) <= a && Math.abs(y - z) <= b) {
                            res++;
                        }
                    }
                }
                tmp[i][k] = true;

                countBetween(i + 1, k, a, b, c, arr, tmp);
                countBetween(i, k - 1, a, b, c, arr, tmp);
                countBetween(i + 1, k - 1, a, b, c, arr, tmp);
            }
        }
    }

    @Test
    public void case1() {
        assertEquals(4, countGoodTriplets(new int[]{3,0,1,1,9,7}, 7, 2, 3));
    }

    @Test
    public void case2() {
        assertEquals(0, countGoodTriplets(new int[]{1,1,2,2,3}, 0, 0, 1));
    }

}
