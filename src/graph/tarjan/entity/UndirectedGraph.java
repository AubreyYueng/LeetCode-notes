package graph.tarjan.entity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Yiyun On 2020/2/28 22:34
 */
public class UndirectedGraph {

    public Map<Integer, List<Integer>> adj = new HashMap<>();
    public int largest = Integer.MIN_VALUE;

    public static UndirectedGraph init(int[]... pairs) {
        UndirectedGraph graph = new UndirectedGraph();
        for (int[] p : pairs) {
            graph.addEdge(p[0], p[1]);
        }
        return graph;
    }

    private void addEdge(int v1, int v2) {
        adj.putIfAbsent(v1, new LinkedList<>());
        adj.putIfAbsent(v2, new LinkedList<>());
        adj.get(v1).add(v2);
        adj.get(v2).add(v1);

        this.largest = Math.max(largest, Math.max(v1, v2));
    }
    
}
