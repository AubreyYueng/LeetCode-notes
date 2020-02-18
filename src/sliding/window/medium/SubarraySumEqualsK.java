package sliding.window.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yiyun On 2020/2/17 22:52
 *
 * 560. Subarray Sum Equals K
 * https://leetcode.com/problems/subarray-sum-equals-k/
 */
public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;

        // if we use two-pointers, the time complexity would be O(n^2)
        // One pass: if sum over index[i, j] is equal to k, let S(n) equal to sum of first n elements, then S(j) - S(i) is also equal to k. So we only need to find if there exists sum=currSum-k.
        // Notice that if 0 is in nums, then there could be multiple (i,j) for same sum. So we use Map instead of Set
        int sum = 0;
        int res = 0;
        Map<Integer, Integer> sums = new HashMap<>();
        sums.put(0, 1);
        for(int n: nums) {
            sum+=n;
            Integer cnt = sums.get(sum-k);
            if(cnt != null)
                res+=cnt;
            sums.put(sum, sums.getOrDefault(sum, 0)+1);
        }

        return res;
    }

}
