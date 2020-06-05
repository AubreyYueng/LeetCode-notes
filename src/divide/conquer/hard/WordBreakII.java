package divide.conquer.hard;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yiyun On 2020/6/4 19:17
 * 140. Word Break II
 */
public class WordBreakII {

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Map<String, List<String>> used = new HashMap<>();
        int maxLen = wordDict.stream().mapToInt(String::length).max().orElse(0);
        return helper(s, dict, used, maxLen);
    }

    private List<String> helper(String s, Set<String> dict, Map<String, List<String>> used, int maxLen) {
        if (used.containsKey(s))
            return used.get(s);

        if (s.length() == 0)    // means the original s from its caller in dict
            return null;

        List<String> res = new LinkedList<>();
        for (int i = 1; i <= s.length(); i++) {
            // unnecessary to continue
            if (i > maxLen)
                break;

            String prev = s.substring(0, i);
            if (!dict.contains(prev))
                continue;

            String rest = s.substring(i);
            List<String> subRes = helper(rest, dict, used, maxLen);
            if (subRes == null)     // according to the codes above, this means rest is in dict
                res.add(prev);
            else {
                for (String sub : subRes) {
                    res.add(prev + " " + sub);
                }
            }
        }

        used.put(s, res);
        return res;
    }

    @Test
    public void case1() {
        List<String> expected = Arrays.asList("cats and dog","cat sand dog");
        List<String> actual = wordBreak("catsanddog", Arrays.asList("cat","cats","and","sand","dog"));
        assertEquals(expected.size(), actual.size());
        System.out.println(actual);
        assertTrue(expected.containsAll(actual));
    }

}
