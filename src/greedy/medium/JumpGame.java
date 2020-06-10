package greedy.medium;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yiyun On 2020/6/10 15:35
 *
 * 55. Jump Game
 */
public class JumpGame {

    // nums[i] means we can arrive at all positions <= i+nums[i],
    // thus we can update the furthest position through iteration between 0 to 'reach'
    // while 'reach' is dynamically changed
    public boolean canJump(int[] nums) {
        int reach = nums[0];
        int len = nums.length;
        for (int i = 0; i < len && i <= reach; i++) {   // starts from 0 in case nums={0} which should return true
            int jumpedIdx = i + nums[i];
            if (jumpedIdx > reach)
                reach = jumpedIdx;
            if (jumpedIdx >= len-1)
                return true;
        }
        return false;
    }

    @Test
    public void case1() {
        assertTrue(canJump(new int[]{2,3,1,1,4}));
    }

    @Test
    public void case2() {
        assertFalse(canJump(new int[]{3,2,1,0,4}));
    }

}
