package bit.manipulation.medium;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Yiyun On 2021/9/22 20:37
 * 1239. Maximum Length of a Concatenated String with Unique Characters
 * Contains:
 *  1[Rej]. Bit-manipulation solution.
 *  2[Rej]. TLE solution: normal DFS/brute force.
 *  3[Accept]. Refactored bit-manipulation solution from lee215
 */
public class MaxLenConcatStrUniqChars {

    List<String> arr;
    int n;
    int[] bits;

    public int maxLength(List<String> arr) {
        List<Integer> dp = new ArrayList<>();
        dp.add(0);
        int res = 0;
        for (String s : arr) {
            int a = 0, dup = 0;
            for (char c : s.toCharArray()) {
                dup |= a & (1 << (c - 'a'));
                a |= 1 << (c - 'a');
            }
            if (dup > 0) continue;
            for (int i = dp.size() - 1; i >= 0; --i) {
                if ((dp.get(i) & a) > 0) continue;
                dp.add(dp.get(i) | a);
                res = Math.max(res, Integer.bitCount(dp.get(i) | a));
            }
        }
        return res;
    }

    public int maxLength_bit_rej(List<String> arr) {
        this.arr = arr;

        Set<Integer> bitset = new HashSet<>();
        for (String s: arr) {
            int bits = toBits(s);
            // Unused space用来储存这个词的长度，在dfs里用到。不做这个操作也可以。
            bitset.add(bits);
        }
        // arr只是为了在dfs取数，set拿不了
        this.bits = set2Arr(bitset);
        this.n = this.bits.length;
        return dfs_bit_rej(0, 0);
    }

    private int dfs_bit_rej(int idx, int cur) {
        int curCombo = cur & ((1 << 26) - 1);
        int curLen = cur >> 26;
        int res = curLen;

        for (int i = idx; i < n; i++) {
            int next = bits[i];
            int nextBits = next & ((1 << 26) - 1);
            int nextLen = next >> 26;

            // 这个词本身就有重复字母，无谓再继续了
            if ((curCombo & nextBits) > 0)
                continue;

            int nextCombo = curCombo + nextBits + (curLen+nextLen << 26);
            res = Math.max(res, dfs_bit_rej(i+1, nextCombo));
        }
        return res;

    }

    private int toBits(String s) {
        int bits = 0;
        for (char ch: s.toCharArray()) {
            int mask = 1 << ch - 'a';

            // 这个词里的字母本来就有重复，无谓再循环了
            if ((bits & mask) > 0)
                break;
            bits += mask;
        }
        return bits + (s.length()<<26);
    }

    private int[] set2Arr(Set<Integer> set) {
        int[] arr = new int[set.size()];
        int i = 0;
        for(Integer val: set) {
            arr[i++] = val;
        }
        return arr;
    }

    @Test
    public void case1() {
        assertEquals(16, maxLength_bit_rej(new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p"))));
    }

    @Test
    public void case2() {
        assertNotEquals(20, maxLength_bit_rej(new ArrayList<>(Arrays.asList("yy","bkhwmpbiisbldzknpm"))));
    }

    // Because it require O(N), imagine input an even 1-D arr of 10 element, then it goes exponentially exploded.
   public int maxLength_TLE(List<String> arr) {
        this.arr = arr;
        return dfs_TLE(0, "");
    }

    private int dfs_TLE(int idx, String cur) {
        int curLen = cur.length();

        Set<Character> set = new HashSet<>();
        for (char c : cur.toCharArray()) {
            set.add(c);
        }

        if (set.size() != curLen)
            return 0;

        int res = curLen;
        for(int i = idx; i < arr.size(); i++) {
            res = Math.max(res, dfs_TLE(idx+1, cur+arr.get(i)));
        }
        return res;
    }

}
