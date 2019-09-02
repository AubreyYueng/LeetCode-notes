package dynamicprogramming.medium;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/9/1 23:21
 *
 * 91. Decode Ways
 *
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * Example 1:
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 *
 * Example 2:
 * Input: "226"
 * Output: 3
 * Explanation: It could be decodedas "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 * TODO: Be aware of '0'
 */
public class DecodeWays {

    public int numDecodings(String s) {
        if (s == null || s.isEmpty())
            return 0;

        Set<String> validInts = new HashSet<>();
        for (int c = 1; c <= 26; c++) {
            validInts.add(String.valueOf(c));
        }

        // f(0, n) = f(0, n-1) + f(0, n-2)
        int[] sums = new int[s.length()];
        sums[0] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 1; i < s.length(); i++) {
            int sum = s.charAt(i) == '0' ? 0 : sums[i-1];
            String withFormer = s.substring(i-1, i+1);
            if (validInts.contains(withFormer)) {
                sum += (i > 1) ? sums[i-2] : 1;
            }

            sums[i] = sum;
        }
        return sums[sums.length-1];
    }

    @Test
    public void case1() {
        assertEquals(2, numDecodings("12"));
    }

    @Test
    public void case2() {
        assertEquals(3, numDecodings("226"));
    }

    @Test
    public void case3() {
        assertEquals(0, numDecodings("0"));
    }

}
