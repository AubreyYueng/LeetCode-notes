package graph.tarjan;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/2/26 17:34
 *
 * Tarjan’s Algorithm to find Strongly Connected Components(SCC)
 * https://www.geeksforgeeks.org/tarjan-algorithm-find-strongly-connected-components/
 * https://youtu.be/TyWtx7q2D7Y
 *
 * An SCC of a directed graph is a maximal strongly connected sub-graph
 * 强连通分量是图论中的概念。图论中，强连通图指每一个顶点皆可以经由该图上的边抵达其他的每一个点的有向图。意即对于此图上每一个点对，
 * 皆存在路径Vₐ→Vb以及Vb→Vₐ。强连通分量则是指一张有向图G的极大强连通子图G'。如果将每一个强连通分量缩成一个点，则原图G将会变成一张有向无环图。
 *
 * Definition of cross edge:
 *  1. It connects vertices in two **different** DFS-tree
 *  2. or two vertices in the **same** DFS-tree neither of which is the ancestor of the other.
 *
 * Tarjan Algorithm is based on following facts:
 *  1. DFS search produces a DFS tree/forest
 *  2. Strongly Connected Components form subtrees of the DFS tree.
 *  3. If we can find the head of such subtrees, we can print/store all the nodes in that subtree (including head) and that will be one SCC.
 *  4. There is no back edge from one SCC to another (There can be cross edges, but cross edges will not be used while processing the graph).
 */
public class StronglyConnectedComponents {

    private Map<Integer, List<Integer>> adj;
    private int[] low;
    private int[] disc;
    private int times = 0;
    private boolean[] onStack;      // make inquiry about if node is in stack linear time
    private Stack<Integer> stack;
    private List<List<Integer>> scc;

    private void init(DirectedGraph graph) {
        this.adj = graph.adj;
        int len = graph.largest+1;
        this.disc = new int[len];
        this.low = new int[len];
        this.onStack = new boolean[len];
        this.stack = new Stack<>();
        this.scc = null;
    }

    private List<List<Integer>> getScc() {
        this.scc = new LinkedList<>();
        for (Integer v : adj.keySet()) {
            if (disc[v] == 0)   // unvisited
                dfs(v);
        }
        return this.scc;
    }

    // This method follows the pseudo-code on wiki, returning completed scc sub tree
    // https://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm
    private void dfs(Integer v) {
        times++;
        low[v] = times;
        disc[v] = times;
        stack.push(v);
        onStack[v] = true;

        // Be careful: we can't simply "return;" when children is null, because null means we only skip for loop.
        for (Integer u : adj.getOrDefault(v, Collections.emptyList())) {
            if (disc[u] == 0) {  // unvisited
                dfs(u);
                low[v] = Math.min(low[v], low[u]);
            } else if (onStack[u]) {    // if not in stack, means u has been pop(added to scc) and (v, u) is a cross edge
                low[v] = Math.min(low[v], disc[u]);
            }
        }

        if (low[v] == disc[v]) {
            List<Integer> subScc = new LinkedList<>();
            Integer u;
            // Be careful if we use while() {}, we need to do operate one more on v after loop ends.
            do {
                u = stack.pop();
                onStack[u] = false;
                subScc.add(u);
            } while (!v.equals(u));
            scc.add(subScc);
        }
    }

    private static class DirectedGraph {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int largest = Integer.MIN_VALUE;

        static DirectedGraph init(int[]... pairs) {
            DirectedGraph graph = new DirectedGraph();
            for (int[] p : pairs) {
                graph.addEdge(p[0], p[1]);
            }
            return graph;
        }

        void addEdge(int u, int v) {
            adj.putIfAbsent(u, new LinkedList<>());
            adj.get(u).add(v);

            this.largest = Math.max(largest, Math.max(u, v));
        }
    }

    @Test
    public void case1() {
        int[][] pairs = {{1, 0}, {0, 2}, {2, 1}, {0, 3}, {3, 4}};
        verify("[[4], [3], [1, 2, 0]]", pairs);
    }

    @Test
    public void case2() {
        int[][] pairs = {{0, 1}, {1, 2}, {2, 3}};
        verify("[[3], [2], [1], [0]]", pairs);
    }

    @Test
    public void case3() {
        int[][] pairs = {{0, 1}, {1, 2}, {2, 0}, {1, 3}, {1, 4}, {1, 6}, {3, 5}, {4, 5}};
        verify("[[5], [3], [4], [6], [2, 1, 0]]", pairs);
    }

    @Test
    public void case5() {
        int[][] pairs = {{0, 1}, {1, 2}, {2, 3}, {2, 4}, {3, 0}, {4, 2}};
        verify("[[4, 3, 2, 1, 0]]", pairs);
    }

    private void verify(String expected, int[][] pairs) {
        init(DirectedGraph.init(pairs));
        assertEquals(expected, "["+getScc().stream().map(s -> Arrays.toString(s.toArray())).reduce((a, b) -> a + ", " + b).orElse("") + "]");
    }
}
