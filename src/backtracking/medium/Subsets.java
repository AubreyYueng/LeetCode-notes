package backtracking.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yiyun On 2019/9/17 10:46
 *
 * 78. Subsets
 *
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 * Input: nums = [1,2,3]
 * Output:
 * [
 *  [3],
 *  [1],
 *  [2],
 *  [1,2,3],
 *  [1,3],
 *  [2,3],
 *  [1,2],
 *  []
 * ]
 *
 * TODO: f(3) = {(3,f(2)), f(2), (3,f(3)), f(3)}
 * TODO: bit manipulation
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) return res;

        dfs(0, nums, new LinkedList<>(), res);
        return res;
    }

    private void dfs(int index, int[] nums, LinkedList<Integer> list, List<List<Integer>> res) {
        res.add(new LinkedList<>(list));

        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(i+1, nums, list, res);
            list.removeLast();
        }
    }

    @Test
    public void case1() {
        System.out.println(subsets(new int[]{1, 2, 3}));
    }

}
