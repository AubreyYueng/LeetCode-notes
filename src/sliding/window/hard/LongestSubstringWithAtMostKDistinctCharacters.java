package sliding.window.hard;

import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/11/5 00:41
 *
 * 340. Longest Substring with At Most K Distinct Characters
 *
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 *
 * Example 1:
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: T is "ece" which its length is 3.
 *
 * Example 2:
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: T is "aa" which its length is 2.
 *
 * TODO: beware of the del index(should be min of the rightmost)
 * TODO: and also need to remove delCh both in curr-set and rightmost-map
 * TODO: watch out when k==0
 */
public class LongestSubstringWithAtMostKDistinctCharacters {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) return 0;

        Map<Character, Integer> rightMost = new HashMap<>();
        Set<Character> currSubStr = new HashSet<>();
        int max = 0;
        int currLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            rightMost.put(curr, i);
            if (currSubStr.contains(curr)) {
                currLen++;
            } else {
                if (currSubStr.size() < k) {
                    currLen++;
                    currSubStr.add(curr);
                } else {
                    int del = Collections.min(rightMost.values());
                    char delCh = s.charAt(del);
                    currSubStr.remove(delCh);
                    rightMost.remove(delCh);

                    currLen = i-del;
                    currSubStr.add(curr);
                }
            }

            max = Math.max(max, currLen);
            System.out.println();
        }
        return max;
    }

    @Test
    public void case1() {
        assertEquals(3, lengthOfLongestSubstringKDistinct("eceba", 2));
    }

    @Test
    public void case2() {
        assertEquals(2, lengthOfLongestSubstringKDistinct("aa", 1));
    }

    @Test
    public void case3() {
        assertEquals(3, lengthOfLongestSubstringKDistinct("eceba", 2));
    }

    @Test
    public void case4() {
        assertEquals(5, lengthOfLongestSubstringKDistinct("ccaabbb", 2));
    }

    @Test
    public void case5() {
        assertEquals(4, lengthOfLongestSubstringKDistinct("abaccc", 2));
    }

    @Test
    public void case6() {
        assertEquals(3, lengthOfLongestSubstringKDistinct("cdaba", 2));
    }

    @Test
    public void case7() {
        assertEquals(0, lengthOfLongestSubstringKDistinct("aa", 0));
    }
}
