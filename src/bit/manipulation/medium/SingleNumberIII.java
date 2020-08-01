package bit.manipulation.medium;

/**
 * Created by Yiyun On 2020/7/30 22:19
 *
 * 260. Single Number III
 */
public class SingleNumberIII {

    // Q: two elements appear only once and all the other elements appear exactly twice.
    // Find the two elements that appear only once.

    // Step1. XOR with bitmask 0 to find diff between the two 'once' num, thus we get diff between x, y.
    // Step2. use x & (-x) to find diff's right most 1-bit: x_mask.
    // Step3. init x = 0, XOR with x if AND x_mask != 0
    // Step4: then y = x XOR diff.
    public int[] singleNumber(int[] nums) {
        int diff = 0;       //  diff between x and y
        for (int num : nums) diff ^= num;

        int x_mask = diff & (-diff);    // i.e. the rightmost 1-bit of diff
        int x = 0;                      // init x
        for (int num : nums) {
            if ((x_mask & num) != 0)    // only XOR with those num that has the same rightmost 1-bit with x
                x ^= num;
        }

        return new int[]{x, x ^ diff};
    }

}
