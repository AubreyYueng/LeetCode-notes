package math.easy;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yiyun On 2020/6/23 04:42
 *
 * 9. Palindrome Number
 */
public class PalindromeNumber {

    // Require: solve it without converting the integer to a string
    // Solution: revert the number itself, and then compare with the original number
    public boolean isPalindrome(int x) {
        // when last digit is 0, or negative
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;

        int rest = x;
        int res = 0;
        // if the condition is "rest != 0", then we need to beware of "overflow"
        // But we don't need to reverse the whole digits
        while (res < rest) {
            res = res * 10 + rest % 10;
            rest /= 10;
        }

        return res == rest || res / 10 == rest;
    }

    @Test
    public void case1() {
        assertTrue(isPalindrome(121));
    }

    @Test
    public void case2() {
        assertFalse(isPalindrome(-121));
    }

    @Test
    public void case3() {
        assertFalse(isPalindrome(10));
    }

}
