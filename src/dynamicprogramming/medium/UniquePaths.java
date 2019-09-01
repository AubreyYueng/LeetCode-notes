package dynamicprogramming.medium;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/9/1 15:51
 *
 * 62. Unique Paths
 *
 * A robot is located at the top-left corner of a m x n grid.
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right
 * corner of the grid.
 * How many possible unique paths are there?
 * Note: m and n will be at most 100.
 *
 * Example 1:
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 *
 * Example 2:
 * Input: m = 7, n = 3
 * Output: 28
 *
 * TODO: f(i,j) = f(i-1, j) + f(i, j-1). 即左上步数相加
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(i==0||j==0){
                    f[i][j] = 1;
                }
                else{
                    f[i][j] = f[i-1][j] + f[i][j-1];
                }
            }
        }
        return f[m-1][n-1];
    }

    public int uniquePaths_(int m, int n) {
        int[][] f = new int[m][n];
        f[0][0] = 1;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if (i == 0 && j == 0)
                    continue;

                f[i][j] = 0;
                if (i > 0)
                    f[i][j] += f[i-1][j];
                if (j > 0)
                    f[i][j] += f[i][j-1];
            }
        }
        return f[m-1][n-1];
    }

    @Test
    public void case1() {
        assertEquals(28, uniquePaths(7, 3));
    }

    @Test
    public void case2() {
        assertEquals(28, uniquePaths_(7, 3));
    }

}
