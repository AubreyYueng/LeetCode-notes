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
 */
public class LongestRepeatingSubstring {

    // binary search + hashset of hashes
    public int longestRepeatingSubstring_review20200724(String S) {
        return new Helper_review20200724(S).res();
    }

    private static class Helper_review20200724 {
        String S;
        int n;

        Helper_review20200724(String s) {
            S = s;
            this.n = s.length();
        }

        private int res() {
            int l = 0;  // representing the possible length of substring
            int r = n;
            while (l < r) {
                int mid = (l + r) / 2;
                if (search_hashsetOfHashes(mid) != -1) l = mid + 1;
                else r = mid;
            }
            return l-1;
        }

        private int search_hashsetOfHashes(int L) {
            HashSet<Integer> seen = new HashSet<>();
            for (int i = 0; i < n - L + 1; i++) {
                int h = S.substring(i, i+L).hashCode();
                if (seen.contains(h)) return i;
                seen.add(h);
            }
            return -1;
        }
    }

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
        assertEquals(0, longestRepeatingSubstring_review20200724("abcd"));
    }

    @Test
    public void case2() {
        assertEquals(2, longestRepeatingSubstring_review20200724("abbaba"));
    }

    @Test
    public void case3() {
        assertEquals(3, longestRepeatingSubstring_review20200724("aabcaabdaab"));
    }

    @Test
    public void case4() {
        assertEquals(4, longestRepeatingSubstring_review20200724("aaaaa"));
    }

}
