package twopointers.medium;

import java.util.Arrays;

/**
 * Created by Yiyun On 2020/5/27 01:19
 *
 * 259. 3Sum Smaller
 */
public class ThreeSumSmaller {

    // binary: O(n^2logn)
    // two pointers: O(n^2)
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int res = 0;
        for (int i = 0; i < len-2; i++) {
            res += twoSumSmallerBinarySearch(i+1, nums, target-nums[i]);
        }
        return res;
    }

    // O(nlogn)
    private int twoSumSmallerBinarySearch(int st, int[] nums, int target) {
        int res = 0;
        for (int i = st; i < nums.length-1 && nums[i] <= (target)/2; i++) {
            res += binarySearch(i, nums, target-nums[i])-i;
        }
        return res;
    }

    // find the max value that less or equal to the target.
    // 最小值最大问题, 用模板二: mid=(l+h+1)/2, check 是用来移动 low 指针的
    private int binarySearch(int st, int[] nums, int target) {
        int l = st;
        int h = nums.length-1;
        while (l < h) {
            int mid = (l+h+1) / 2;
            if (nums[mid] < target)
                l = mid;
            else
                h = mid-1;
        }
        return l;
    }

    // O(n)
    private int twoSumSmallerTwoPointers() {
        return 0;
    }
}
