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
 *
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * For example, given the following triangle
 * [
 *       [2],
 *      [3,4],
 *     [6,5,7],
 *    [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 *
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 *
 * TODO: 注意不是简单的加上下一行相邻的最小值
 *
 * 动态规划 时间O(n2) 空间O(1)
 * 点(i,j)的下一行的相邻数字是(i+1,j)和(i+1,j+1)。
 * f(i,j)表示从下往上走到位置(i,j)时的最小路径和，计算方式/状态转移方程是
 * f(i,j)=(i,j)+min(f(i+1,j),f(i+1,j+1))
 * 复杂度分析：
 * 直接把f(i,j)存在位置(i,j)处，不使用额外空间，因此空间复杂度为O(1)。
 * 两层for loop，第一次竖着遍历，第二次横着遍历，时间复杂度为O(n2)。
 */
public class Triangle {

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
