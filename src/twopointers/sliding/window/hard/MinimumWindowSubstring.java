package twopointers.sliding.window.hard;

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

    public String minWindow(String s, String t) {
        // cases like: aabc (more than 1 a)
        Map<Character, Integer> tCnt = new HashMap<>();
        for (char c : t.toCharArray()) {
            int cnt = tCnt.getOrDefault(c, 0);
            tCnt.put(c, cnt+1);
        }

        // index -> character, only those chars in t,
        // thus we don't need to iterate all s chars, and can avoid check if in t chars every time
        Map<Integer, Character> sMap = new HashMap<>();
        List<Integer> sIndices = new LinkedList<>();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (tCnt.containsKey(c)) {
                sIndices.add(i);
                sMap.put(i, c);
            }
        }

        // count of in t chars within the sliding window
        Map<Character, Integer> windowCnt = new HashMap<>();
        // flag for if certain window contains all the characters in t(by comparing with tLen)
        int flag = 0;
        int tLen = t.length();

        int minWidth = 0;
        // then we can start to slide the window, l and r refer to the index of sIdx list
        int l = 0;
        int r = 0;
        // First, we find the first right index with left index 0 that contains all t chars
        for (; r < sIndices.size(); r++) {
            int rIdx = sIndices.get(r);

            // update windowCnt
            Character c = sMap.get(rIdx);
            int cnt = windowCnt.getOrDefault(c, 0);
            int newCnt = cnt+1;
            windowCnt.put(c, newCnt);

            // update flag
            if (newCnt <= tCnt.get(c))
                flag++;

            if (flag == tLen) {
                int lIdx = sIndices.get(l);
                minWidth = rIdx-lIdx+1;
                break;
            }
        }
        // There's never a fitting window
        if (flag < tLen)
            return "";
//        System.out.println("first fitting window: l:" + sIndices.get(l) + ", r: " + sIndices.get(r));

        String res = s.substring(sIndices.get(l), sIndices.get(r)+1);

        int sIndicesLen = sIndices.size();
        while (l < r && r < sIndicesLen) {
            int lIdx = sIndices.get(l);
            int rIdx = sIndices.get(r);

            Character lVal = sMap.get(lIdx);
            int currLCnt = windowCnt.get(lVal);     // count of lVal in the window
            int tLCnt = tCnt.get(lVal);             // count of lVal in t

            if (currLCnt > tLCnt) {
                // if current left value exceed the actual, we can move left to the next
                windowCnt.put(lVal, currLCnt-1);
                l++;
                lIdx = sIndices.get(l);

                // compare the current width of the window with former minWidth to decide if res is needed updating
                int currWidth = rIdx-lIdx+1;
                if (currWidth < minWidth) {
                    minWidth = currWidth;
                    res = s.substring(sIndices.get(l), sIndices.get(r)+1);
                }
            } else {
                // otherwise, left pointer can't be moved, so we move right to the next
                if (r == sIndicesLen-1)
                    break;
                else {
                    r++;
                    rIdx = sIndices.get(r);
                    Character rVal = sMap.get(rIdx);
                    windowCnt.put(rVal, windowCnt.get(rVal)+1);
                }
            }
//            System.out.println("sliding fitting window: l:" + sIndices.get(l) + ", r: " + sIndices.get(r));
        }
//        System.out.println("final fitting window: l:" + sIndices.get(l) + ", r: " + sIndices.get(r));

        return res;
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
