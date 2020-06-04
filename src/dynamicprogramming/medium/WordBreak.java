package dynamicprogramming.medium;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Yiyun On 2020/6/4 18:29
 *
 * 139. Word Break
 */
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null)
            return false;

        // for querying efficiency
        Set<String> dict = new HashSet<>(wordDict);

        int len = s.length();
        boolean[] state = new boolean[len+1];   // is first n letters breakable
        state[0] = true;                        // initial state

        // conversion function
        // because substring(st, ed)'s ed is exclusive, and initial state is at index 0, thus we start from 1
        for (int i = 1; i <= len; i++) {
            for (int j = i; j > 0; j--) {
                if (dict.contains(s.substring(i-j, i)) && state[i-j]) {
                    state[i] = true;
                    break;
                }
            }
        }

        return state[len];
    }

}
