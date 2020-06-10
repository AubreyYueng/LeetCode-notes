package stack.monotonic.medium;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/6/9 19:50
 *
 * 739. Daily Temperatures
 */
public class DailyTemperatures {

    // Implement through monotonically decreasing stack
    public int[] dailyTemperatures(int[] T) {
        int len = T.length;
        int[] res = new int[len];

        LinkedList<Integer> descStack = new LinkedList<>();
        for (int i = 0; i < T.length; i++) {
            int curr = T[i];
            while (!descStack.isEmpty() && curr > T[descStack.peek()]) {
                int poppedIdx = descStack.pop();
                res[poppedIdx] = i-poppedIdx;
            }

            descStack.push(i);
        }

        return res;
    }

    @Test
    public void case1() {
        assertEquals("[1, 1, 4, 2, 1, 1, 0, 0]",
                Arrays.toString(dailyTemperatures(new int[]{73,74,75,71,69,72,76,73})));
    }

}
