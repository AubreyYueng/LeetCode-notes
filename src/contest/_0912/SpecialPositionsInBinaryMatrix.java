package contest._0912;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 9/12/20 22:31
 *
 * 1582. Special Positions in a Binary Matrix
 */
public class SpecialPositionsInBinaryMatrix {

    public int numSpecial(int[][] mat) {
        if (mat == null || mat.length == 0) return 0;
        int m = mat.length;
        int n = mat[0].length;

        int[] rowCnt = new int[m];
        int[] colCnt = new int[n];

        List<One> ones = new ArrayList<>();

        for (int i = 0; i < mat.length; i++) {
            int[] row = mat[i];
            for (int j = 0; j < row.length; j++) {
                int num = mat[i][j];
                if (num == 1) {
                    ones.add(new One(i, j));
                    rowCnt[i]++;
                    colCnt[j]++;
                }
            }
        }

        int res = 0;
        for (One o : ones) {
            if (rowCnt[o.i] == 1 && colCnt[o.j] == 1)
                res++;
        }
        return res;
    }

    private static class One {
        int i;
        int j;

        One(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    @Test
    public void case1() {
        // [[1,0,0],[0,0,1],[1,0,0]]
        assertEquals(1, numSpecial(new int[][]{
                {1, 0, 0},
                {0, 0, 1},
                {1, 0, 0}
        }));
    }

}
