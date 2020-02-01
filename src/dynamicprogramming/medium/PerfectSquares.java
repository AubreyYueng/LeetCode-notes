package dynamicprogramming.medium;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/2/1 15:12
 *
 * 279. Perfect Squares
 * https://leetcode.com/problems/perfect-squares/
 */
public class PerfectSquares {

    public int numSquares(int n) {
        // f(n) = min{f(n-k) + 1 : k belongs to all square numbers}
        int res[] = new int[n+1];
        for(int i = 0; i < n+1; i++) res[i] = i;

        List<Integer> squareNum = new LinkedList<>();
        int root = 1;
        while (true) {
            int sqrt = root * root;
            if (sqrt > n) break;
            squareNum.add(sqrt);
            root++;
        }

        for (int i = 1; i < n+1; i++) {

            int min = res[i];
            for (int sq : squareNum) {
                if (sq > i) break;

                min = Math.min(min, res[i-sq]+1);
            }
            res[i] = min;

            System.out.println(i + ": " + Arrays.toString(res));
        }

        return res[n];
    }

    @Test
    public void case1() {
        assertEquals(3, numSquares(12));
    }

}
