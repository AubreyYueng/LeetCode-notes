package binary.search.medium;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/11/7 15:06
 *
 * Longest Repeating Substring
 *
 * 1062. Longest Repeating Substring
 *
 * Given a string S, find out the length of the longest repeating substring(s).
 * Return 0 if no repeating substring exists.
 *
 * Example 1:
 * Input: "abcd"
 * Output: 0
 * Explanation: There is no repeating substring.
 *
 * Example 2:
 * Input: "abbaba"
 * Output: 2
 * Explanation: The longest repeating substrings are "ab" and "ba", each of which occurs twice.
 *
 * Example 3:
 * Input: "aabcaabdaab"
 * Output: 3
 * Explanation: The longest repeating substring is "aab", which occurs 3 times.
 *
 * Example 4:
 * Input: "aaaaa"
 * Output: 4
 * Explanation: The longest repeating substring is "aaaa", which occurs twice.
 *
 * Note:
 * The string S consists of only lowercase English letters from 'a' - 'z'.
 * 1 <= S.length <= 1500
 *
 * TODO: Approach 1: Binary Search + Hashset of Already Seen Strings
 * TODO: Approach 2: Binary Search + Hashset of Hashes of Already Seen Strings
 *
 * TODO: Binary Search PART: suppose char length is 15
 * TODO: 1) Start to find repeated string of max length 8:(1+15)/2;
 * TODO: 2) If NOT FOUND, find max length of 4:(1+8)/2;
 * TODO: 3) If FOUND, find max length of 6:(4+8)/2,...etc.
 * TODO: Basically, what discussed above is the thought of binary search.
 * TODO: Notice: think carefully which binary search pattern to use.
 *
 * TODO: Search PART: 3 approaches
 * TODO: 1. Linear-time slice + hashset of already seen strings. O((N−L)L) time complexity and huge space consumption
 * TODO:    in the case of large strings (O(N-L)L).
 * TODO: 2. Linear-time slice + hashset of hashes of already seen strings. O((N−L)L) time complexity and moderate
 * TODO:    space consumption even in the case of large strings (O(N-L)).
 * TODO: 3. Rabin-Karp=constant-time slice+hashset of hashes of already seen strings. Hashes are computed with the
 * TODO:    rolling hash algorithm. O(N−L) time complexity and moderate space consumption even in the case of large
 * TODO:    strings (O(N-L)).
 * TODO: Must refer to the leetcode SOLUTION for comprehensive analysis of algorithm (including Rolling Hash,
 * TODO: Linear congruential generator).
 */
public class LongestRepeatingSubstring {

    // approach 2: binary search+hash of already seen strings
    public int longestRepeatingSubstring(String S) {
        int l = 1;
        int h = S.length()-1;
        while (l < h) {
            int mid = (l + h + 1) / 2;
            if (search(mid, S)) {
                l = mid;
            } else {
                h = mid - 1;
            }
        }
        return l == 1 ? 0 : l;
    }

    private boolean search(int length, String S) {
        System.out.println("length: " + length);
        HashSet<Integer> seen = new HashSet<>();
        for (int i = length; i <= S.length(); i++) {
            int currHash = S.substring(i-length, i).hashCode();
            if (!seen.contains(currHash))
                seen.add(currHash);
            else
                return true;
        }
        return false;
    }

    @Test
    public void case1() {
        assertEquals(0, longestRepeatingSubstring("abcd"));
    }

    @Test
    public void case2() {
        assertEquals(2, longestRepeatingSubstring("abbaba"));
    }

    @Test
    public void case3() {
        assertEquals(3, longestRepeatingSubstring("aabcaabdaab"));
    }

    @Test
    public void case4() {
        assertEquals(4, longestRepeatingSubstring("aaaaa"));
    }

}
