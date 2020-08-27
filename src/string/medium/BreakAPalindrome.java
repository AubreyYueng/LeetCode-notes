package string.medium;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 8/26/20 23:54
 *
 * 1328. Break a Palindrome
 */
public class BreakAPalindrome {

    public String breakPalindrome(String palindrome) {
        if (palindrome == null || palindrome.length() <= 1)
            return "";

        int n = palindrome.length();
        int i = 0;
        for (; i < n/2; i++) {
            if (palindrome.charAt(i) == 'a')
                continue;
            return replace(palindrome, 'a', i);
        }

        int end = n % 2 == 1 ? i+1 : i;
        for (int j = n-1; j >= end; j--) {
            if (palindrome.charAt(j) == 'a')
                return replace(palindrome, 'b', j);
        }
        return "";
    }

    private static String replace(String original, char c, int index) {
        return original.substring(0, index) + c + original.substring(index+1);
    }

    @Test
    public void case1() {
        assertEquals("aaccba", breakPalindrome("abccba"));
    }

    @Test
    public void case2() {
        assertEquals("ab", breakPalindrome("aa"));
    }

    @Test
    public void case3() {
        assertEquals("", breakPalindrome("a"));
    }

    @Test
    public void case4() {
        assertEquals("abb", breakPalindrome("aba"));
    }

    @Test
    public void case5() {
        assertEquals("aabab", breakPalindrome("aabaa"));
    }

}
