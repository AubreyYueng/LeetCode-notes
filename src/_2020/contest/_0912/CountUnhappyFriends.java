package _2020.contest._0912;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 9/12/20 22:47
 *
 * 1583. Count Unhappy Friends
 */
public class CountUnhappyFriends {

    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        return new Helper(preferences, pairs).getRes();
    }

    private static class Helper {
        int[][] preferences;
        Map<Integer, Integer> pairs;

        private Helper(int[][] preferences, int[][] pairs) {
            this.preferences = preferences;
            this.pairs = new HashMap<>();
            for (int[] p : pairs) {
                this.pairs.put(p[0], p[1]);
                this.pairs.put(p[1], p[0]);
            }
        }

        private int getRes() {
            int res = 0;
            Set<Integer> unhappy = new HashSet<>();
            for (Map.Entry<Integer, Integer> en : pairs.entrySet()) {
                int k = en.getKey();
                int v = en.getValue();

                int[] prefers = preferences[k];
                boolean flag = false;
                for (int pre : prefers) {
                    if (pre == v) break;
                    if (pairs.containsKey(pre) && !unhappy.contains(pre)) {
                        int pairOfPre = pairs.get(pre);
                        int[] prefersOfPre = preferences[pre];
                        for (int preOfPre : prefersOfPre) {
                            if (preOfPre == pairOfPre) break;
                            if (preOfPre == k) {
                                if (!unhappy.contains(pre)) {
                                    res++;
                                    unhappy.add(pre);
                                }
                                flag = true;
                                break;
                            }
                        }
                    }
                }
                if (flag && !unhappy.contains(k)) {
                    res++;
                    unhappy.add(k);
                }
            }

            return res;
        }
    }

    @Test
    public void case1() {
        // n = 4, preferences = [[1, 2, 3], [3, 2, 0], [3, 1, 0], [1, 2, 0]], pairs = [[0, 1], [2, 3]]
        assertEquals(2, unhappyFriends(4, new int[][]{
                {1, 2, 3},
                {3, 2, 0},
                {3, 1, 0},
                {1, 2, 0}
        }, new int[][]{
                {0, 1},
                {2, 3}
        }));
    }

    @Test
    public void case2() {
        // n = 4, preferences = [[1, 3, 2], [2, 3, 0], [1, 3, 0], [0, 2, 1]], pairs = [[1, 3], [0, 2]]
        assertEquals(4, unhappyFriends(4, new int[][]{
                {1, 3, 2},
                {2, 3, 0},
                {1, 3, 0},
                {0, 2, 1}
        }, new int[][]{
                {1, 3},
                {0, 2}
        }));
    }

    @Test
    public void case3() {
        assertEquals(5, unhappyFriends(4, new int[][]
                {{1,4,3,2,5},{0,5,4,3,2},{3,0,1,5,4},{2,1,4,0,5},{2,1,0,3,5},{3,4,2,0,1}},
                new int[][]{
                {3, 1},
                {2, 0},
                {5, 4}
        }));
    }

}
