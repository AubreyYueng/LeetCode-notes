package dfs.hard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/18 15:58
 *
 * 329. Longest Increasing Path in a Matrix
 */
public class LongestIncreasingPathInAMatrix {

    // Q: Longest increasing path, can not move diagonally.
    // Here we use DFS + memorization
    // Can be seen a graph, two adjacent cells have a directed edge (a, b) if a < b.
    // The following codes are mostly copied from LC Solution
    // Time: O(mn), Space: O(mn)
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        return new Helper(matrix).res();
    }

    private static class Helper {
        private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int m;
        int n;
        int[][] matrix;
        int[][] cache;

        Helper(int[][] matrix) {
            this.matrix = matrix;
            this.m = matrix.length;
            this.n = matrix[0].length;
            this.cache = new int[m][n];
        }

        private int res() {
            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    res = Math.max(res, dfs(i, j));
                }
            }
            return res;
        }

        private int dfs(int i, int j) {
            if (cache[i][j] != 0) return cache[i][j];
            int adjacentMax = 0;
            for (int[] d : dirs) {
                int x = i+d[0];
                int y = j+d[1];
                if (validMove(i, j, x, y))
                    adjacentMax = Math.max(adjacentMax, dfs(x, y));
            }
            cache[i][j] = adjacentMax+1;
            return cache[i][j];
        }

        private boolean validMove(int i, int j, int x, int y) {
            return x >= 0 && y >= 0 && x < m && y < n && matrix[x][y] > matrix[i][j];
        }

    }

    @Test
    public void case1() {
        // longest increasing path is [1, 2, 6, 9]
        assertEquals(4, longestIncreasingPath(new int[][]{
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        }));
    }

    @Test
    public void case2() {
        // longest increasing path is [3, 4, 5, 6]
        assertEquals(4, longestIncreasingPath(new int[][]{
                {3, 4, 5},
                {3, 2, 6},
                {2, 2, 1}
        }));
    }

}
