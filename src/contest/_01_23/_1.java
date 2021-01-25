package contest._01_23;

import org.junit.Test;

import java.util.regex.Pattern;

/**
 * Created by Yiyun On 2021/1/23 21:57
 */
public class _1 {

    public String maximumTime(String time) {
        String[] regex = time.replace("?", ".").split(":");
        int h = 0;
        if (regex[0].contains(".")) {
            for (int i = 0; i < 24; i++) {
                String fmt = String.format("%02d", i);
                if (Pattern.compile(regex[0]).matcher(fmt).matches())
                    h = Math.max(i, h);
            }
        } else
            h = Integer.valueOf(regex[0]);

        int s = 0;
        if (regex[1].contains(".")) {
            for (int i = 1; i < 60; i++) {
                if (Pattern.compile(regex[1]).matcher(String.format("%02d", i)).matches())
                    s = Math.max(i, s);
            }
        } else
            s = Integer.valueOf(regex[1]);
        return String.format("%02d", h) + ":" + String.format("%02d", s);

    }

    @Test
    public void case1() {
        System.out.println(maximumTime("0?:3?"));
    }
}
