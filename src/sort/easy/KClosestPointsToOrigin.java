package sort.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Yiyun On 8/28/20 17:20
 *
 * 973. K Closest Points to Origin
 */
public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int K) {
        Helper h = new Helper(points, K);
        h.quickSelect(0, points.length-1);
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            res[i] = h.points[i];
        }
        return res;
    }

    private static class Helper {
        int[][] points;
        int K;
        Map<String, Long> distMap = new HashMap<>();

        private Helper(int[][] points, int K) {
            this.points = points;
            this.K = K;
            for (int[] p : points) {
                distMap.put(p[0] + "-" + p[1], (long)p[0]*p[0] + p[1]*p[1]);
            }
        }

        private long getDist(int[] p) {
            return distMap.get(p[0] + "-" + p[1]);
        }

        private void quickSelect(int st, int ed) {
            if (st >= ed) return;

            int pivot = partition(st, ed);
            if (pivot < K) quickSelect(pivot+1, ed);
            else if (pivot > K) quickSelect(st, pivot-1);
        }

        private int partition(int st, int ed) {
            int rand = st + new Random().nextInt(ed-st+1);
            long randP = getDist(points[rand]);
            swap(rand, ed);

            int storedIdx = st;
            for (int i = st; i < ed; i++) {
                if (getDist(points[i]) < randP)
                    swap(storedIdx++, i);
            }
            swap(storedIdx, ed);
            return storedIdx;
        }

        private void swap(int i, int j) {
            int[] tmp = points[i];
            points[i] = points[j];
            points[j] = tmp;
        }
    }

}
