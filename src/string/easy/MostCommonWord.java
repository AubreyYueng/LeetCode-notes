package string.easy;

import java.util.*;

/**
 * Created by Yiyun On 2020/3/4 00:45
 *
 * 819. Most Common Word
 * https://leetcode.com/problems/most-common-word/
 */
public class MostCommonWord {

    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0)
            return null;
        List<String> ban = Arrays.asList(banned);

        Map<String, Integer> cnt = new HashMap<>();
        // \W: match not letters, digits, and underscores chars
        // \w: match letters, digits and underscores
        // Be careful about this regex: 1. not *; 2. ^ is inside the []; 3. escapse: eg. \\ for \
        for(String w: paragraph.split("[^a-zA-Z]+")) {
            w = w.toLowerCase();
            if (!ban.contains(w)) {
                cnt.putIfAbsent(w, 0);
                cnt.put(w, cnt.get(w)+1);
            }
        }

        return cnt.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).map(Map.Entry::getKey).orElse(null);
    }

}
