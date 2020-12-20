package contest._1219;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/12/19 22:06
 */
public class _2 {

    public int maximumUniqueSubarray(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int[] ed = new int[len];
        ed[0] = nums[0];
        map.put(nums[0], 0);
        int res = nums[0];
        int st = 0;

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            Integer idxVal = map.get(num);
            if (idxVal != null && idxVal >= st) {
                st = idxVal + 1;
                for (int j = st; j < i; j++) {
                    ed[j] -= ed[idxVal];
                }
            }

            ed[i] = (i == st ? 0 : ed[i-1]) + num;

            res = Math.max(res, ed[i]);
            map.put(num, i);
//            System.out.println("ed[" + i + "] = " + ed[i]);
        }

        return res;
    }

    @Test
    public void case1() {
        assertEquals(17, maximumUniqueSubarray(new int[]{4, 2, 4, 5, 6}));
    }

    @Test
    public void case2() {
        assertEquals(8   , maximumUniqueSubarray(new int[]{5,2,1,2,5,2,1,2,5}));
    }

    @Test
    public void case3() {
        assertEquals(5027, maximumUniqueSubarray(new int[]{558,508,782,32,187,103,370,607,619,267,984,10}));
    }

    @Test
    public void case4() {
        assertEquals(16911, maximumUniqueSubarray(new int[]{187,470,25,436,538,809,441,167,477,110,275,133,666,345,411,459,490,266,987,965,429,166,809,340,467,318,125,165,809,610,31,585,970,306,42,189,169,743,78,810,70,382,367,490,787,670,476,278,775,673,299,19,893,817,971,458,409,886,434}));
    }

    @Test
    public void case5() {
        assertEquals(10001, maximumUniqueSubarray(new int[]{10000,1,10000,1,1,1,1,1,1}));
    }

}
