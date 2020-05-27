package array.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yiyun On 2020/5/26 22:22
 *
 * 1. Two Sum
 */
public class TwoSum {

    // time: O(N)
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();    // value -> index
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int complement = target-num;
            if (map.containsKey(complement))
                return new int[]{i, map.get(complement)};

            map.put(num, i);
        }

        return null;
    }

}
