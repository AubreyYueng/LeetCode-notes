package dynamicprogramming.hard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/24 22:14
 *
 * 517. Super Washing Machines
 */
public class SuperWashingMachines {

    public int findMinMoves(int[] machines) {
        int res = -1;
        int n = machines.length;
        int total = total(machines);
        if (total % n != 0) return -1;
        int avg = total / n;

        int gain = 0;       // num of clothes that will be passed to next machine
        for (int m : machines) {
            int loss = m-avg;   // when loss < 0, means the number of clothes it needs to receive
            gain += loss;
            // Note!!! It is not Math.abs(loss). Because at each step, giving is unidirectional, while receiving is bidirectional!
            // eg. [-1, 2, -1] needs 2 steps while [1, -2, 1] needs only 1.
            res = Math.max(Math.max(Math.abs(gain), loss), res);
        }
        return res;
    }

    private int total(int[] machines) {
        int total = 0;
        for (int m : machines) {
            total += m;
        }
        return total;
    }

    @Test
    public void case1() {
        assertEquals(3, findMinMoves(new int[]{1, 0, 5}));
    }

    @Test
    public void case2() {
        assertEquals(2, findMinMoves(new int[]{0, 3, 0}));
    }

    @Test
    public void case3() {
        assertEquals(-1, findMinMoves(new int[]{0, 2, 0}));
    }

    @Test
    public void case4() {
        assertEquals(8, findMinMoves(new int[]{0, 0, 11, 5}));
    }

    @Test
    public void case5() {
        assertEquals(4, findMinMoves(new int[]{9, 1, 8, 8, 9}));
    }

}
