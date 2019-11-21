package sliding.window.hard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/11/5 00:59
 *
 * 159. Longest Substring with At Most Two Distinct Characters
 *
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 *
 * Example 1:
 * Input: "eceba"
 * Output: 3
 * Explanation: t is "ece" which its length is 3.
 *
 * Example 2:
 * Input: "ccaabbb"
 * Output: 5
 * Explanation: t is "aabbb" which its length is 5.
 *
 * TODO: see 340
 */
public class LongestSubstringwithAtMostTwoDistinctCharacters {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        return new LongestSubstringWithAtMostKDistinctCharacters().lengthOfLongestSubstringKDistinct(s, 2);
    }

    @Test
    public void case1() {
        assertEquals(3, lengthOfLongestSubstringTwoDistinct("eceba"));
    }

    @Test
    public void case2() {
        assertEquals(5, lengthOfLongestSubstringTwoDistinct("ccaabbb"));
    }

}
