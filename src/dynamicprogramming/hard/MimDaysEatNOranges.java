package dynamicprogramming.hard;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 9/12/20 21:23
 *
 * 1553. Minimum Number of Days to Eat N Oranges
 */
public class MimDaysEatNOranges {

    public int minDays(int n) {
        if (n == 0) return 0;
        return new Helper().minDays(n);
    }

    private static class Helper {
        Map<Integer, Integer> dp;   // we can't use int[] because the test case could be exceed its max length.

        private Helper() {
            this.dp = new HashMap<>();
            dp.put(0, 0);
            dp.put(1, 1);
        }

        private int minDays(int n) {
            if (!dp.containsKey(n))
                dp.put(n, 1 + Math.min(n % 2 + minDays(n / 2), n % 3 + minDays(n / 3)));
            return dp.get(n);
        }
    }

    @Test
    public void case1() {
        assertEquals(4, minDays(10));
    }

    @Test
    public void case2() {
        assertEquals(3, minDays(6));
    }

    @Test
    public void case3() {
        assertEquals(1, minDays(1));
    }

    @Test
    public void case4() {
        assertEquals(6, minDays(56));
    }

    @Test
    public void case5() {
        assertEquals(4, minDays(5));
    }

    @Test
    public void case6() {
        assertEquals(8, minDays(182));
    }

    @Test
    public void case7() {
        assertEquals(32, minDays(638826798));
    }

}
