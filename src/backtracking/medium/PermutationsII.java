package backtracking.medium;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/8/30 05:28
 *
 * 47. Permutations II
 *
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * Example:
 * Input: [1,1,2]
 * Output:
 * [
 *  [1,1,2],
 *  [1,2,1],
 *  [2,1,1]
 * ]
 *
 * TODO: sort first, only tiny difference with Permutations#dfs, DON'T forget "&& used[i-1]"
 * TODO: sort an array: use Arrays#sort instead of Collections#sort
 */
public class PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> results = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        dfs(results, nums, used, new LinkedList<>());
        return results;
    }

    private void dfs(List<List<Integer>> results, int[] nums, boolean[] used, List<Integer> tmp) {
        if (tmp.size() == nums.length) {
            results.add(tmp);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i-1] == nums[i] && used[i-1])) continue;

            used[i] = true;
            List<Integer> newTmp = new LinkedList<>(tmp);
            newTmp.add(nums[i]);
            dfs(results, nums, used, newTmp);
            used[i] = false;
        }
    }

    @Test
    public void case1() {
        assertEquals(new LinkedList<>(asList(
                asList(1,1,2),
                asList(1,2,1),
                asList(2,1,1)
        )), permuteUnique(new int[]{1,1,2}));
    }

}
