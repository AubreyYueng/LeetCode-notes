package math.medium;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/6/23 02:35
 *
 * 8. String to Integer (atoi)
 */
public class StringToIntegerAtoi {

    // Copied from discussion(but the post is slightly wrong):
    // https://leetcode.com/problems/string-to-integer-atoi/discuss/4643/Java-Solution-with-4-steps-explanations
    // 1. discards all leading whitespaces
    // 2. sign of the number
    // 3. overflow
    // 4. invalid input
    public int myAtoi(String str) {
        int sign = 1;
        int total = 0;
        int i = 0;

        if (str.length() == 0)
            return 0;

        while (i < str.length() && str.charAt(i) == ' ') {
            i++;
        }

        if (i == str.length()) return 0;

        char first = str.charAt(i);
        if (first == '-' || first == '+') {
            sign = str.charAt(i++) == '+' ? 1 : -1;
        }

        while (i < str.length()) {
            int digit = str.charAt(i) - '0';
            if (digit < 0 || digit > 9) break;

            if (Integer.MAX_VALUE / 10 < total || (Integer.MAX_VALUE / 10 == total && Integer.MAX_VALUE % 10 < digit))
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            total = 10 * total + digit;
            i++;
        }

        return total * sign;
    }

    @Test
    public void case1() {
        assertEquals(0, myAtoi(" "));
    }

}
