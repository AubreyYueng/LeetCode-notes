package string.easy;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yiyun On 2019/11/21 22:19
 *
 * 202. Happy Number
 *
 * Write an algorithm to determine if a number is "happy".
 * A happy number is a number defined by the following process: Starting with any positive integer, replace the number
 * by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it
 * loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
 *
 * Example:
 * Input: 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */
public class HappyNumber {

    public boolean isHappy(int n) {
        List<Integer> digits = digits(n);
        Set<Integer> resSet = new HashSet<>();
        resSet.add(n);
        int res = 0;
        while (res != 1) {
            res = digits.stream().mapToInt(d -> d*d).sum();
            if (resSet.contains(res))
                break;

            resSet.add(res);
            digits = digits(res);
        }
        return res == 1;
    }

    private List<Integer> digits(int n) {
        LinkedList<Integer> digits = new LinkedList<>();
        while (n >= 10) {
            digits.addFirst(n % 10);
            n /= 10;
        }
        digits.addFirst(n);
        return digits;
    }

    @Test
    public void testDigits() {
        System.out.println(digits(100));
        System.out.println(digits(101));
        System.out.println(digits(19));
        System.out.println(digits(9));
        System.out.println(digits(135));
    }

    @Test
    public void case1() {
        assertTrue(isHappy(19));
    }

    @Test
    public void case2() {
        assertFalse(isHappy(2));
    }

}
