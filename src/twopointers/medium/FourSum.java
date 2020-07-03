package twopointers.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Yiyun On 2020/7/3 11:55
 *
 * 18. 4Sum
 */
public class FourSum {

    private int len;
    private int[] nums;

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        this.len = nums.length;
        this.nums = nums;
        return kSum(0, 4, target);
    }

    // k for k-sum
    private List<List<Integer>> kSum(int st, int k, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (st == nums.length || nums[st] * k > target || nums[len-1] * k < target)
            return Collections.emptyList();

        if (k == 2)
            return twoSum(target, st);
        for (int i = st; i < len; i++) {
            if (i == st || nums[i-1] != nums[i]) {  // ignore duplicates
                for (List<Integer> subRes : kSum(i + 1, k - 1, target - nums[i])) {
                    List<Integer> currRes = new ArrayList<>(subRes);
                    currRes.add(nums[i]);
                    res.add(currRes);
                }
            }
        }
        return res;
    }

    private List<List<Integer>> twoSum(int target, int st) {
        List<List<Integer>> res = new ArrayList<>();
        int l = st;
        int r = len-1;
        while (l < r) {
            int sum = nums[l] + nums[r];
            // ignore duplicates
            if ( (l > st && nums[l]==nums[l-1]) || sum < target) {
                l++;
            } else if ( (r < len-1 && nums[r]==nums[r+1]) || sum > target) {
                r--;
            } else
                res.add(Arrays.asList(nums[l++], nums[r--]));
        }
        return res;
    }



}
