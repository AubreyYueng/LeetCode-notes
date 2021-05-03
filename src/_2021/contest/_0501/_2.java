package _2021.contest._0501;

import java.util.PriorityQueue;

/**
 * Created by Yiyun On 2021/5/1 10:54
 */
public class _2 {

    private static class SeatManager {

        private PriorityQueue<Integer> q;

        public SeatManager(int n) {
            q = new PriorityQueue<>();
            for (int i = 1; i <= n; i++) {
                q.add(i);
            }
        }

        public int reserve() {
            return q.remove();
        }

        public void unreserve(int seatNumber) {
            q.add(seatNumber);
        }
    }
}
