package easy;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * Created by Yiyun on 2019/7/3 16:27
 *
 * No 17: Letter Combinations of a Phone Number
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 * Example:
 *
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 *
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 *
 */
public class LetterCombinationsOfAPhoneNumber {

    public List<String> letterCombinations(String digits) {
        Map<String, List<String>> phone = new HashMap<>();
        phone.put("2", asList("a", "b", "c"));
        phone.put("3", asList("d", "e", "f"));
        phone.put("4", asList("g", "h", "i"));
        phone.put("5", asList("j", "k", "l"));
        phone.put("6", asList("m", "n", "o"));
        phone.put("7", asList("p", "q", "r", "s"));
        phone.put("8", asList("t", "u", "v"));
        phone.put("9", asList("w", "x", "y", "z"));

        if (digits.length() == 0)
            return new LinkedList<>();

        String[] strings = digits.split("");
        LinkedList<List<String>> candidates = new LinkedList<>();
        for (int i = 1; i < strings.length; i++) {
            candidates.add(phone.get(strings[i]));
        }

        return combination(phone.get(strings[0]), candidates);
    }

    private List<String> combination(List<String> prefixes, LinkedList<List<String>> candidates) {
        out.println("prefixes: " + prefixes + ", candidates: " + candidates);
        if (candidates.isEmpty())
            return prefixes;

        List<String> poppedFirst = candidates.pop();
        return combination(
                prefixes.stream().flatMap(p -> poppedFirst.stream().map(c -> p+c)).collect(toList()),
                candidates
        );
    }

    @Test
    public void case1() {
        out.println(letterCombinations("2"));
    }
}
