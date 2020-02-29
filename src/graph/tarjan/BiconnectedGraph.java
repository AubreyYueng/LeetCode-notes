package graph.tarjan;

import graph.tarjan.entity.UndirectedGraph;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yiyun On 2020/2/28 22:28
 *
 * Biconnected graph:
 * An undirected graph is called Biconnected if there are two vertex-disjoint paths between any two vertices.
 *
 * A connected graph is Biconnected if it is connected and doesn’t have any Articulation Point. We mainly need to check two things in a graph.
 * 1) The graph is connected.
 * 2) There is not articulation point in graph.
 */
public class BiconnectedGraph {

    private Map<Integer, List<Integer>> adj;
    private int[] low;
    private int times = 0;
    private int[] disc;

    private BiconnectedGraph init(int[]... pairs) {
        UndirectedGraph graph = UndirectedGraph.init(pairs);
        this.adj = graph.adj;
        int len = graph.largest+1;
        this.disc = new int[len];
        this.low = new int[len];
        return this;
    }

    private boolean isBiconnected() {
        int v = adj.keySet().iterator().next();
        if (hasAps(v, v))
            return false;

        // check connectivity
        for (Integer k : adj.keySet()) {
            if (disc[k] == 0)
                return false;       // disconnect graph
        }

        return true;
    }

    private boolean hasAps(int v, int pre) {
        disc[v] = ++times;
        low[v] = disc[v];

        int chdCnt = 0;
        for (Integer chd : adj.get(v)) {
            chdCnt++;
            if (disc[chd] == 0) {
                if (hasAps(chd, v))        // here's also different from dfs in finding APs
                    return true;

                low[v] = Math.min(low[v], low[chd]);

                if ((pre==v && chdCnt>=2) || (pre!=v && low[chd]>= disc[v]))
                    return true;
            } else if (pre != chd) {
                low[v] = Math.min(low[v], disc[chd]);
            }
        }
        return false;
    }

    @Test
    public void case1() {
        int[][] pairs = {{0, 1}};
        assertTrue(init(pairs).isBiconnected());
    }

    @Test
    public void case2() {
        int[][] pairs = {{1, 0}, {0, 2}, {2, 1}, {0, 3}, {3, 4}, {2, 4}};
        assertTrue(init(pairs).isBiconnected());
    }

    @Test
    public void case3() {
        int[][] pairs = {{0, 1}, {1, 2}};
        assertFalse(init(pairs).isBiconnected());
    }

    @Test
    public void case4() {
        int[][] pairs = {{1, 0}, {0, 2}, {2, 1}, {0, 3}, {3, 4}};
        assertFalse(init(pairs).isBiconnected());
    }

    @Test
    public void case5() {
        int[][] pairs = {{0, 1}, {1, 2}, {2, 0}};
        assertTrue(init(pairs).isBiconnected());
    }

}
