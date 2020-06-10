package greedy.hard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/6/10 15:37
 *
 * 45. Jump Game II
 */
public class JumpGameII {

    // Basically, it's to find the max index of every step by iterating through the max of last step
    public int jump(int[] nums) {
        if (nums == null || nums.length <= 0)
            return 0;
        int step = 0;
        int currMax = 0;
        int nextMax = 0;
        int i = 0;
        while (i <= currMax) {    // because currMax will be updated
            while (i <= currMax) {
                nextMax = Math.max(nextMax, i + nums[i]);
                i++;
            }
            step++;
            if (nextMax >= nums.length-1)
                return step;
            currMax = nextMax;
        }
        return 0;
    }

    @Test
    public void case1() {
        assertEquals(2, jump(new int[]{2,3,1,1,4}));
    }

}
