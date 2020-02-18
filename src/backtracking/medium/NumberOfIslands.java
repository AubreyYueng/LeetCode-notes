package backtracking.medium;

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

}
