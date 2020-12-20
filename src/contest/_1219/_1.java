package contest._1219;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/12/19 21:36
 */
public class _1 {

    public String reformatNumber(String number) {
        LinkedList<String> subs = new LinkedList<>();
        int cnt = 0;

        StringBuilder sub = new StringBuilder();
        for (char c : number.toCharArray()) {
            if (c == ' ' || c == '-')
                continue;
            sub.append(c);

            if (++cnt == 3) {
                subs.add(sub.toString());
                cnt = 0;
                sub = new StringBuilder();
            }
        }

        String remain = sub.toString();
        if (!remain.equals(""))
            subs.add(remain);

        String last = null;
        if (subs.getLast().length() == 1) {
            char c = subs.removeLast().charAt(0);
            char[] str = subs.removeLast().toCharArray();
            last = String.valueOf(str[0]) + String.valueOf(str[1]) + "-" + String.valueOf(str[2]) + c;
        }

        String prefix = String.join("-", subs.toArray(new String[]{}));
        if (subs.isEmpty())
            return last;
        return last == null ? prefix : prefix + "-" + last;
    }

    @Test
    public void case1() {
        assertEquals("12", reformatNumber("12"));
    }

}
