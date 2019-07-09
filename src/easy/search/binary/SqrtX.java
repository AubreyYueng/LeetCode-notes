package easy.search.binary;

import org.junit.Test;

import static java.lang.System.out;
import static org.junit.Assert.assertEquals;

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
 * TODO: refer to the binary search patterns below;
 * TODO: Sqrt can only be applied to [l, mid - 1] & [mid, r]
 * TODO: USE long INSTEAD
 */
public class SqrtX {

    public int mySqrt(int x) {
        return (int)sqrt(0, x, x);
    }

    /**
     * TODO: to ensure 'high' is check, x = original x + 1;
     */
    private long sqrt(long low, long high, long x) {
        long mid = (low + high) / 2;
        long sqrt = mid * mid;
        out.println("low: " + low + ", high: " + high + ", mid: " + mid + ", sqrt: " + sqrt);

        if (high == mid)
            return mid;
        if (sqrt > x)
            return sqrt(low, mid, x);
        return sqrt(mid, high, x);
    }

    /**
     * TODO: [l, mid] & [mid + 1, r]
     */
    public int pattern_1(int x) {
        int low = 0;
        int high = x;
        while(low < high) {
            int mid = (low + high) / 2;
            out.println("low: " + low + ", high: " + high + ", mid: " + mid + ", sqrt: " + mid*mid);
            if (mid * mid >= x) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * TODO: [l, mid - 1] & [mid, r]; mid = actual_mid + 1
     */
    public int pattern_2(int x) {
        int low = 0;
        int high = x;
        while(low < high) {
            int mid = (low + high) / 2 + 1;
            out.println("low: " + low + ", high: " + high + ", mid: " + mid + ", sqrt: " + mid*mid);
            if (mid * mid >= x) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return low;
    }

    @Test
    public void case1() {
        out.println(mySqrt(18));
    }

    @Test
    public void case2() {
        assertEquals(4, pattern_2(18));
    }

    @Test
    public void case3() {
        assertEquals(5, pattern_1(18));      // wrong: should be 4
    }
}
