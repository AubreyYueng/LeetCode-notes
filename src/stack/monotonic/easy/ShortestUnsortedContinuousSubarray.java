package stack.monotonic.easy;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/6/13 07:35
 *
 * 581. Shortest Unsorted Continuous Subarray
 */
public class ShortestUnsortedContinuousSubarray {

    // in-place:
    // 1. find the largest/smallest wrong number by detecting the first wrong slope
    // 2. find the correct index for 1's two number
    // 3. return 2's distance
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        boolean flag = false;
        for (int i = 1; i < len; i++) {
            if (!flag && nums[i] < nums[i-1])
                flag = true;
            if (flag)
                min = Math.min(min, nums[i]);
        }

        flag = false;
        for (int i = len-2; i >= 0 ; i--) {
            if (!flag && nums[i] > nums[i+1])
                flag = true;
            if (flag)
                max = Math.max(max,  nums[i]);
        }

        int l = 0, r = len-1;
        for (; l < len; l++) {
            if (min < nums[l])
                break;
        }

        for (; r >= 0; r--) {
            if (max > nums[r])
                break;
        }
        return r-l < 0 ? 0 : r - l + 1;
    }

    // Using stack, but unlike those of finding the distance between currIdx and peekedIdx
    // It's important to figure out what we're actually looking for.
    // Here it is the distance between rightmost and leftmost wrong index
    public int findUnsortedSubarray_stack(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int len = nums.length;

        // finding leftmost wrong index
        int lErr = len;
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                lErr = Math.min(lErr, stack.pop());
            }
            stack.push(i);
        }

        stack.clear();

        // finding rightmost wrong index
        int rErr = 0;
        for (int i = len-1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                rErr = Math.max(rErr, stack.pop());
            }
            stack.push(i);
        }

        // rErr <= lErr means they're still initial value
        return rErr > lErr ? rErr-lErr+1 : 0;
    }

    @Test
    public void case1() {
        assertEquals(5, findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
    }

    @Test
    public void case2() {
        assertEquals(0, findUnsortedSubarray(new int[]{1, 2, 3, 4}));
    }

    @Test
    public void case3() {
        assertEquals(4, findUnsortedSubarray(new int[]{1, 3, 2, 2, 2}));
    }

    @Test
    public void case4() {
        assertEquals(2, findUnsortedSubarray(new int[]{1, 3, 2, 3, 3}));
    }

}
