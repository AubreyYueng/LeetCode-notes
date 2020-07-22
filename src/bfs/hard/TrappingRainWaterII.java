package bfs.hard;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/20 20:45
 *
 * 407. Trapping Rain Water II
 */
public class TrappingRainWaterII {

    private static int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static class Cell {
        int r;
        int c;
        int h;

        Cell(int r, int c, int h) {
            this.r = r;
            this.c = c;
            this.h = h;
        }
    }

    private static class Helper {
        private boolean[][] visited;
        private int[][] heightMap;
        private PriorityQueue<Cell> pq;
        private int m;          // row cnt
        private int n;          // col cnt
        private int res = 0;

        public Helper(int[][] heightMap) {
            this.heightMap = heightMap;
            this.pq = new PriorityQueue<>(Comparator.comparing(c -> c.h));
            this.m = heightMap.length;
            this.n = heightMap[0].length;
            visited = new boolean[m][n];

            init();
            bfs();
        }

        private void init() {
            // init left/right-most col
            for (int i = 0; i < m; i++) init(i, i, 0, n -1);
            // init top/bottom row
            for (int j = 0; j < n; j++) init(0, m -1, j, j);
        }

        private void init(int i1, int i2, int j1, int j2) {
            visited[i1][j1] = true;
            visited[i2][j2] = true;
            pq.add(new Cell(i1, j1, heightMap[i1][j1]));
            pq.add(new Cell(i2, j2, heightMap[i2][j2]));
        }

        private void bfs() {
            while (!pq.isEmpty()) {
                Cell cur = pq.poll();
                for (int[] d : dirs) {
                    int r = cur.r + d[0];
                    int c = cur.c + d[1];
                    // the lowest of visited cell decides whether its surrounded unvisited cell can trap water,
                    // then update their heights as a higher height compared to its own.
                    if (r > 0 && c > 0 && r < m -1 && c < n -1 && !visited[r][c]) {
                        int h = heightMap[r][c];
                        visited[r][c] = true;
                        res += Math.max(0, cur.h-h);
                        pq.add(new Cell(r, c, Math.max(cur.h, h)));
                    }
                }
            }
        }
    }

    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0) return 0;
        return new Helper(heightMap).res;
    }

    @Test
    public void case1() {
        assertEquals(4, trapRainWater(new int[][]{
                {1,4,3,1,3,2},
                {3,2,1,3,2,4},
                {2,3,3,2,3,1}
        }));
    }


}
