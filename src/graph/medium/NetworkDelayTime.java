package graph.medium;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2021/4/27 20:43
 * 743. Network Delay Time
 */
public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int n, int k) {
        // 首先定义以下三个变量：
        // g[N][N] edges
        // dist[N] 到该点的最短路径(不断更新)
        // boolean st[N] 最短路径是否已确定
        int[][] g = new int[n+1][n+1];
        int[] dist = new int[n+1];
        boolean[] st = new boolean[n+1];

        // init dist
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        // init g[][] i.e. edges
        for (int[] e: g) Arrays.fill(e, -1);
        for(int[] t: times) {
            g[t[0]][t[1]] = t[2];
        }

        int N = n;
        while (0 < N--) {    // 遍历 N 个点
            int t = -1; // 没确定的点中，离出发点距离最小的点
            for (int i = 1; i <= n; i++) {
                if (!st[i] && (t==-1 || dist[t] > dist[i]))
                    t = i;
            }

            // 用这个t(即离出发点距离最小的点)，更新其它的点
            for (int i = 1; i <= n; i++) {
                if (g[t][i] != -1)
                    dist[i] = Math.min(dist[i], dist[t] + g[t][i]);
            }

            // 最后确定t
            st[t] = true;
        }

        // 最后返回最短路径的最大值。如果存在一个点没有最短路径，说明不可达，返回-1
        int res = -1;
        for (int i = 1; i <= n; i++) {
            int d = dist[i];
            if (d == Integer.MAX_VALUE) return -1;
            res = Math.max(d, res);
        }
        return res;
    }

    @Test
    public void case1() {
        assertEquals(2, networkDelayTime(new int[][]{{2,1,1},{2,3,1},{3,4,1}}, 4, 2));
    }
}
