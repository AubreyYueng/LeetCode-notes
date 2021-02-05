package prep.bloomberg;

import org.junit.Test;
import tool.Pair;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2021/2/4 20:36
 *
 * 387. First Unique Character in a String
 */
public class FirstUniqueCharacterInStr {

    public int firstUniqChar(String s) {
        LinkedList<Pair<Character, Integer>> q = new LinkedList<>();   // pair: ch, index
        Map<Character, Integer> visited = new HashMap<>();  // 1: occur once, -1: occur more than once

        char[] chArr = s.toCharArray();
        for (int i = 0; i < chArr.length; i++) {
            char ch = chArr[i];
            if (!visited.containsKey(ch)) {
                q.offer(new Pair<>(ch, i));
                visited.put(ch, 1);
            } else {
                visited.put(ch, -1);
                while (!q.isEmpty() && 1 != visited.get(q.peek().a)) {
                    q.pop();
                }
            }
        }

        return q.isEmpty() ? -1 : q.peek().b;
    }

    @Test
    public void case1() {
        assertEquals(2, firstUniqChar("loveleetcode"));
    }

}
