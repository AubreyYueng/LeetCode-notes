package math.easy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/6/23 01:47
 *
 * 7. Reverse Integer
 */
public class ReverseInteger {

    public int reverse(int x) {
        int rest = x;
        int rev = 0;

        while (rest != 0) {
            int tmpRev = rev;

            int tail = rest % 10;
            tmpRev *= 10;
            tmpRev += tail;

            // deal with int overflow
            if ((tmpRev-tail)/10 != rev)
                return 0;

            rev = tmpRev;
            rest /= 10;
        }
        return rev;
    }

    @Test
    public void case1() {
        assertEquals(321, reverse(123));
    }

    @Test
    public void case2() {
        assertEquals(-123, reverse(-321));
    }

    @Test
    public void case3() {
        assertEquals(21, reverse(120));
    }

    @Test
    public void case4() {
        assertEquals(0, reverse(1534236469));
    }

}
