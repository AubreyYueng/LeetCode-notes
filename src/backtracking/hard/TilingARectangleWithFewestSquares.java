package backtracking.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yiyun On 2020/7/25 14:14
 *
 * 1240. Tiling a Rectangle with the Fewest Squares
 */
public class TilingARectangleWithFewestSquares {

    // It's a solution copied from top voted discussion with some personal comments.
    public int tilingRectangle(int n, int m) {
        return new Helper(n, m).res;
    }

    private static class Helper {
        Map<Long, Integer> set = new HashMap<>();
        int res = Integer.MAX_VALUE;

        public Helper(int n, int m) {
            if (n > m) {
                dfs(m, n, new int[m], 0);
            } else {
                dfs(n, m, new int[n], 0);
            }
        }

        private void dfs(int n, int m, int[] h, int cnt) {
            if (cnt >= res) return;
            boolean isFull = true;
            int pos = -1, minH = Integer.MAX_VALUE;

            // 1. Check if full.
            // pos: i for min{h[i]}, set minH = min{h[i]}
            // init: minH = 0, pos = 0
            for (int i = 0; i < n; i++) {
                if (h[i] < m) isFull = false;
                if (h[i] < minH) {
                    pos = i;
                    minH = h[i];
                }
            }

            // 2. If Full: set min res, then return.
            if (isFull) {
                res = Math.min(cnt, res);
                return;
            }

            // Not Full.
            // 3. hash = \sum_i h(i)(m+1)^{n-i+1}
            // Put to set if smaller/not existed, otherwise return.
            // init: hash=0, cnt=0
            long hash = 0, base = m + 1;
            for (int i = 0; i < n; i++) {
                hash *= base;
                hash += h[i];
            }
            if (set.containsKey(hash) && set.get(hash) <= cnt) return;
            set.put(hash, cnt);

            // 4. Find max end<n and h([pos, end])=minH s.t. distance(pos, end) + minH <= m
            // init: pos = 0, end = n-1, minH = 0
            int end = pos;
            while (end+1 < n && h[end+1] == h[pos] && (end+1 - pos + 1 + minH) <= m) end++;

            // 5. Add new square edge length(starts from larger size)
            // Update height: for j in [pos, end]: newH = oldH + distance(pos, j)
            for (int j = end; j >= pos; j--) {
                int[] next  = Arrays.copyOf(h, n);
                for (int k = pos; k <= j; k++) next[k] += j - pos + 1;
                dfs(n, m, next, cnt + 1);
            }
        }
    }

}
