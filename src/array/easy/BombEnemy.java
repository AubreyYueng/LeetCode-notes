package array.easy;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 9/12/20 09:55
 *
 * 361. Bomb Enemy
 */
public class BombEnemy {

    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        return new Helper(grid).getRes();
    }

    private static class Helper {
        char[][] grid;
        int[][] kills;

        private int m;
        private int n;

        private Helper(char[][] grid) {
            this.grid = grid;
            this.m = grid.length;
            this.n = grid[0].length;
            this.kills = new int[m][n];
        }

        private int getRes() {
            // scan by row
            for (int i = 0; i < m; i++) {
                kills[i] = scanBy(grid[i]);
            }

            // scan by column
            for (int j = 0; j < n; j++) {
                int[] killsOfCol = scanBy(extractCol(j));
                for (int i = 0; i < killsOfCol.length; i++) {
                    kills[i][j] += killsOfCol[i];
                }
            }

            return Arrays.stream(kills).mapToInt(row -> Arrays.stream(row).max().getAsInt()).max().getAsInt();
        }

        private char[] extractCol(int col) {
            char[] column = new char[m];
            for (int i = 0; i < grid.length; i++) {
                column[i] = grid[i][col];
            }
            return column;
        }

        private int[] scanBy(char[] scanned) {
            int len = scanned.length;
            int[] res = new int[len];

            // slow and fast is the index of wall
            int slow = -1;
            int fast = 0;
            // eCnt is the enemies count between walls(slow/fast)
            int eCnt = 0;

            while (fast <= len) {
                char ch;
                if (fast == len || (ch=scanned[fast]) == 'W') {
                    for (int i = slow+1; i < fast; i++) {
                        if (scanned[i] == '0')
                            res[i] = eCnt;
                    }
                    eCnt = 0;       // reset eCnt
                    slow = fast;
                }
                else if (ch == 'E')
                    eCnt++;
                fast++;
            }

            return res;
        }
    }

    @Test
    public void case1() {
        assertEquals(3, maxKilledEnemies(new char[][]{
                {'0','E','0','0'},
                {'E','0','W','E'},
                {'0','E','0','0'}
        }));
    }

}
