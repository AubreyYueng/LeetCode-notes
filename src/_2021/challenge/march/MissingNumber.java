package _2021.challenge.march;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2021/3/4 01:30
 */
public class MissingNumber {

    public int missingNumber(int[] nums) {
        boolean zeroFlip = false;
        for (int n: nums) {
            if (n == 0) {
                continue;
            }
            int i = Math.abs(n)-1;
            nums[i] = -nums[i];
            zeroFlip = (zeroFlip || nums[i] == 0);
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 || (nums[i] == 0 && zeroFlip))
                continue;
            return i+1;
        }
        return 0;
    }

    @Test
    public void case1() {
        assertEquals(8, missingNumber(new int[]{9,6,4,2,3,5,7,0,1}));
    }

    @Test
    public void case2() {
        assertEquals(3, missingNumber(new int[]{0,1,2}));
    }
}
