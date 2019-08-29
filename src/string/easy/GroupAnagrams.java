package string.easy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Yiyun On 2019/8/27 22:12
 *
 * 49. Group Anagrams
 *
 * Given an array of strings, group anagrams together.
 *
 * Example:
 *
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *  ["ate","eat","tea"],
 *  ["nat","tan"],
 *  ["bat"]
 * ]
 * Note:
 *
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> group = new HashMap<>();
        for (String str : strs) {
            String key = Arrays.stream(str.split("")).sorted().collect(Collectors.joining());
            List<String> value = group.get(key);
            if (value == null)
                group.put(key, new ArrayList<>(Collections.singletonList(str)));
            else
                value.add(str);
        }
        return new ArrayList<>(group.values());
    }

}
