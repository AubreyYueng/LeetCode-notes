package graph.tarjan;

import graph.tarjan.entity.UndirectedGraph;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/2/26 17:42
 *
 * Articulation Points(APs) (or Cut Vertices) in a Graph
 * https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
 * https://courses.cs.washington.edu/courses/cse421/04su/slides/artic.pdf
 *
 * 1) u is root of DFS tree and it has at least two children.
 * 2) u is not root of DFS tree and it has a child v such that no vertex in subtree rooted with v has a back edge to
 * one of the ancestors (in DFS tree) of u.
 *
 * 对一个无向图进行深度优先遍历（没有回溯的深搜），经过的边会形成一个树（同一个连通块里）。
 * 原图的边可以分成两类：在这个树上的边和不在这个树上的边。
 * 不在这个树上的边一定是这个树上的反向边，而不是从一个树枝到另一个树枝，因为是在无向图上深搜。
 * 记录每个结点在这个树上的深度（即访问顺序，不是高度），然后low[u]其实是u结点及其以下的所有结点的反向边所指的最“浅”的结点的深度。
 * 如果low[u]小于u的深度，就说明有条反向边“跨”过了u点，那么u点就会在某个环上；否则u就是割点。
 *
 * Time complexity: O(V+E)
 *
 * Time complexity of dfs:
 * 1. if the graph is adjacency matrix (a V x V array): O(V * V)
 * 2. using adjacency lists, the sum of the sizes of the adjacency lists of all the nodes is E (total number of edges).
 *    For a directed graph: O(V) + O(E) = O(V + E)
 *    For a undirected graph: O(V) + O (2E) ~ O(V + E)
 */
public class ArticulationPoints {

    private Map<Integer, List<Integer>> adj;

    /**
     * low[u] = min(disc[u], disc[w]) where w is an ancestor of u and there is a back edge from some descendant of u to w.
     * 所有反向边的后代结点(包括自己)，指向的最小祖先(no)
     */
    private int[] low;
    private int currNo = 0;     // same as 'times' in most tutorial
    private int[] no;           // visit order in dfs tree, same as 'disc[]' in most tutorial
    private Integer[] parent;
    private Set<Integer> aps;   // Articulation Points

    private void init(UndirectedGraph graph) {
        this.adj = graph.adj;
        int len = graph.largest+1;
        this.no = new int[len];
        this.low = new int[len];
        this.parent = new Integer[len];
        this.aps = null;
    }

    private Set<Integer> getAps(Consumer<Integer> dfs) {
        if (this.aps == null) {
            this.aps = new HashSet<>();
            for (Integer v : adj.keySet()) {    // iteration times > 1 when disconnected graph
                if (no[v] == 0)
                    dfs.accept(v);
            }
        }

        return this.aps;
    }

    private void dfs(int v) {
        no[v] = ++currNo;
        low[v] = no[v];

        int chdCnt = 0;
        for (Integer chd : adj.get(v)) {
            chdCnt++;
            if (no[chd] == 0) {   // unvisited
                parent[chd] = v;

                dfs(chd);

                low[v] = Math.min(low[v], low[chd]);
                // be careful about the conditions
                if ((parent[v]==null && chdCnt>=2) || (parent[v]!=null && low[chd]>=no[v]))
                    aps.add(v);
            } else if (!chd.equals(parent[v])) {
                low[v] = Math.min(low[v], no[chd]);
            }
        }
    }

    // Here we don't need to store header(parent[]), we use pre==v to check if it's root(instead of parent[v]==null)
    private void dfs(int v, int pre) {
        no[v] = ++currNo;
        low[v] = no[v];

        int chdCnt = 0;
        for (Integer chd : adj.get(v)) {
            chdCnt++;
            if (no[chd] == 0) {   // unvisited
                dfs(chd, v);

                low[v] = Math.min(low[v], low[chd]);
                // be careful about the conditions
                if ((pre==v && chdCnt>=2) || (pre!=v && low[chd]>=no[v]))
                    aps.add(v);
            } else if (pre != chd) {
                low[v] = Math.min(low[v], no[chd]);
            }
        }
    }

    @Test
    public void case1() {
        int[][] pairs = {{1, 0}, {0, 2}, {0, 3}, {3, 4}};
        verify("[0, 3]", pairs);
    }

    @Test
    public void case2() {
        int[][] pairs = {{0, 1}, {1, 2}, {2, 3}};
        verify("[1, 2]", pairs);
    }

    @Test
    public void case3() {
        int[][] pairs = {{0, 1}, {1, 2}, {2, 0}, {1, 3}, {1, 4}, {1, 6}, {3, 5}, {4, 5}};
        verify("[1]", pairs);
    }


    private void verify(String expected, int[][] pairs) {
        init(UndirectedGraph.init(pairs));
        assertEquals(expected, Arrays.toString(getAps(this::dfs).toArray()));       // use parent[]

        init(UndirectedGraph.init(pairs));
        assertEquals(expected, Arrays.toString(getAps(v -> dfs(v, v)).toArray()));  // use 'pre' instead of parent[]
    }
}
