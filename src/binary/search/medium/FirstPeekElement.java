package binary.search.medium;

import org.junit.Test;

import static java.lang.System.out;
import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/7/13 13:28
 *
 * 162. Find Peak Element
 *
 * A peak element is an element that is greater than its neighbors.
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 *
 * Example 2:
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 * or index number 5 where the peak element is 6.
 *
 * Note:
 * Your solution should be in logarithmic complexity.
 */
public class FirstPeekElement {

    public int findPeakElement(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int mid;
        while(low<high){
            mid = low + (high-low)/2;
            out.println("low: " + low + ", high: " + high + ", mid: " + mid);
            if(nums[mid]<nums[mid+1]) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    @Test
    public void case1() {
        assertEquals(5, findPeakElement(new int[]{1,2,1,3,5,6,4}));
    }

    @Test
    public void case2() {
        assertEquals(1, findPeakElement(new int[]{1,2,1,8,5,6,4}));
    }
}
