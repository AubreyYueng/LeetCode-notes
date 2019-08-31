package divide.conquer.easy;

import org.junit.Test;

import static java.lang.System.out;
import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/7/15 04:06
 *
 * 53. Maximum Subarray
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest
 * sum and return its sum.
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
 * TODO: dynamic programming solution see {@link dynamicprogramming.easy.MaximumSubarray_DP}
 *
 * (分治) O(nlogn)
 * 1. 考虑区间 [l, r] 内的答案如何计算，令 mid = (l + r) / 2，则该区间的答案由三部分取最大值，分别是：
 *  (1). 区间 [l, mid] 内的答案（递归计算）。
 *  (2). 区间 [mid + 1, r] 内的答案（递归计算）。
 *  (3). 跨越 mid和mid+1 的连续子序列。
 * 2. 其中，第(3)部分只需要从 mid 开始向 l 找连续的最大值，以及从 mid+1 开始向 r 找最大值即可，在线性时间内可以完成。
 * 3. 递归终止条件显然是 l==r ，此时直接返回 nums[l]。
 * 时间复杂度
 * 由递归主定理 S(n)=2S(n/2)+O(n)，解出总时间复杂度为 O(nlogn)。
 * 或者每一层时间复杂度是 O(n)，总共有 O(logn) 层，故总时间复杂度是 O(nlogn)。
 */
public class MaximumSubarray_DivideConquer {

    /**
     * recurrence : T(n) = 2T(n/2) + O(n);
     * Time Complexity: O(n log n)
     */

    // Find the maximum possible sum in arr[]
    // such that arr[m] is part of it
    public static int maxCrossingSum(int arr[], int low,
                              int mid, int high) {
        // Include elements on left of mid.
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        for (int i = mid; i >= low; i--) {
            sum = sum + arr[i];
            if (sum > leftSum)
                leftSum = sum;
        }

        // Include elements on right of mid
        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= high; i++) {
            sum = sum + arr[i];
            if (sum > rightSum)
                rightSum = sum;
        }

        // Return sum of elements on left
        // and right of mid
        return leftSum + rightSum;
    }

    // Returns sum of maxium sum subarray
    // in aa[l..h]
    public static int maxSubArraySum(int arr[], int low,
                              int high) {
        // Base Case: Only one element
        if (low == high)
            return arr[low];
        int mid = (low + high)/2;
        return Math.max(Math.max(maxSubArraySum(arr, low, mid),
                maxSubArraySum(arr, mid+1, high)),
                maxCrossingSum(arr, low, mid, high));
    }

    @Test
    public void case2() {
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        assertEquals(6, maxSubArraySum(arr, 0, arr.length-1));
    }



}
