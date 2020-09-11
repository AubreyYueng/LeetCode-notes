package dynamicprogramming.medium;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/6/10 11:22
 *
 * 152. Maximum Product Subarray
 */
public class MaximumProductSubarray {

    public int maxProduct_review20200910(int[] nums) {
        return new Helper_20200910(nums).getRes();
    }

    // when it comes to [i], chances are:
    // a. prev+[i], ends at [i]
    // b. [i], starts at [i]
    // c. without [i].
    // two cases:
    // 0: have to restart, update higher score in result
    // neg: flip the largest to smallest, but can be saved by next neg.
    // TYPICAL MISTAKE: a[i+1]=max(a[i],b[i]) + [i], instead we need min and max at the same time
    private static class Helper_20200910 {
        private final int[] nums;
        int n;

        Helper_20200910(int[] nums) {
            this.nums = nums;
            this.n = nums.length;
        }

        private int getRes() {
            if (nums.length == 0) return 0;

            int res = nums[0];
            int min = nums[0];  // min and max when includes [i] in current loop stage
            int max = nums[0];

            for (int i = 1; i < n; i++) {
                int num = nums[i];
                int thisMax = Math.max(num, Math.max(num * min, num * max));
                int thisMin = Math.min(num, Math.min(num * min, num * max));

                min = thisMin;
                max = thisMax;
                res = Math.max(res, max);
            }

            return res;
        }
    }

    // state: minToCurr[len], maxToCurr[len]
    // func: minToCurr[i] = min(minToCurr[i-1]*nums[i], maxToCurr[i-1]*nums[i], nums[i])
    // maxToCurr[i] = max(minToCurr[i-1]*nums[i], maxToCurr[i-1]*nums[i], nums[i])
    // res = max(res, maxToCurr) in every iteration
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int currMax = nums[0];
        int currMin = nums[0];
        int res = currMax;
        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i];
            int[] comp = new int[]{currMax * cur, currMin * cur, cur};
            Arrays.sort(comp);
            currMax = comp[2];
            currMin = comp[0];
            res = Math.max(res, currMax);
        }

        return res;
    }

    // Approach 2 (https://leetcode.com/problems/maximum-product-subarray/discuss/183483/In-Python-it-can-be-more-concise-PythonC%2B%2BJava)
    // Fact: maximum product must start with the first element or end with the last element.
    // Because the number at two end could either be both negative or both positive or 1 positive 1 negative. Thus the
    // result can be the subarray in the middle.
    // Explanation of codes: Calculate prefix product in A. Calculate suffix product in A. Return the max.
    public int maxProduct_1(int[] nums) {
        int n = nums.length, res = nums[0], l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            l =  (l == 0 ? 1 : l) * nums[i];
            r =  (r == 0 ? 1 : r) * nums[n - 1 - i];
            res = Math.max(res, Math.max(l, r));
        }
        return res;
    }

    @Test
    public void case1() {
        assertEquals(6, maxProduct(new int[]{2,3,-2,4}));
    }

    @Test
    public void case2() {
        assertEquals(0, maxProduct(new int[]{-2,0,-1}));
    }

}
