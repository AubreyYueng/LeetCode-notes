package binary.search.medium;

import org.junit.Test;

import static java.lang.System.out;
import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/7/13 00:44
 *
 * 33. Search in Rotated Sorted Array
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * Your algorithm's runtime complexity must be in the order of O(log n).

 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * TODO: 1. finding closely on left(eg. maxIdx): it is like set high fixed, adjust low = mid, so it has to be(mid-1, mid)
 * TODO: 2. finding closely on right: see class FindMinimumInRotatedSortedArray
 * TODO: 3. Notice indexOutOfBoundException when [low = maxIdx + 1;]
 */
public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;

        int low;
        int high;
        int maxIdx = findMaxIdx(nums);
        if (nums[0] > target){
            low = maxIdx + 1;
            high = nums.length - 1;
        } else {
            low = 0;
            high = maxIdx;
        }

        if (low >= nums.length)
            low = nums.length-1;

        while (low < high) {
            int mid = (low + high + 1) / 2;
//            out.println("low: " + low + ", high: " + high + ", mid: " + mid);
            if (target < nums[mid])
                high = mid - 1;
            else
                low = mid;
        }
        return nums[low] == target ? low : -1;
    }

    private int findMaxIdx(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = (low + high + 1) / 2;
//            out.println("low: " + low + ", high: " + high + ", mid: " + mid);
            if (nums[mid] > nums[low])
                low = mid;
            else
                high = mid - 1;
        }

        return low;
    }

    @Test
    public void case1() {
        assertEquals(4, search(new int[]{4,5,6,7,0,1,2}, 0));
    }

    @Test
    public void case2() {
        assertEquals(-1, search(new int[]{4,5,6,7,0,1,2}, 3));
    }

    @Test
    public void case3() {
        assertEquals(-1, search(new int[]{1}, 0));
    }

    @Test
    public void case4() {
        assertEquals(-1, search(new int[]{1,3}, 0));
    }


}
