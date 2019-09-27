package backtracking.medium;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yiyun On 2019/9/17 13:41
 *
 * 216. Combination Sum III
 *
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be
 * used and each combination should be a unique set of numbers.
 * Note:
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 *
 * Example 1:
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 *
 * Example 2:
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * TODO: Notice the "res.add(new LinkedList<>(tmp));" part. Don't add tmp directly, 'cause it's changed constantly.
 */
public class CombinationSumIII {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new LinkedList<>();
        dfs(1, k, n, n, res, new LinkedList<>());
        return res;
    }

    private void dfs(int start, int k, int n, int target, List<List<Integer>> res, LinkedList<Integer> tmp) {
        if (tmp.size() == k && target == 0)
            res.add(new LinkedList<>(tmp));
        for (int i = start; i < 10; i++) {
            if (target < i)
                break;
            tmp.add(i);
            dfs(i+1, k, n, target-i, res, tmp);
            tmp.removeLast();
        }
    }

    @Test
    public void case1() {
        System.out.println(combinationSum3(3, 7));
    }

}
