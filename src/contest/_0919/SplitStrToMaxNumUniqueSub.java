package contest._0919;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/9/19 23:10
 *
 * 1593. Split a String Into the Max Number of Unique Substrings
 */
public class SplitStrToMaxNumUniqueSub {

    public int maxUniqueSplit(String s) {
        Helper h = new Helper(s);
        h.dfs(new HashSet<>(), 0);
        return h.res;
    }

    private static class Helper {
        String s;
        int res;
        int n;

        private Helper(String s) {
            this.s = s;
            this.n = s.length();
        }

        private void dfs(Set<String> subString, int st) {
//            System.out.println(subString + ",  " + st);
            if (st >= n) {
                res = Math.max(res, subString.size());
                return;
            }

            for (int ed = st+1; ed <= n; ed++) {
                String subStr = s.substring(st, ed);
                if (!subString.contains(subStr)) {
                    Set<String> newSet = new HashSet<>(subString);
                    newSet.add(subStr);
                    dfs(newSet, ed);
                }
            }
        }
    }

    @Test
    public void case1() {
        assertEquals(5, maxUniqueSplit("ababccc"));
    }

    @Test
    public void case2() {
        assertEquals(2, maxUniqueSplit("aba"));
    }

    @Test
    public void case3 () {
        assertEquals(1, maxUniqueSplit("aa"));
    }

}
