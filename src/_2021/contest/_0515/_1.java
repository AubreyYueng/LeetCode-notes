package _2021.contest._0515;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Yiyun On 2021/5/15 10:36
 */
public class _1 {

    public String sortSentence(String s) {
        List<String> res = new ArrayList<>(Arrays.asList(s.split(" ")));
        res.sort(Comparator.comparing(str -> Integer.parseInt(str.substring(str.length() - 2))));
        res = res.stream().map(str -> str.substring(0, str.length()-1)).collect(Collectors.toList());
        return String.join(" ", res.toArray(new String[]{}));
    }
}
