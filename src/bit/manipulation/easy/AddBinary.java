package bit.manipulation.easy;

/**
 * Created by Yiyun On 2020/7/30 20:32
 *
 * 67. Add Binary
 */
public class AddBinary {

    // Approach 1(can't work on Java, even BigInteger can also lead to overflow)
    // Bit manipulation
    //     a ^ b:        result without carries(XOR)
    //    (a & B) << 1:  carry
    // Approach 2: bit-by-bit computation. Low performance though.
    // Following codes are mostly learned from LC Solution
    public String addBinary(String a, String b) {
        int n = a.length();
        int m = b.length();
        if (n < m) return addBinary(b, a);
        int L = Math.max(n, m);

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int j = m - 1;

        for (int i = L-1; i > -1; i--) {
            if (a.charAt(i) == '1') ++carry;
            if (j > -1 && b.charAt(j--) == '1') ++carry;
            sb.append(carry % 2 == 1 ? '1' : '0');
            carry /= 2;
        }

        if (carry == 1) sb.append('1');
        return sb.reverse().toString();
    }
}
