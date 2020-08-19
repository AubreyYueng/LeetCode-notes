package dynamicprogramming.medium;

import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/9/1 02:39
 *
 * 120. Triangle
 */
public class Triangle {

    public int minimumTotal_review20200818(List<List<Integer>> triangle) {
        if (triangle == null || triangle.isEmpty() )
            return 0;

        if (triangle.size() == 1) return triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            update(triangle.get(i), triangle.get(i-1));
        }
        return Collections.min(triangle.get(triangle.size()-1));
    }

    private void update(List<Integer> next, List<Integer> prev) {
        int size = prev.size();
        next.set(0, next.get(0)+prev.get(0));
        next.set(size, next.get(size)+prev.get(size-1));

        for (int i = 1; i < next.size()-1; i++) {
            int curr = next.get(i);
            next.set(i, curr + Math.min(prev.get(i-1), prev.get(i)));
        }
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int rowCnt = triangle.size();
        List<Integer> minOfLastRow = triangle.get(rowCnt-1);
        for (int i = triangle.size()-2; i >= 0; i--) {
            List<Integer> minOfCurrRow = new LinkedList<>();
            List<Integer> currRow = triangle.get(i);

            for (int j = 0; j < currRow.size(); j++) {
                int value = currRow.get(j);
                minOfCurrRow.add(value + Math.min(minOfLastRow.get(j), minOfLastRow.get(j + 1)));
            }

            minOfLastRow = minOfCurrRow;
        }

        return minOfLastRow.get(0);
    }

    @Test
    public void case1() {
        assertEquals(11, minimumTotal(asList(
                Collections.singletonList(2),
                asList(3, 4),
                asList(6, 5, 7),
                asList(4, 1, 8, 3)
        )));
    }

    @Test
    public void case2() {
        assertEquals(-1, minimumTotal(asList(
                Collections.singletonList(-1),
                asList(2, 3),
                asList(1, -1, -3)
        )));
    }

}
