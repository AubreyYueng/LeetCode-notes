package easy;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;

/**
 * Created by Yiyun On 2019/7/2 22:56
 *
 * No 914: In a deck of cards, each card has an integer written on it.

 Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 or more
 groups of cards, where:

 Each group has exactly X cards.
 All the cards in each group have the same integer.

 Example:
 Input: [1,2,3,4,4,3,2,1]
 Output: true
 Explanation: Possible partition [1,1],[2,2],[3,3],[4,4]

 Note:

 1 <= deck.length <= 10000
 0 <= deck[i] < 10000

 TODO: 1. Each group has exactly X cards; X >= 2; 1 OR MORE GROUPS;
 TODO: 2. same integer can be split into multiple groups, and especially the end of loop
 */
public class XOfAKindInADeckOfCards {

    /**
     * ALTERNATIVE SOLUTION:
     * 1. find the smallest value in keyCnt first, then divide every other rest value in map.
     */
    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> keyCnt = new HashMap<>();
        for (int d : deck) {
            if (keyCnt.containsKey(d))
                keyCnt.put(d, keyCnt.get(d) + 1);
            else {
                keyCnt.put(d, 1);
            }
        }

        if (keyCnt.isEmpty())
            return false;

        Integer largestCommonDivisor = keyCnt.entrySet().iterator().next().getValue();
        for (Integer v : keyCnt.values()) {
            System.out.println("result: " + largestCommonDivisor);
            if (largestCommonDivisor == null)
                return false;
            largestCommonDivisor = largestCommonDivisor(largestCommonDivisor, v);
        }
        return largestCommonDivisor != null;
    }

    private Integer largestCommonDivisor(int a, int b) {
        System.out.println("a: " + a + ", b: " + b);
        if (b > a)
            return largestCommonDivisor(b, a);
        if (b <= 1)
            return null;
        int remainder = a % b;
        if (remainder == 0)
            return b;
        return largestCommonDivisor(remainder, b);
    }

    @Test
    public void case1() {
        assertFalse(hasGroupsSizeX(new int[]{1,1,1,2,2,2,3,3}));
    }
}
