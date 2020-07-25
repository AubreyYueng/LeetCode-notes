package binary.search.hard;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/24 00:57
 *
 * 1044. Longest Duplicate Substring
 */
public class LongestDuplicateSubstring {

    // Similar with the above question: 1062. Longest Repeating Substring,
    // except 1062 returns len and here returns a substring.
    // Here using binary search+hashes hash is not accepted by all test cases given a very long string.
    // Thus we have to use Rabin-Karp for hashing
    public String longestDupSubstring(String S) {
        return new Helper(S).res();
    }

    private static class Helper {
        String S;
        int n;
        int a;      // base
        long modulus;
        int[] nums;

        Helper(String s) {
            S = s;
            this.n = s.length();
            this.a = 26;
            this.modulus = (long)Math.pow(2, 32);
            this.nums = new int[n];
            for(int i = 0; i < n; ++i) nums[i] = (int)S.charAt(i) - (int)'a';
        }

        private String res() {
            int l = 0;  // representing the possible length of substring
            int r = n;
            int st = -1;
            while (l < r) {
                int mid = (l + r) / 2;
                int currSt = search_rabinKarp(mid);
                if (currSt != -1) {
                    l = mid + 1;
                    st = currSt;
                }
                else r = mid;
            }
            return st == -1 ? "" : S.substring(st, (int)(st+l-1));
        }

        // codes are mostly copied from LC Solution
        private int search_rabinKarp(int L) {
            // compute the hash of string S[:L]
            long h = 0;
            for(int i = 0; i < L; ++i) h = (h * a + nums[i]) % modulus;

            // already seen hashes of strings of length L
            HashSet<Long> seen = new HashSet<>();
            seen.add(h);
            // const value to be used often : a**L % modulus
            long aL = 1;
            for (int i = 0; i < L; ++i) aL = (aL * a) % modulus;

            for(int i = 1; i < n - L + 1; ++i) {
                // compute rolling hash in O(1) time
                h = (h * a - nums[i - 1] * aL % modulus + modulus) % modulus;
                h = (h + nums[i + L - 1]) % modulus;
                if (seen.contains(h)) return i;
                seen.add(h);
            }
            return -1;
        }

        private int search_hashesHashset(long L) {
            HashSet<Integer> seen = new HashSet<>();
            for (int i = 0; i < n - L + 1; i++) {
                String curr = S.substring(i, (int)(i+L));
                int h = curr.hashCode();
                if (seen.contains(h)) return i;
                seen.add(h);
            }
            return -1;
        }
    }

    @Test
    public void case1() {
        assertEquals("ana", longestDupSubstring("banana"));
    }

    @Test
    public void case2() {
        assertEquals("", longestDupSubstring("abcd"));
    }

}
