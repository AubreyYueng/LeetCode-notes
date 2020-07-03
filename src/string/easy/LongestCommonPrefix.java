package string.easy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/3 10:57
 * 14. Longest Common Prefix
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";

        int n = strs[0].length();
        String s = strs[0];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            Character c = commonFirst(s.charAt(i), strs, i);
            if (c == null)
                break;
            sb.append(c);
        }

        String res = sb.toString();
        return res.isEmpty() ? "" : res;
    }

    private Character commonFirst(Character c, String[] strs, int i) {
        for (String s : strs) {
            if (i >= s.length())
                return null;

            if (s.charAt(i) != c)
                return null;
        }
        return c;
    }

    @Test
    public void case1() {
        assertEquals("fl", longestCommonPrefix(new String[]{"flower","flow","flight"}));
    }

    @Test
    public void case2() {
        assertEquals("", longestCommonPrefix(new String[]{"dog","racecar","car"}));
    }

}
