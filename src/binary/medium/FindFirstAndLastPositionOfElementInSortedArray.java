package binary.medium;

import org.junit.Test;

import java.util.Arrays;

import static java.lang.System.out;
import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun on 2019/7/9 12:03
 *
 * No. 34 Find First and Last Position of Element in Sorted Array
 * Given an array of integers nums sorted in ascending order,
 * find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 *
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0)
            return new int[]{-1, -1};

        int max = findMax(nums, target, 0, nums.length-1);
        if (nums[max] != target)
            return new int[]{-1, -1};
        int min = findMin(nums, target, 0, max);
        return new int[]{min, max};
    }

    private int findMin(int[] nums, int target, int low, int high) {
        while (low < high) {
            int mid = (low + high) / 2;
            out.println("finding max, low: " + low + ", high: " + high + ", midIdx: " + mid);
            if (target > nums[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private int findMax(int[] nums, int target, int low, int high) {
        while (low < high) {
            int mid = (low + high + 1) / 2;
            out.println("finding min, low: " + low + ", high: " + high + ", midIdx: " + mid);
            if (target < nums[mid]) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return low;
    }

    @Test
    public void case1() {
        assertEquals(
                Arrays.toString(new int[]{-1, -1}),
                Arrays.toString((searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)))
        );
    }

    @Test
    public void case2() {
        assertEquals(
                Arrays.toString(new int[]{3, 4}),
                Arrays.toString((searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)))
        );
    }

    @Test
    public void case3() {
        assertEquals(
                Arrays.toString(new int[]{5, 5}),
                Arrays.toString((searchRange(new int[]{5, 7, 7, 8, 8, 10}, 10)))
        );
    }

    @Test
    public void case4() {
        assertEquals(
                Arrays.toString(new int[]{-1, -1}),
                Arrays.toString((searchRange(new int[]{}, 0)))
        );
    }

}
