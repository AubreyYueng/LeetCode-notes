package _2021.challenge.april;

import java.util.Comparator;

/**
 * Created by Yiyun On 2021/4/9 07:50
 *
 * 953. Verifying an Alien Dictionary
 */
public class VerifyAlienDict {

    public boolean isAlienSorted(String[] words, String order) {
        Comparator<String> cmp = (String a, String b) -> {
            int aLen = a.length();
            int bLen = b.length();

            for (int i = 0; i < Math.min(aLen, bLen); i++) {
                char chx = a.charAt(i);
                char chy = b.charAt(i);
                int idx = order.lastIndexOf(chx);
                int idy = order.lastIndexOf(chy);

                if (idx == idy) continue;
                return idx > idy ? 1 : -1;
            }
            if (aLen == bLen) return 0;
            return aLen > bLen ? 1 : -1;
        };
        for (int i = 1; i < words.length; i++) {
            String b = words[i];
            String a = words[i-1];
            if (cmp.compare(a, b) > 0)
                return false;
        }
        return true;
    }
}
