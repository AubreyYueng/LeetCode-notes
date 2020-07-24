package stack.hard;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/23 11:59
 *
 * 726. Number of Atoms
 */
public class NumberOfAtoms {

    public String countOfAtoms(String formula) {
        return countOfAtoms_stack(formula);
    }

    // This approach can also be transformed to recursion
    // Time: O(N^2)
    // Space: O(N)
    private String countOfAtoms_stack(String formula) {
        int n = formula.length();
        LinkedList<Map<String, Integer>> stack = new LinkedList<>();
        stack.push(new HashMap<>());

        int i = 0;
        while (i < n) {
            char ch = formula.charAt(i);
            if (ch == '(') {
                stack.push(new HashMap<>());
                i++;
            } else if (ch == ')') {
                Map<String, Integer> subCnt = stack.pop();
                int oldI = ++i;
                while (i < n && Character.isDigit(formula.charAt(i))) i++;
                int currCnt = oldI==i ? 1 : Integer.parseInt(formula.substring(oldI, i));

                Map<String, Integer> updated = stack.peek();
                subCnt.forEach((k, v) -> updated.put(k, updated.getOrDefault(k, 0) + v * currCnt));
            } else {
                int oldI = i++;
                while (i < n && Character.isLowerCase(formula.charAt(i))) i++;
                String name = formula.substring(oldI, i);
                oldI = i;
                while (i < n && Character.isDigit(formula.charAt(i))) i++;
                int currCnt = i == oldI ? 1 : Integer.parseInt(formula.substring(oldI, i));

                Map<String, Integer> subCnt = stack.peek();
                subCnt.put(name, subCnt.getOrDefault(name, 0) + currCnt);
            }
        }

        StringBuilder res = new StringBuilder();
        Map<String, Integer> finalCnt = stack.peek();
        List<String> keys = finalCnt.keySet().stream().sorted().collect(Collectors.toList());
        for (String name : keys) {
            res.append(name);
            int cnt = finalCnt.get(name);
            if (cnt > 1)
                res.append(cnt);
        }
        return res.toString();
    }

    @Test
    public void case1() {
        assertEquals("H2O", countOfAtoms("H2O"));
    }

    @Test
    public void case2() {
        assertEquals("H2MgO2", countOfAtoms("Mg(OH)2"));
    }

    @Test
    public void case3() {
        assertEquals("K4N2O14S4", countOfAtoms("K4(ON(SO3)2)2"));
    }

    private String countOfAtoms_regex(String formula) {
        return null;    // TODO
    }

}
