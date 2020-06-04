package array.hard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/5/28 22:27
 *
 * 41. First Missing Positive
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        int len = nums.length;

        // we use in-space flag by negating the numbers
        // so we need to pre-process negatives and zeros
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0)
                nums[i] = len+1;
        }

        // flag the numbers' existence by negating numbers in related index
        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            if (num > len)
                continue;

            int flag = nums[num-1];
            if (flag > 0)
                nums[num-1] *= -1;
        }

        // if the number is still positive, means the "index" is not in the original array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                return i+1;
        }

        return len+1;
    }

    @Test
    public void case1() {
        assertEquals(3, firstMissingPositive(new int[]{1, 2, 0}));
    }

}
