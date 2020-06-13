package twopointers.sliding.window.medium;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/6/12 22:41
 *
 * 438. Find All Anagrams in a String
 */
public class FindAllAnagramsInAString {

    // Find duplicates: Hash + Sliding window
    public List<Integer> findAnagrams(String s, String p) {
        int lenP = p.length();
        int lenS = s.length();
        if (lenS < lenP)
            return Collections.emptyList();

        // Here we use array instead of char->count HashMap
        int[] hashS = new int[26];
        int[] hashP = new int[26];

        for (int i = 0; i < lenP; i++) {
            hashP[p.charAt(i) - 'a']++;
            hashS[s.charAt(i) - 'a']++;
        }

        // init
        List<Integer> res = new LinkedList<>();
        if (Arrays.equals(hashP, hashS))
            res.add(0);

        int ed = lenP;     // right index of sliding window
        while (ed < lenS) {
            // st index of previous window -1
            hashS[s.charAt(ed-lenP) - 'a']--;
            // ed index of current window +1
            hashS[s.charAt(ed) - 'a']++;
            if (Arrays.equals(hashP, hashS))
                res.add(ed-lenP+1);
            ed++;
        }

        return res;
    }

    @Test
    public void case1() {
        assertEquals("[0, 6]", findAnagrams("cbaebabacd", "abc").toString());
    }

    @Test
    public void case2() {
        assertEquals("[0, 1, 2]", findAnagrams("abab", "ab").toString());
    }

}
