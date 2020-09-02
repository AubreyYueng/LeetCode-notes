package dfs.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/2/18 11:18
 * 200. Number of Islands
 * https://leetcode.com/problems/number-of-islands/
 */
public class NumberOfIslands {

    private int col;
    private int row;
    private char[][] grid;

    // below is the solution for dfs, time O(MN), space worst O(MN) when grid filled with lands and dfs has to goes by MN deep
    // we also have bfs and union find solutions, be sure to comprehend them later
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;

        this.col = grid[0].length;
        this.row = grid.length;
        int cnt = 0;
        this.grid = grid;
        for(int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == '1')
                    cnt++;
                dfs(r, c);
            }
        }

        return cnt;

    }

    private void dfs(int r, int c) {
        if (r < 0 || c < 0 || r >= row || c >= col || grid[r][c] != '1')
            return;
        grid[r][c] = '0';
        dfs(r-1, c);
        dfs(r+1, c);
        dfs(r, c-1);
        dfs(r, c+1);
    }

    int clusterCount(int numOfRows, List<String> grid) {
        if (grid == null || grid.size() == 0) return 0;
        return new Helper(new ArrayList<>(grid)).countClusters();
    }

    private static class Helper {
        private final int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        private final int colCnt;
        private final int rowCnt;
        private final char[][] grid;

        private Helper(List<String> input) {
            this.colCnt = input.get(0).length();
            this.rowCnt = input.size();
            this.grid = new char[rowCnt][colCnt];
            for (int i = 0; i < rowCnt; i++) {
                String line = input.get(i);
                for (int j = 0; j < colCnt; j++) {
                    grid[i][j] = line.charAt(j);
                }
            }
        }

        private int countClusters() {
            int res = 0;

            for (int r = 0; r < rowCnt; r++) {
                char[] row = grid[r];
                for (int c = 0; c < colCnt; c++) {
                    char ch = row[c];
                    if (Character.isLowerCase(ch)) {
                        res++;
                        dfs(r, c, ch);
                    }
                }
            }

            return res;
        }

        private void dfs(int r, int c, char ch) {
            if (!inCluster(r, c, ch))
                return;

            grid[r][c] = Character.toUpperCase(ch);
            for (int[] dir : directions) {
                dfs(r+dir[0], c+dir[1], ch);
            }
        }

        private boolean inCluster(int r, int c, char ch) {
            return r >= 0 && c >= 0 && r < rowCnt && c < colCnt && ch == grid[r][c];
        }
    }

    @Test
    public void case1() {
        assertEquals(5, clusterCount(3, Arrays.asList("aabba", "aabba", "aaacb")));
    }

}
