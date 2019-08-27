package string.easy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/8/27 21:09
 *
 * 38. Count and Say
 *
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 *
 * Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.
 *
 * Note: Each term of the sequence of integers will be represented as a string.
 * Example 1:
 * Input: 1
 * Output: "1"
 *
 * Example 2:
 * Input: 4
 * Output: "1211"
 *
 * TODO: Notice the last n same chars, that's why we set j <= sLength, bcs we're comparing through the next char
 */
public class CountAndSay {

    public String countAndSay(int n) {
        String s = "1";
        for (int i = 1; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            char curr = s.charAt(0);
            int currCnt = 1;
            int sLength = s.length();
            for (int j = 1; j <= sLength; j++) {
                // System.out.println("in---- curr: " + curr + ", currCnt: " + currCnt);
                char newCurr = j == sLength ? 'a' : s.charAt(j);
                if (newCurr != curr) {
                    sb.append(currCnt).append(curr);
                    curr = newCurr;
                    currCnt = 1;
                } else {
                    currCnt++;
                }

                // System.out.println("out---- curr: " + curr + ", currCnt: " + currCnt);
            }

            s = sb.toString();
            System.out.println("current s: " + s);
        }

        return s;
    }

    @Test
    public void case1() {
        assertEquals("1", countAndSay(1));
    }

    @Test
    public void case2() {
        assertEquals("1211", countAndSay(4));
    }

    @Test
    public void case3() {
        assertEquals("312211", countAndSay(6));
    }
}
