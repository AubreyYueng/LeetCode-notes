package twopointers.sliding.window.hard;

import javafx.util.Pair;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/6/7 00:57
 *
 * 76. Minimum Window Substring
 */
public class MinimumWindowSubstring {

    // In solution: "The solution is pretty intuitive. We keep expanding the window by moving the right pointer. When the window has
    // all the desired characters, we contract (if possible) and save the smallest window till now."
    public String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty())
            return "";

        Map<Character, Integer> tMap = new HashMap<>(); // t: chars -> cnt
        Map<Character, Integer> seen = new HashMap<>(); // sliding window: seen chars -> cnt
        for (int i = 0; i < t.length(); i++) {
            Character c = t.charAt(i);
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        List<Pair<Character, Integer>> pos = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (tMap.keySet().contains(c))
                pos.add(new Pair<>(c, i));
        }

        int l = 0;
        int r = 0;
        int resSt = -1;
        int resEd = -1;


        int expected = tMap.keySet().size();
        int actual = 0;     // increase when seen.val == tMap.val

        while (r < pos.size()) {        // main loop: move right pointer
            Character edChar = pos.get(r).getKey();
            int edPos = pos.get(r).getValue();
            int edCnt = seen.getOrDefault(edChar, 0)+1;
            seen.put(edChar, edCnt);
            if (edCnt == tMap.get(edChar))
                actual++;

            // contract the sliding window
            while (expected == actual && l <= r) {
                Character stChar = pos.get(l).getKey();
                int stPos = pos.get(l).getValue();

                if (resSt == -1 || (resEd-resSt) > edPos-stPos) {
                    resSt = stPos;
                    resEd = edPos;
                }

                int stCnt = seen.get(stChar) - 1;
                seen.put(stChar, stCnt);
                if (stCnt < tMap.get(stChar))
                    actual--;

                l++;
            }
            r++;
        }

        return resSt == -1 ? "" : s.substring(resSt, resEd+1);
    }



    @Test
    public void case1() {
        assertEquals("BANC", minWindow("ADOBECODEBANC", "ABC"));
    }

    @Test
    public void case2() {
        assertEquals("", minWindow("a", "aa"));
    }

    @Test
    public void case3() {
        assertEquals("ab", minWindow("bdab", "ab"));
    }

    @Test
    public void case4() {
        assertEquals("ba", minWindow("bba", "ab"));
    }

    @Test
    public void case5() {
        assertEquals("cwae", minWindow("cabwefgewcwaefgcf", "cae"));
    }

    @Test
    public void case6() {
        assertEquals("baab", minWindow("aabaabaaab", "bb"));
    }

}
