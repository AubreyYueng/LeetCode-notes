package contest._0905.bi;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 9/5/20 11:02
 *
 * 1573. Number of Ways to Split a String
 */
public class NumberOfWaysToSplitAString {

    private static final int K = 3;

    public int numWays(String s) {
        int hw = hammingWeight(s);
        if (hw % 3 != 0) return 0;

        long n = s.length()-2;
        long res=((1 + n) * n) / 2 % (1000000007);
        if (hw == 0) return (int)res;

        int subN = hw / 3;
        return new Helper(s, subN).res();
    }

    private int hammingWeight(String s) {
        int res = 0;
        for (char c : s.toCharArray()) {
            res += c == '1' ? 1 : 0;
        }
        return res;
    }

    private static class Helper {
        String s;

        long first;
        long second;

        public Helper(String s, int subN) {
            this.s = s;

            int st0 = 0;
            int ed0 = 0;
            int st1 = 0;
            int ed1 = 0;

            int idx = 0;
            char[] arr = s.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                char ch = arr[i];
                if (ch == '1') {
                    idx ++;
                    if (idx == subN)
                        st0 = i;
                    if (idx == subN + 1)
                        ed0 = i;
                    if (idx == subN*2)
                        st1 = i;
                    if (idx == subN*2 + 1)
                        ed1 = i;
                }
            }

            this.first = ed0-st0;
            this.second = ed1-st1;
        }

        private int res() {
            long res = first * second%(1000000007);
            return (int)res;
        }
    }

    @Test
    public void case1() {
        assertEquals(4, numWays("10101"));
    }

}
