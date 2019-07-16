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
 * TODO: 2 solutions, see codes demonstrated below
 */
public class MaximumSubarray {

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

    /*--------------- dp solution O--------------*/

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
    public void case1() {
        assertEquals(6, (maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4})));
    }

    @Test
    public void case2() {
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        assertEquals(6, maxSubArraySum(arr, 0, arr.length-1));
    }



}
