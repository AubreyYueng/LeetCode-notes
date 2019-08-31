package dynamicprogramming.easy;

import divide.conquer.easy.MaximumSubarray_DivideConquer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/8/30 06:10
 *
 * 53. Maximum Subarray
 *
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum
 * and return its sum.
 *
 * Example:
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 * Follow up:
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach,
 * which is more subtle.
 *
 * TODO: divide & conquer solution see {@link divide.conquer.easy.MaximumSubarray_DivideConquer}
 *
 * (动态规划) O(n)
 * 1. 设 f(i) 表示以第 i 个数字为结尾的最大连续子序列的 和 是多少。
 * 2. 初始化 f(0) = nums[0]。
 * 3. 转移方程f(i) = max(f(i−1)+nums[i], nums[i])。可以理解为当前有两种决策，一种是将第 i 个数字和前边的数字拼接起来；另一种是第 i 个数
 *    字单独作为一个新的子序列的开始。
 * 4. 最终答案为 ans = max(f(k)), 0 ≤ k < n。
 * 时间复杂度
 * 状态数为 O(n)，决策数为 O(1)，故总时间复杂度为 O(n)。
 *
 */
public class MaximumSubarray_DP {

    /*-------------- regular solution ------------*/
    public int maxSubArray(int[] nums) {
//        out.println("nums: " + Arrays.toString(nums));

        int maxSum = nums[0];
        int maxCurrent = nums[0];
        for(int i=1; i<nums.length; i++) {
//            out.println("maxSum: " + maxSum + ", maxCurrent: " + maxCurrent);

            maxCurrent = Math.max(nums[i], maxCurrent+nums[i]);
//            out.println("maxCurrent: " + maxCurrent);
            if(maxCurrent>maxSum) {
                maxSum = maxCurrent;
            }
        }
//        out.println("maxSum: " + maxSum + ", maxCurrent: " + maxCurrent);
        return maxSum;
    }

    @Test
    public void case1() {
        assertEquals(6, maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }

}
