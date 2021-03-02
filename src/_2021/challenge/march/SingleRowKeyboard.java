package _2021.challenge.march;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2021/3/1 23:43
 */
public class SingleRowKeyboard {

    public int calculateTime(String keyboard, String word) {
        char[] chars = word.toCharArray();
        int res = keyboard.indexOf(chars[0]);
        int prev = res;
        for (int i = 1; i < word.length(); i++) {
            char ch = chars[i];
            int curr = keyboard.indexOf(ch);
            res += Math.abs(curr-prev);
            prev = curr;
        }
        return res;

    }

    @Test
    public void case1() {
        assertEquals(4, calculateTime("abcdefghijklmnopqrstuvwxyz", "cba"));
    }

}
