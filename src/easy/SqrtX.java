package easy;

import org.junit.Test;

import static java.lang.System.out;

/**
 * Created by Yiyun On 2019/7/8 15:39
 *
 * No. 69 Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 *
 * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
 * Example 1:
 * Input: 4
 * Output: 2
 *
 * Example 2:
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.
 *
 * TODO: binary search.
 * TODO: USE long INSTEAD
 * TODO: B_SEARCH PATTERN CAN NOT COVER 0, 1
 */
public class SqrtX {

    public int mySqrt(int x) {
        return (int)sqrt(0, x+1, x);
    }

    private long sqrt(long low, long high, long x) {
        long mid = (low + high) / 2;
        long sqrt = mid * mid;
        out.println("low: " + low + ", high: " + high + ", mid: " + mid + ", sqrt: " + sqrt);

        if (low == mid)
            return mid;
        if (sqrt > x)
            return sqrt(low, mid, x);
        return sqrt(mid, high, x);
    }

    /**
     * Notice: r = original r + 1; refer to sqrt(1) or sqrt(0).
     * +1 is to ensure that the original r to be checked
     */
    private long bSearchPattern(long l, long r) {
        long mid = (l + r) / 2;
        if (l == mid)
            return mid;
        if (check(mid))
            return bSearchPattern(l, mid);
        return bSearchPattern(mid, r);
    }

    private boolean check(long mid) {
        // TODO
        return false;
    }

    @Test
    public void case1() {
        out.println(mySqrt(1));
    }
}
