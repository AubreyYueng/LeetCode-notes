package _2021.contest._01_09;

import java.util.*;

/**
 * Created by Yiyun On 2021/1/9 22:28
 */
public class _2 {

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        boolean[] valid = new boolean[n];
        List<List<Integer>> expected = new ArrayList<>();
        List<List<Integer>> rule = new ArrayList<>();

        Map<Integer, List<Integer>> tgtIdx = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int t = target[i];
            tgtIdx.putIfAbsent(t, new ArrayList<>());
            tgtIdx.get(t).add(i);
        }
        for (int i = 0; i < n; i++) {
            int s = source[i];
            expected.add(tgtIdx.get(s));
        }
        return 0;

    }

}
