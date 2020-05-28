package array.medium;

import org.junit.Test;

import java.util.*;

/**
 * Created by Yiyun On 2020/5/27 14:19
 *
 * 56. Merge Intervals
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
//        return mergeInComponent(intervals);
        return mergeBySorting(intervals);
    }

    //  Other than the sort invocation, we do a simple linear scan of the list,
    // so the runtime is dominated by the O(nlgn) complexity of sorting
    public int[][] mergeBySorting(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparing(interval -> interval[0]));
        LinkedList<int[]> res = new LinkedList<>();
        for (int[] interval : intervals) {
            if (res.isEmpty() || interval[0] > res.getLast()[1])
                res.add(interval);
            else
                res.getLast()[1] = Math.max(res.getLast()[1], interval[1]);
        }
        return res.toArray(new int[][]{});
    }

    // Result: Memory Limit Exceeded!!!
    // Here we learn from Connected Components(undirected graph):
    // some nodes are not connected directly but through a third node can they connect.
    private int[][] mergeInComponent(int[][] intervals) {
        ConnectedComponentHelper helper = new ConnectedComponentHelper(intervals);
        List<int[]> res = new LinkedList<>();
        for (List<int[]> component : helper.components) {
            if (component.size() == 1)
                res.add(component.get(0));
            else {
                res.add(new int[]{
                        component.stream().mapToInt(n -> n[0]).min().orElse(0),
                        component.stream().mapToInt(n -> n[1]).max().orElse(0),
                });
            }
        }
        return res.toArray(new int[][]{});
    }

    private static class ConnectedComponentHelper {

        private int[][] intervals;

        private Map<int[], List<int[]>> graph = new HashMap<>();
        private List<List<int[]>> components = new LinkedList<>();
        private Set<int[]> visited = new HashSet<>();

        public ConnectedComponentHelper(int[][] intervals) {
            this.intervals = intervals;
            buildGraph();
            buildComponents();
        }

        // Time: O(n^2)
        private void buildGraph() {
            for (int[] node : intervals) {
                graph.put(node, new LinkedList<>());
            }

            for (int[] a : intervals) {
                for (int[] b : intervals) {
                    if (overlap(a, b)) {
                        graph.get(a).add(b);
                        graph.get(b).add(a);
                    }
                }
            }
        }

        // NOTE: "undirected graph"
        // Trick: here we don't use mutual relationship to avoid redundant adjacent nodes
        private boolean overlap(int[] a, int[] b) {
            return a[0] <= b[1] && b[0] <= a[1];
        }

        private void buildComponents() {
            for (int[] entry : intervals) {
                if (!visited.contains(entry)) {
                    List<int[]> component = new LinkedList<>();
                    components.add(component);
                    dfs(entry, component);
                }
            }
        }

        // TODO: try to implement dfs in iteration style instead of recursion
        private void dfs(int[] head, List<int[]> component) {
            visited.add(head);
            component.add(head);
            for (int[] child : graph.get(head)) {
                if (!visited.contains(child))
                    dfs(child, component);
            }
        }
    }

    @Test
    public void case1() {
        for (int[] interval : merge(new int[][]{{1,3},{2,6},{8,10},{15,18}})) {
            System.out.println(Arrays.toString(interval));
        }
    }
}
