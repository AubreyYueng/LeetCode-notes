package string.medium;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/9/3 18:07
 *
 * 6. ZigZag Conversion
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to
 * display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string s, int numRows);
 *
 * Example 1:
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 *
 * Example 2:
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 */
public class ZigZagConversion {

    public String convert(String s, int numRows) {
        int length = s.length();

        List<List<Character>> zz = new LinkedList<>();
        for (int i = 0; i < numRows; i++) {
            zz.add(new LinkedList<>());
        }

        int i = 0;
        while (i < length) {
            for (int j = 0; j < numRows && i < length; j++,i++) {
                zz.get(j).add(s.charAt(i));
            }

            for (int j = 0; j < numRows-2 && i < length; j++,i++) {
                zz.get(numRows-2-j).add(s.charAt(i));
            }
        }

        StringBuilder sb = new StringBuilder();
        zz.forEach(z -> z.forEach(sb::append));
        return sb.toString();
    }

    @Test
    public void case1() {
        assertEquals("PAHNAPLSIIGYIR", convert("PAYPALISHIRING", 3));
    }

    @Test
    public void case2() {
        assertEquals("PINALSIGYAHRPI", convert("PAYPALISHIRING", 4));
    }
}
