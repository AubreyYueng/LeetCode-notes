package sort.medium;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/15 22:04
 *
 * 179. Largest Number
 */
public class LargestNumber {

    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, (a, b) -> (b+a).compareTo(a+b));

        if (strs[0].equals("0"))
            return "0";

        StringBuilder res = new StringBuilder();
        for (String str : strs) {
            res.append(str);
        }
        return res.toString();
    }

    @Test
    public void case1() {
        assertEquals("210", largestNumber(new int[]{10, 2}));
    }

    @Test
    public void case2() {
        assertEquals("9534330", largestNumber(new int[]{3, 30, 34, 5, 9}));
    }

}
