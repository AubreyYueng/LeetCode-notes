package string.easy;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yiyun On 2019/8/29 17:51
 *
 * 151. Reverse Words in a String
 *
 * Given an input string, reverse the string word by word.
 *
 * Example 1:
 * Input: "the sky is blue"
 * Output: "blue is sky the"
 *
 * Example 2:
 * Input: "  hello world!  "
 * Output: "world! hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 *
 * Example 3:
 * Input: "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 * Note:
 * A word is defined as a sequence of non-space characters.
 * Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
 * You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 * Follow up:
 * For C programmers, try to solve it in-place in O(1) extra space.
 *
 * TODO: NOTICS the last element should not append " ";
 */
public class ReverseWordsInAString {

    public String reverseWords(String s) {
        LinkedList<String> array = new LinkedList<>();
        for (String sub : s.trim().split("\\s+")) {
            array.addFirst(sub);
        }
        StringBuilder newS = new StringBuilder();
        String last = array.pollLast();
        array.forEach(a -> newS.append(a).append(" "));
        newS.append(last);
        return newS.toString();
    }

}
