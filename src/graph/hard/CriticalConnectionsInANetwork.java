package graph.hard;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/2/27 23:29
 *
 * 1192. Critical Connections in a Network
 * https://leetcode.com/problems/critical-connections-in-a-network/
 */
public class CriticalConnectionsInANetwork {

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        return new APHelper(connections, n).getAp();
    }

    private static class APHelper {
        private Map<Integer, List<Integer>> adj;    // adjacent list
        private int[] low;
        private int[] disc;
        private int[] parent;
        private int times = 0;

        private List<List<Integer>> ans;

        private List<List<Integer>> getAp() {
            if (ans == null) {
                ans = new LinkedList<>();
                for(Integer k: adj.keySet()) {
                    if (disc[k] == 0)
                        dfs(k);
                }
            }
            return ans;
        }

        private void dfs(int v) {
            times++;
            disc[v] = times;
            low[v] = times;

            int chdCnt = 0;
            for(Integer u: adj.get(v)) {
                chdCnt++;
                if (disc[u] == 0) { // unvisited
                    parent[u] = v;
                    dfs(u);

                    low[v] = Math.min(low[u], low[v]);
                    if ((parent[v]==-1 && chdCnt > 1)||(parent[v]!=-1 && low[u] > disc[v]))
                        ans.add(Arrays.asList(u, v));
                } else if (parent[v] != u) {
                    low[v] = Math.min(low[v], disc[u]);
                }
            }
        }

        APHelper(List<List<Integer>> edges, int n) {
            this.adj = new HashMap<>();
            edges.forEach(this::add);
            this.low = new int[n];
            this.disc = new int[n];
            this.parent = new int[n];
            Arrays.fill(parent, -1);
        }

        private void add(List<Integer> pair) {
            Integer v = pair.get(0);
            Integer u = pair.get(1);

            adj.putIfAbsent(v, new LinkedList<>());
            adj.putIfAbsent(u, new LinkedList<>());
            adj.get(v).add(u);
            adj.get(u).add(v);
        }
    }

    @Test
    public void case1() {
        int[][] pairs = {{0, 1}, {1, 2}, {2, 0}, {1, 3}};
        verify("[[3, 1]]", 4, pairs);
    }

    private void verify(String expected, int n, int[][]pairs) {
        List<List<Integer>> ans = criticalConnections(n, connections(pairs));
        assertEquals(expected, "["+ans.stream().map(s->Arrays.toString(s.toArray())).reduce((a,b)->a+", "+b).orElse("")+"]");
    }

    private List<List<Integer>> connections(int[]... pairs) {
        List<List<Integer>> ans = new LinkedList<>();
        for (int[] p : pairs) {
            ans.add(Arrays.asList(p[0], p[1]));
        }
        return ans;
    }

}
