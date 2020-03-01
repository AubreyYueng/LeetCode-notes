package bfs.easy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Yiyun On 2020/2/27 23:30
 * 994. Rotting Oranges
 * https://leetcode.com/problems/rotting-oranges/
 */
public class RottingOranges {

    public int orangesRotting(int[][] grid) {
        // This problem equals to finding the lowest depth number
        // Understand that every minute rotten happens simutaneously
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, -1, 0, 1};

        int row = grid.length;
        int col = grid[0].length;

        LinkedList<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> depth = new HashMap<>();
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] != 2) continue;
                int pos = r * col + c;
                depth.put(pos, 0);
                queue.add(pos);
            }
        }

        int ans = 0;
        while(!queue.isEmpty()) {
            Integer pos = queue.pop();
            int r = pos / col;
            int c = pos % col;
            int dep = depth.get(pos);
            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (0 <= nr && nr < row && 0 <= nc && nc < col && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2;
                    int nPos = nr * col + nc;
                    queue.add(nPos);
                    depth.put(nPos, dep+1);
                    ans = dep+1;
                }
            }
        }

        for(int[] nRow: grid) {
            for(int n: nRow) {
                if (n == 1)
                    return -1;
            }
        }

        return ans;
    }

}
