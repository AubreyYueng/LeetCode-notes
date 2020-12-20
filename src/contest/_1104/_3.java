package contest._1104;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/11/14 22:48
 *
 * Minimum Operations to Reduce X to Zero
 */
public class _3 {

    public int minOperations(int[] nums, int x) {
        int len = nums.length;
        if (len == 1) return x == nums[0] ? 1 : -1;

        LinkedList<Move> queue = new LinkedList<>();
        queue.add(new Move(1, len-1, 1, x-nums[0]));
        queue.add(new Move(0, len-2, 1, x-nums[len-1]));
        while (!queue.isEmpty()) {
            Move m = queue.poll();
            if (m.res < 0)
                continue;

            if (m.res == 0)
                return m.ops;

            if (m.st <= m.ed) {
                if (m.st < len)
                    queue.add(new Move(m.st+1, m.ed, m.ops+1, m.res-nums[m.st]));
                if (m.ed >= 0)
                    queue.add(new Move(m.st, m.ed-1, m.ops+1, m.res-nums[m.ed]));

            }
        }

        return -1;
    }

    private static class Move {
        int st;
        int ed;
        int ops;
        int res;

        private Move(int st, int ed, int ops, int res) {
            this.st = st;
            this.ed = ed;
            this.ops = ops;
            this.res = res;
        }
    }

    @Test
    public void case1() {
        assertEquals(2, minOperations(new int[]{1,1,4,2,3}, 5));
    }

    @Test
    public void case2() {
        assertEquals(-1, minOperations(new int[]{5,6,7,8,9}, 4));
    }

    @Test
    public void case3() {
        assertEquals(5, minOperations(new int[]{3,2,20,1,1,3}, 10));
    }

}
