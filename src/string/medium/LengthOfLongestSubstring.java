package string.medium;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/9/3 22:01
 *
 * 3. Longest Substring Without Repeating Characters
 *
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * TODO: NOTE that window don't step back, so that's why '&& formerPos >= leftIdx', or we can use Math.max(l1, l2)
 */
public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int longest = 0;
        int left = 0;
        Map<Character, Integer> posMap = new HashMap<>();

        int length = s.length();
        for (int right = 0; right < length; right++) {
            Character ch = s.charAt(right);
            Integer formerPos = posMap.get(ch);
            posMap.put(ch, right);

            if (formerPos != null && formerPos >= left)
                left = formerPos + 1;

            longest = Math.max(right-left+1, longest);
        }

        return longest;
    }

    @Test
    public void case1() {
        assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
    }

    @Test
    public void case2() {
        assertEquals(1, lengthOfLongestSubstring("bbbbb"));
    }

    @Test
    public void case3() {
        assertEquals(3, lengthOfLongestSubstring("pwwkew"));
    }

    @Test
    public void case4() {
        assertEquals(2, lengthOfLongestSubstring("abba"));
    }

}
