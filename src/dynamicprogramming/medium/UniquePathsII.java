package dynamicprogramming.medium;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/9/1 15:03
 *
 * 63. Unique Paths II
 *
 * A robot is located at the top-left corner of a m x n grid
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right
 * corner of the grid
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * Note: m and n will be at most 100.
 *
 * Example 1:
 * Input:
 * [
 *  [0,0,0],
 *  [0,1,0],
 *  [0,0,0]
 * ]
 * Output: 2
 * Explanation:
 * There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 *
 * TODO: method 1 is similar with {@link UniquePaths#uniquePaths_}, both need to avoid influencing the top vertex
 * TODO: method 2 is similar with {@link UniquePaths#uniquePaths}, process col0 and row0 first. (faster)
 * TODO: int[][] is default all 0
 */
public class UniquePathsII {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rowCnt = obstacleGrid.length;
        int colCnt = obstacleGrid[0].length;
        int[][] paths = new int[rowCnt][colCnt];
        paths[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0;

        for (int i = 0; i < rowCnt; i++) {
            for (int j = 0; j < colCnt; j++) {
                if (i == 0 && j == 0)
                    continue;

                paths[i][j] = 0;

                if (obstacleGrid[i][j] != 1) {
                    if (i > 0)
                        paths[i][j] += paths[i-1][j];
                    if (j > 0)
                        paths[i][j] += paths[i][j-1];
                }

            }
        }
        return paths[rowCnt-1][colCnt-1];
    }

    public int uniquePathsWithObstacles_(int[][] obstacleGrid) {
        if(obstacleGrid[0][0] == 1){
            return 0;
        }
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];

        for(int i = 0 ; i < row ; i++){
            if(obstacleGrid[i][0] != 1){
                dp[i][0] = 1;
            }else{
                break;
            }
        }
        for(int j = 0 ; j < col ; j++){
            if(obstacleGrid[0][j] != 1){
                dp[0][j] = 1;
            }
            else{
                break;
            }
        }
        for(int i = 1 ; i < row ; i++){
            for(int j = 1 ; j < col ; j++){
                if(obstacleGrid[i][j] != 1){
                    dp[i][j] = dp[i][j-1] + dp[i-1][j];
                }
            }
        }
        return dp[row-1][col-1];
    }

    @Test
    public void case1() {
        assertEquals(2, uniquePathsWithObstacles(new int[][]{
                {0,0,0},
                {0,1,0},
                {0,0,0}
        }));
    }

    @Test
    public void case2() {
        assertEquals(0, uniquePathsWithObstacles(new int[][]{
                {1}
        }));
    }

    @Test
    public void case3() {
        assertEquals(0, uniquePathsWithObstacles(new int[][]{
                {1,0}
        }));
    }

}
