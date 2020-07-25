package bit.manipulation.easy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/25 18:17
 *
 * 461. Hamming Distance
 */
public class HammingDistance {

    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    public int hammingDistance_bitshift(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0) {
            if (xor % 2 == 1)
                distance += 1;
            xor = xor >> 1;
        }
        return distance;
    }

    @Test
    public void case1() {
        assertEquals(2, hammingDistance(1, 4));
    }

}
