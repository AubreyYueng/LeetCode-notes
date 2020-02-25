package graph.medium;

import graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Yiyun On 2020/2/24 18:31
 * 133. Clone Graph
 * https://leetcode.com/problems/clone-graph/solution/
 */
public class CloneGraph {

    public Node cloneGraph(Node node) {
        if (node == null)
            return null;
        if (node.neighbors == null)
            return new Node(node.val, null);

        // next time we can try DFS search
        // here we use BFS
        Map<Node, Node> visited = new HashMap<>();  // original -> new
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);
        visited.put(node, new Node(node.val, new ArrayList<>()));
        while(!queue.isEmpty()) {
            Node n = queue.remove();
            for(Node neigh: n.neighbors) {
                if (!visited.containsKey(neigh)) {
                    visited.put(neigh, new Node(neigh.val, new ArrayList<>()));
                    queue.add(neigh);
                }
                visited.get(n).neighbors.add(visited.get(neigh));
            }
        }
        return visited.get(node);
    }

}
