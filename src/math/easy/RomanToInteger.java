package math.easy;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/2 22:43
 *
 * 13. Roman to Integer
 */
public class RomanToInteger {

    private Character[] symbols = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
    private int[] nums = {1, 5, 10, 50, 100, 500, 1000};

    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Set<Character>> neighbors = initNeighs();
        for (int i = 0; i < 7; i++) {
            map.put(symbols[i], nums[i]);
        }

        int sum = 0;

        int len = s.length();
        for (int i = 0; i < len; ) {
            char curr = s.charAt(i);
            int currV = map.get(curr);

            Set<Character> neighs = neighbors.get(curr);
            if (i < len-1 && neighs != null) {
                char nextCh = s.charAt(i+1);
                if (neighs.contains(nextCh)) {
                    int nextV = map.get(nextCh);
                    sum += nextV-currV;
                    i+=2;
                    continue;
                }
            }

            sum += currV;
            i++;
        }

        return sum;
    }

    private Map<Character, Set<Character>> initNeighs() {
        Character[] keys = {'I', 'X', 'C'};
        Character[][] values = {{'V', 'X'}, {'L', 'C'}, {'D', 'M'}};
        Map<Character, Set<Character>> neighbors = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            Set<Character> v = new HashSet<>();
            v.addAll(Arrays.asList(values[i]));
            neighbors.put(keys[i], v);
        }
        return neighbors;
    }

    @Test
    public void case1() {
        assertEquals(1994, romanToInt("MCMXCIV"));
    }

    @Test
    public void case2() {
        assertEquals(58, romanToInt("LVIII"));
    }

    @Test
    public void case3() {
        assertEquals(9, romanToInt("IX"));
    }

    @Test
    public void case4() {
        assertEquals(4, romanToInt("IV"));
    }

}
