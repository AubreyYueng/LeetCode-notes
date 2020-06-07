package twopointers.sliding.window.medium;

/**
 * Created by Yiyun On 2020/2/17 22:09
 *
 * 209. Minimum Size Subarray Sum
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 */
public class MinimumSizeSubarraySum {

    public int minSubArrayLen(int s, int[] nums) {
        // two pointers
        // space O(1)
        // time complexity is O(n): each element can be visited at most twice(once by left & once by right)
        int len = nums.length;
        int res = len+1;
        int l = 0;
        int sum = 0;
        for (int r = 0; r < len; r++) {
            sum+=nums[r];
            while (sum >= s) {
                sum-=nums[l];
                res = Math.min(res, r-l+1);
                l++;
            }
        }
        return (res == len+1) ? 0 : res;
    }

}
