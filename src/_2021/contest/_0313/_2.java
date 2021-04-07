package _2021.contest._0313;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yiyun On 2021/3/13 22:48
 * Find Center of Star Graph
 */
public class _2 {

    public int findCenter(int[][] edges) {
        if (edges.length == 1)
            return edges[0][0];
        Set<Integer> set = new HashSet<>();
        for (int i : edges[0]) {
            set.add(i);
        }
        int res = 0;
        for (int i : edges[1]) {
            if (set.contains(i)) {
                res = i;
                break;
            }
        }
        return res;
    }

}
