package bit.manipulation.easy;

/**
 * Created by Yiyun On 2020/8/1 14:16
 *
 * 231. Power of Two
 */
public class PowerOfTwo {

    // Approach 1: get the rightmost 1-bit. (similar trick applied on 260. Single Number III)
    // Approach 2: set the rightmost 1-bit to 0: x & (x-1)
    public boolean isPowerOfTwo_1(int n) {
        if (n == 0) return false;
        long x = (long)n;
        return (x & (-x)) == x;
    }

    public boolean isPowerOfTwo_2(int n) {
        if (n == 0) return false;
        long x = (long)n;
        return (x & (x-1)) == 0;
    }

}
