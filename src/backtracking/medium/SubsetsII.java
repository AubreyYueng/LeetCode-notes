package backtracking.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yiyun On 2019/9/17 13:13
 *
 * 90. Subsets II
 *
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 * Input: [1,2,2]
 * Output:
 * [
 *  [2],
 *  [1],
 *  [1,2,2],
 *  [2,2],
 *  [1,2],
 *  []
 * ]
 *
 * TODO: contains duplicate: sort first
 */
public class SubsetsII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null) {
            return res;
        }

        Arrays.sort(nums);
        dfs(0, nums, new LinkedList<>(), res);
        return res;
    }

    private void dfs(int index, int[] nums, LinkedList<Integer> list, List<List<Integer>> res) {
        res.add(new LinkedList<>(list));

        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i-1])
                continue;
            list.add(nums[i]);
            dfs(i+1, nums, list, res);
            list.removeLast();
        }
    }
}
