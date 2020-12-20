package contest._1104;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yiyun On 2020/11/14 22:19
 *
 * Determine if Two Strings Are Close
 */
public class _2 {

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length())
            return false;

        Map<String, Integer> diffCnt = new HashMap<>();
        for (int i = 0; i < word1.toCharArray().length; i++) {
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);
            if (c1 == c2)
                continue;

            String key = c1 > c2 ? String.valueOf(c1) + String.valueOf(c2) : String.valueOf(c2) + String.valueOf(c1);
            diffCnt.put(key, diffCnt.getOrDefault(key, 0) + 1);
        }

        return diffCnt.values().stream().allMatch(i -> i > 1);

    }

    @Test
    public void case1() {
        assertTrue(closeStrings("abc", "bca"));
    }

    @Test
    public void case2() {
        assertFalse(closeStrings("a", "aa"));
    }

    @Test
    public void case3() {
        assertTrue(closeStrings("cabbba", "abbccc"));
    }

    @Test
    public void case4() {
        assertFalse(closeStrings("cabbba", "aabbss"));
    }
}
