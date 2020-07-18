package greedy.hard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/17 23:22
 *
 * 316. Remove Duplicate Letters
 */
public class RemoveDuplicateLetters {

    // The following codes are mostly copied from the LC Solution.
    // Approach 1: Greedy - Solving Letter by Letter
    public String removeDuplicateLetters(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;

        // minIdx is the index of smallest letter before iteration ends,
        // and iteration ends when the suffix can't contain every letter in s.
        int minIdx = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(minIdx)) minIdx = i;
            if (--cnt[s.charAt(i)-'a'] == 0) break;
        }

        // Smallest before the smallest suffix that contains every letter, must be the smallest of all,
        // thus we make recursive calls on the suffix.
        if (s.isEmpty()) return "";
        char min = s.charAt(minIdx);
        String suffix = s.substring(minIdx+1);
        return min + removeDuplicateLetters(suffix.replace(String.valueOf(min), ""));
    }

    @Test
    public void case1() {
        assertEquals("abc", removeDuplicateLetters("bcabc"));
    }

    @Test
    public void case2() {
        assertEquals("acdb", removeDuplicateLetters("cbacdcbc"));
    }

}
