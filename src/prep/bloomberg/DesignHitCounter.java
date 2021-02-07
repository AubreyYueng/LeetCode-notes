package prep.bloomberg;

import org.junit.Test;
import tool.Pair;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2021/2/5 11:59
 *
 * 362. Design Hit Counter
 */
public class DesignHitCounter {

    public static class HitCounter {

        private int total;
        private final LinkedList<Pair<Integer, Integer>> q;   // timestamp, cnt

        /** Initialize your data structure here. */
        public HitCounter() {
            q = new LinkedList<>();
            total = 0;
        }

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            if (!q.isEmpty() && q.getLast().a == timestamp) {
                q.getLast().b += 1;
            } else {
                q.offer(new Pair<>(timestamp, 1));
            }
            total += 1;
            System.out.println(total);
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            int st = timestamp-5 * 60;
            while (!q.isEmpty() && q.peek().a <= st) {
                total -= q.poll().b;
            }
            return total;
        }
    }

    @Test
    /**
     * ["HitCounter","hit","hit","hit","hit","getHits","hit","getHits","hit","getHits"]
     * [[],[1],[1],[1],[300],[300],[300],[300],[301],[301]]
     */
    public void case2() {
        HitCounter hc = new HitCounter();
        hc.hit(1);
        hc.hit(1);
        hc.hit(1);
        hc.hit(300);
        assertEquals(4, hc.getHits(300));
        hc.hit(300);
        assertEquals(5, hc.getHits(300));
        hc.hit(301);
        assertEquals(3, hc.getHits(301));
    }

    @Test
    public void case1() {
        HitCounter hc = new HitCounter();
        hc.hit(1);
        hc.hit(2);
        hc.hit(3);
        assertEquals(3, hc.getHits(4));
        hc.hit(300);
        assertEquals(4, hc.getHits(300));
        assertEquals(3, hc.getHits(301));
    }

}
