package contest._01_16;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Yiyun On 2021/1/16 21:51
 */
public class _1 {

    public int countGoodRectangles(int[][] rectangles) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int res = 0;
        int largest = -1;
        for (int[] rec : rectangles) {
            int len = Math.min(rec[0], rec[1]);
            pq.add(len);
            if (pq.peek() != largest) {
                largest = pq.peek();
                res = 0;
            }
            if (pq.peek() == len) {
                res += 1;
            }
        }
        return res;
    }

}
