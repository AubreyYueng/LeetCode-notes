package dynamicprogramming.medium;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/2/4 14:54
 *
 * 494. Target Sum
 * https://leetcode.com/problems/target-sum/
 */
public class TargetSum {

    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0)
            return 0;

        Map<Integer, Integer> res = new HashMap<>();
        res.put(0, 1);  // be careful about the initialization

        for (int curr : nums) {
            Map<Integer, Integer> currRes = new HashMap<>();
            for (Map.Entry<Integer, Integer> en : res.entrySet()) {
                int k = en.getKey();
                int v = en.getValue();
                int s1 = k + curr;
                int cnt1 = v + currRes.getOrDefault(s1, 0);
                currRes.put(s1, cnt1);

                int s2 = k - curr;
                int cnt2 = v + currRes.getOrDefault(s2, 0);
                currRes.put(s2, cnt2);
            }
            res = currRes;
            res.forEach((k, v) -> System.out.print("(" + k + "," + v + ")"));
            System.out.println("\n");
        }

        return res.getOrDefault(S, 0);
    }

    @Test
    public void case1() {
        assertEquals(5, findTargetSumWays(new int[]{1,1,1,1,1}, 3) );
    }

    @Test
    public void case2() {
        assertEquals(2, findTargetSumWays(new int[]{1,0}, 1));
    }

    @Test
    public void case3() {
        assertEquals(256, findTargetSumWays(new int[]{0,0,0,0,0,0,0,0,1}, 1));
    }

}
