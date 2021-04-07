package _2021.contest._0313;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2021/3/13 22:57
 * Maximum Score of a Good Subarray
 */
public class _4 {

    public int maximumScore(int[] nums, int k) {
        int[] left = new int[k+1];
        left[0] = nums[k];
        for (int i = 1; i < k+1; i++) {
            left[i] = Math.min(left[i-1], nums[k-i]);
        }
        int[] right = new int[nums.length-k];
        right[0] = nums[k];
        for (int i = 1; i < nums.length-k; i++) {
            System.out.println(i);
            right[i] = Math.min(right[i-1], nums[k+i]);
        }

        int res = nums[k];
        for (int i = 0; i < left.length; i++) {
            for (int j = 0; j < right.length; j++) {
                int score = Math.min(nums[i], nums[j]) * (i+j+1);
                res = Math.max(score, res);
            }
        }
        return res;
    }

    @Test
    public void case1() {
        assertEquals(15, maximumScore(new int[]{1,4,3,7,4,5}, 3));
    }

}
