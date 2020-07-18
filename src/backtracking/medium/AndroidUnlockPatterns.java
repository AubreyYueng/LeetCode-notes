package backtracking.medium;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/17 21:07
 *
 * 351. Android Unlock Patterns
 */
public class AndroidUnlockPatterns {

    // Q: 3x3 key lock screen, orderly connect at least m keys and at most n
    // distinct keys with no jumps through any non-selected key
    // Follow up of optimization:
    // leetcode.com/problems/android-unlock-patterns/discuss/82463/Java-DFS-solution-with-clear-explanations-and-optimization-beats-97.61-(12ms)
    // The following codes are mostly copied from the LC Solution
    public int numberOfPatterns(int m, int n) {
        return new Helper(m, n).result();
    }

    private static class Helper {
        boolean[] used = new boolean[9];
        int m;
        int n;

        Helper(int m, int n) {
            this.m = m;
            this.n = n;
        }

        private int result() {
            int res = 0;
            for (int len = m; len <= n; len++) {
                res += calPatterns(-1, len);
                Arrays.fill(used, false);   // reset used
            }
            return res;
        }

        private int calPatterns(int last, int len) {
            if (len == 0)
                return 1;
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                if (valid(i, last)) {
                    used[i] = true;
                    sum += calPatterns(i, len-1);
                    used[i] = false;
                }
            }
            return sum;
        }

        private boolean valid(int i, int last) {
            if (used[i]) return false;
            // initial status
            if (last == -1) return true;
            // knight move or adjacent cells(in a row or in a column)
            if ((last + i) % 2 == 1) return true;
            int mid = (i+last) / 2;
            // cell on diagonal end while are not adjacent(which can also be applied on next 'if' condition)
            if (mid == 4) return used[mid];
            // adjacent cells on diagonal
            if ((i % 3 != last % 3) && (i / 3 != last / 3)) return true;
            return used[mid];
        }
    }

    @Test
    public void case1() {
        assertEquals(9, numberOfPatterns(1, 1));
    }

}
