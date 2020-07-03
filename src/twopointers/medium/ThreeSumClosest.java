package twopointers.medium;

import java.util.Arrays;

/**
 * Created by Yiyun On 2020/7/3 11:12
 *
 * 16. 3Sum Closest
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;   // find the smallest diff
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            int l = i+1;
            int r = len - 1;

            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                int currDiff = target-sum;
                if (Math.abs(currDiff) < Math.abs(diff))
                    diff = currDiff;

                if (sum < target)
                    l++;
                else
                    r--;
            }
        }

        return target-diff;
    }

}
