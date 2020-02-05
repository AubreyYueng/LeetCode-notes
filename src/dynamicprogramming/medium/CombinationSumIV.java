package dynamicprogramming.medium;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/2/4 20:26
 * 377. Combination Sum IV
 * https://leetcode.com/problems/combination-sum-iv/
 */
public class CombinationSumIV {

    public int combinationSum4(int[] nums, int target) {
        Map<Integer, Integer> sumCnt = new HashMap<>();
        sumCnt.put(0, 1);       // always be careful about the initialization
        int cnt = 0;

        while (true) {
            for (Map.Entry<Integer, Integer> en : sumCnt.entrySet()) {
                if (en.getKey() == target) cnt+=en.getValue();
            }
            sumCnt.remove(target);
            if (sumCnt.isEmpty()) break;

            Map<Integer, Integer> currSumCnt = new HashMap<>();
            for (Map.Entry<Integer, Integer> en : sumCnt.entrySet()) {
                int s = en.getKey();
                int nCnt = en.getValue();
                for (int n: nums) {
                    int cSum = s + n;
                    if (cSum > target) continue;
                    currSumCnt.put(cSum, nCnt+currSumCnt.getOrDefault(cSum, 0));
                }
            }

            sumCnt = currSumCnt;
//            sumCnt.forEach((k, v) -> System.out.print("("+k+","+v+")"));
//            System.out.println("\n");
        }

        return cnt;
    }

    @Test
    public void case1() {
        assertEquals(7, combinationSum4(new int[]{1,2,3}, 4));
    }

}
