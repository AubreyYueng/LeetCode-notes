package array.hard;

import java.util.HashSet;

/**
 * Created by Yiyun On 2020/6/9 01:19
 *
 * 128. Longest Consecutive Sequence
 */
public class LongestConsecutiveSequence {

    // optimized Brute Force
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int res = 0;

        for (int num : nums) {
            if (set.contains(num-1))
                continue;

            int len = 1;
            int cur = num;
            while (set.contains(++cur)) {
                len++;
            }

            res = Math.max(res, len);
        }

        return res;
    }

}
