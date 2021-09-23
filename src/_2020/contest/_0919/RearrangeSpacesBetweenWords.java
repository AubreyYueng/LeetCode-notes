package _2020.contest._0919;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/9/19 22:52
 *
 * 1592. Rearrange Spaces Between Words
 */
public class RearrangeSpacesBetweenWords {

    public String reorderSpaces(String text) {
        String[] words = text.trim().split("\\s+");
        int wCnt = words.length;
        if (wCnt == 1) {
            String w = words[0];
            int spaceCnt = text.length() - w.length();
            for (int i = 0; i < spaceCnt; i++) {
                w += " ";
            }
            return w;
        }
        wCnt--;

        int spaceCnt = 0;
        for (char c : text.toCharArray()) if (c == ' ') spaceCnt++;

        StringBuilder delimiter = new StringBuilder();
        for (int i = 0; i < spaceCnt / wCnt; i++) {
            delimiter.append(" ");
        }

        StringBuilder rest = new StringBuilder();
        for (int i = 0; i < spaceCnt % wCnt; i++) {
            rest.append(" ");
        }

        String res = "";
        for (int i = 0; i < words.length; i++) {
            res += words[i];
            if (i != words.length-1)
                res += delimiter;
            else
                res += rest;
        }

        return res;

    }

    @Test
    public void case1() {
        assertEquals("hello  ", reorderSpaces("  hello"));
    }


}
