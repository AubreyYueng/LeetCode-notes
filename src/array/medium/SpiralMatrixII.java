package array.medium;

/**
 * Created by Yiyun On 2020/7/5 21:27
 * 59. Spiral Matrix II
 */
public class SpiralMatrixII {

    public int[][] generateMatrix(int n) {
        int num = 1;
        int[][] res = new int[n][n];
        int i1 = 0;
        int j1 = 0;
        int i2 = n-1;
        int j2 = n-1;

        while (j1 <= j2 && i1 <= i2) {
            for (int j = j1; j <= j2; j++) res[i1][j] = num++;
            i1++;
            for (int i = i1; i <= i2; i++) res[i][j2] = num++;
            j2--;
            if (i1 <= i2)
                for (int j = j2; j >= j1; j--) res[i2][j] = num++;
            i2--;
            if (j1 <= j2)
                for (int i = i2; i >= i1; i--) res[i][j1] = num++;
            j1++;
        }
        return res;
    }

}
