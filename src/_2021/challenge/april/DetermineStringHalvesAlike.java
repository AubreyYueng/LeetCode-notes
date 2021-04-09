package _2021.challenge.april;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yiyun On 2021/4/9 08:11
 *
 * 1704. Determine if String Halves Are Alike
 */
public class DetermineStringHalvesAlike {

    private static final Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    public boolean halvesAreAlike(String s) {
        int halfId = s.length() / 2;
        String first = s.substring(0, halfId);
        String second = s.substring(halfId, s.length());

        return cntVow(first) == cntVow(second);
    }

    private int cntVow(String s) {
        int cnt = 0;
        for(char ch : s.toCharArray()) {
            if (vowels.contains(ch))
                cnt++;
        }
        return cnt;
    }

}
