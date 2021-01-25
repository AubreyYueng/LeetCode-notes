package contest._01_23;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2021/1/23 22:29
 */
public class _2 {

    public int minCharacters(String a, String b) {
        int res = Math.min(aLTb(a, b), aLTb(b, a));
        // TODO
        return res;
    }

    private int aLTb(String a, String b) {
        int res = Integer.MAX_VALUE;
        char limit = b.charAt(0);
        for (char c : b.toCharArray()) {
            if (c < limit) limit = c;
        }

        if (limit != 'a') {
            res = 0;
            for (char c : a.toCharArray()) {
                if (c >= limit) res++;
            }
        } else {
            limit = a.charAt(0);
            for (char c : a.toCharArray()) {
                if (c > limit) limit = c;
            }
            if (limit != 'z'){
                res = 0;
                for (char c : b.toCharArray()) {
                    if (c <= limit) res++;
                }
            }
        }
        return res;
    }

    @Test
    public void case1() {
        assertEquals(2, minCharacters("aba", "caa"));
    }


    @Test
    public void case2() {
        assertEquals(3, minCharacters("dabadd", "cda"));
    }

    @Test
    public void case3() {
        assertEquals(1, minCharacters("dcada", "d"));
    }


}
