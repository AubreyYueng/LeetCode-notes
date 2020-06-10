package bit.manipulation.easy;

/**
 * Created by Yiyun On 2020/6/9 20:20
 *
 * 136. Single Number
 */
public class SingleNumber {

    // This question requires in-place operation.
    // Useful technique: a^b == b^a and that a^a == 0
    public int singleNumber(int[] nums) {
        int a = 0;
        for (int i : nums) {
            a ^= i;
        }
        return a;
    }

}
