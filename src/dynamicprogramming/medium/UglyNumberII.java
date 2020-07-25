package dynamicprogramming.medium;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/25 17:29
 *
 * 264. Ugly Number II
 */
public class UglyNumberII {

    // Q: Write a program to find the n-th ugly number. Ugly numbers are positive numbers whose prime factors only
    // include 2, 3, 5. And n does not exceed 1690.
    // Codes are learned from LC Solution
    public int nthUglyNumber(int n) {
        int len = 1690;
        int[] nums = new int[len];
        nums[0] = 1;
        int ugly, i2 = 0, i3 = 0, i5 = 0;

        for (int i = 1; i < len; i++) {
            ugly = Math.min(Math.min(nums[i2] * 2, nums[i3] * 3), nums[i5] * 5);
            nums[i] = ugly;

            if (ugly == nums[i2] * 2) ++i2;
            if (ugly == nums[i3] * 3) ++i3;
            if (ugly == nums[i5] * 5) ++i5;
        }

        return nums[n-1];
    }

    @Test
    public void case1() {
        assertEquals(12, nthUglyNumber(10));
    }

}
