package string.medium;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/9/3 05:04
 *
 * 5. Longest Palindromic Substring
 *
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * Example 2:
 * Input: "cbbd"
 * Output: "bb"
 *
 * TODO: remember to revert the index after while ends: see left++ and right-- at the end of 'expand()';
 */
public class LongestPalindromicSubstring {

    /**
     * f(i, i) = true
     * f(i, j) = f(i+1, j-1) && Si==Sj
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return s;

        ExpandHelper longest = new ExpandHelper();
        for (int i = 0; i < s.length(); i++) {
            ExpandHelper h1 = new ExpandHelper(i, i, s);
            ExpandHelper h2 = new ExpandHelper(i, i+1, s);
            ExpandHelper h = h1.distance > h2.distance ? h1 : h2;
            if (h.distance > longest.distance)
                longest = h;
        }
        return s.substring(longest.left, longest.right+1);
    }

    private static class ExpandHelper {
        private int left;
        private int right;
        private String s;
        private int distance;

        ExpandHelper() {
        }

        ExpandHelper(int left, int right, String s) {
            this.left = left;
            this.right = right;
            this.s = s;

            expand();
        }

        private void expand() {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            left++;
            right--;
            this.distance = right-left;
        }
    }

    @Test
    public void case1() {
        assertEquals("bab", longestPalindrome("babad"));
    }

    @Test
    public void case2() {
        assertEquals("bb", longestPalindrome("cbbd"));
    }

}
