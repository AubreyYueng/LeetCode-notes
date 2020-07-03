package dfs.medium;

/**
 * Created by Yiyun On 2020/7/2 22:01
 *
 * 695. Max Area of Island
 */
public class MaxAreaOfIsland {

    private static int[] rDi = {0, 1, 0, -1};
    private static int[] cDi = {-1, 0, 1, 0};

    private boolean[][] seen;
    private int[][] grid;
    private int rCnt;
    private int cCnt;

    public int maxAreaOfIsland(int[][] grid) {
        this.rCnt = grid.length;
        this.cCnt = grid[0].length;
        this.seen = new boolean[rCnt][cCnt];
        this.grid = grid;

        int res = 0;
        for (int r = 0; r < rCnt; r++) {
            for (int c = 0; c < cCnt; c++) {
                res = Math.max(res, dfs(r, c));
            }
        }
        return res;
    }

    private int dfs(int r, int c) {
        if (r < 0 || r >= rCnt || c < 0 || c >= cCnt || seen[r][c] || grid[r][c] == 0)
            return 0;

        seen[r][c] = true;

        int area = 1;
        for (int i = 0; i < 4; i++) {
            area += dfs(r+rDi[i], c+cDi[i]);
        }

        return area;
    }

}
