package _2020.contest._0919;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/9/19 23:31
 *
 * 1594. Maximum Non Negative Product in a Matrix
 */
public class MaxNonNegProductInMatrix {

    /**
     * A combination with 64 and 152.
     * Be aware:
     1. write 1000000007 instead of 10^9 + 7;
     2. wrap the result. eg.: (int)(max[m - 1][n - 1] % 1000000007)
     */
    public int maxProductPath(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        long[][] min = new long[m][n];
        long[][] max = new long[m][n];

        // initialization
        min[0][0] = max[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) max[i][0] = min[i][0] = grid[i][0] * max[i - 1][0];
        for (int i = 1; i < n; i++) max[0][i] = min[0][i] = grid[0][i] * max[0][i - 1];

        // start of DP
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int num = grid[i][j];
                List<Long> list = new LinkedList<>(Arrays.asList(min[i][j-1], min[i-1][j], max[i][j-1], max[i-1][j]))
                        .stream().map(l -> l * num).sorted().collect(Collectors.toList());
                min[i][j] = list.get(0);
                max[i][j] = list.get(3);
            }
        }

        return max[m - 1][n - 1] < 0 ? -1 : (int)(max[m - 1][n - 1] % 1000000007);
    }

    @Test
    public void case1() {
        assertEquals(-1, maxProductPath(new int[][]
                {{-1,-2,-3},
               {-2,-3,-3},
               {-3,-3,-2}}
        ));
    }

    @Test
    public void case2() {
        assertEquals(8, maxProductPath(new int[][]
                {{1,-2,1},
               {1,-2,1},
               {3,-4,1}}
        ));
    }

    @Test
    public void case3() {
        assertEquals(0, maxProductPath(new int[][]
                {{1, 3},
                {0, -4}}
        ));
    }

    @Test
    public void case4() {
        assertEquals(2, maxProductPath(new int[][]
                {{ 1, 4,4,0},
               {-2, 0,0,1},
               { 1,-1,1,1}}
        ));
    }

    @Test
    public void case5() {
        assertEquals(768, maxProductPath(new int[][]
                {{-1,-4,2},{4,3,-1},{2,-4,4},{1,-1,-4}}
        ));
    }

}
