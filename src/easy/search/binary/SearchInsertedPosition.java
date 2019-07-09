package easy.search.binary;

import org.junit.Test;

import static java.lang.System.out;
import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun on 2019/7/9 12:01
 *
 * No. 35 Search Insert Position
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 *
 * Example 1:
 * Input: [1,3,5,6], 5
 * Output: 2
 *
 * Example 2:
 * Input: [1,3,5,6], 2
 * Output: 1
 *
 * Example 3:
 * Input: [1,3,5,6], 7
 * Output: 4
 *
 * Example 4:
 * Input: [1,3,5,6], 0
 * Output: 0
 *
 * TODO: unlike Sqrt returns the lowIdx, here returns highIdx
 */
public class SearchInsertedPosition {

    public int searchInsert(int[] nums, int target) {
        if (target <= nums[0])
            return 0;
        return search_1(0, nums.length, nums, target);
    }

    private int search_1(int lowIdx, int highIdx, int[] nums, int target) {
        int mid = (lowIdx + highIdx) / 2;
        out.println("low: " + lowIdx + ", high: " + highIdx + ", mid: " + mid);
        if (lowIdx == mid)
            return highIdx;

        if (nums[mid] < target)
            return search_1(mid, highIdx, nums, target);
        return search_1(lowIdx, mid, nums, target);
    }

    private int pattern_1(int[] nums, int target) {
        int lowIdx = 0;
        int highIdx = nums.length;
        while (lowIdx < highIdx) {
            int mid = (lowIdx + highIdx) / 2;
            out.println("start low: " + lowIdx + ", high: " + highIdx + ", mid: " + mid);
            if(nums[mid] >= target)
                highIdx = mid;
            else
                lowIdx = mid+1;
            out.println("end low: " + lowIdx + ", high: " + highIdx);
        }

        return lowIdx;
    }

    @Test
    public void case1() {
        int[] nums = new int[]{1, 3, 5, 6};
        assertEquals(2, search_1(0, nums.length, nums, 5));
    }

    @Test
    public void case2() {
        out.println(pattern_1(new int[]{1, 3, 5, 6}, 4));
    }

}
