package _2021.contest._0501;

import org.junit.Test;

/**
 * Created by Yiyun On 2021/5/1 10:35
 */
public class _1 {

    public String replaceDigits(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i+=2) {
            sb.append(chars[i]);
            if (i+1 < chars.length) {
                int j = Integer.parseInt(String.valueOf(chars[i + 1]));
                int a = chars[i];
                char c = (char)(a+j);
                sb.append(c);
            }
        }
        return sb.toString();
    }

    @Test
    public void case1() {
        System.out.println(replaceDigits("a1c1e1"));
    }
}
