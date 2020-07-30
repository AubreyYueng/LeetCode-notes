package bit.manipulation.easy;

/**
 * Created by Yiyun On 2020/7/29 20:13
 *
 * 190. Reverse Bits
 */
public class ReverseBits {

    // Q: Reverse bits of a given 32 bits unsigned integer.
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            int lsb = n & 1;    // least significant bit
            res += lsb;         // here we can also write as "res |= lsb"
            n >>= 1;
        }
        return res;
    }

}
