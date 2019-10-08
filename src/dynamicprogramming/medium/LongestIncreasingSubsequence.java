package dynamicprogramming.medium;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/10/7 19:42
 *
 * 300. Longest Increasing Subsequence
 *
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * Example:
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * Note:
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 *
 * TODO: O(n log n) solution: https://www.acwing.com/solution/LeetCode/content/287/
 *
 */
public class LongestIncreasingSubsequence {

    /**
     * O(N^2)
     * f(n): max length when contains nums[i]
     * f(n) = Max(f(j))+1 (0 <= j < n)
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return 1;

        int[] maxLengthOfN = new int[nums.length];
        maxLengthOfN[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int prevMaxLength = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && maxLengthOfN[j] > prevMaxLength) {
                    prevMaxLength = maxLengthOfN[j];
                }
            }
            maxLengthOfN[i] = prevMaxLength+1;
        }
        /*for (int i = 0; i < maxLengthOfN.length; i++) {
            System.out.println(i + ": " + maxLengthOfN[i]);
        }*/
        return Arrays.stream(maxLengthOfN).max().getAsInt();
    }

    @Test
    public void case1() {
        assertEquals(4, lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }

    @Test
    public void case2() {
        assertEquals(6, lengthOfLIS(new int[]{1,3,6,7,9,4,10,5,6}));
    }
}
