package binary.search.medium;

import org.junit.Test;

import static java.lang.System.out;
import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/7/12 23:22
 *
 * 153. Find Minimum in Rotated Sorted Array
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 *
 * Example 1:
 * Input: [3,4,5,1,2]
 * Output: 1
 *
 * Example 2:
 * Input: [4,5,6,7,0,1,2]
 * Output: 0
 *
 * TODO: 1. figure out the meaning of rotated: eg.[4,5,6,7,8,0,1,9] is NOT rotated!
 */
public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length-1;
        while (low < high) {
            int mid = (low + high) / 2;
            out.println("low: " + low + ", high: " + high + ", mid: " + mid);
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return nums[low];
    }

    @Test
    public void case1() {
        assertEquals(0, findMin(new int[]{4,5,6,7,8,0,1,2}));
    }

    @Test
    public void case2() {
        assertEquals(1, findMin(new int[]{4,5,6,7,1,3}));
    }

    @Test
    public void case3() {
        assertEquals(1, findMin(new int[]{1}));
    }



}
