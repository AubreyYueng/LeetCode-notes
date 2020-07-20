package backtracking.medium;

import org.junit.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/8/30 04:00
 *
 * 46. Permutations
 *
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 * Input: [1,2,3]
 * Output:
 * [
 *  [1,2,3],
 *  [1,3,2],
 *  [2,1,3],
 *  [2,3,1],
 *  [3,1,2],
 *  [3,2,1]
 * ]
 */
public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
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
            if (used[i]) continue;

            used[i] = true;
            List<Integer> newTmp = new LinkedList<>(tmp);
            newTmp.add(nums[i]);
            dfs(results, nums, used, newTmp);
            used[i] = false;
        }
    }

    public List<List<Integer>> permute_review20200720(int[] nums) {
        return new Helper20200720(nums).res;
    }

    private static class Helper20200720 {
        int [] nums;
        int n;
        List<List<Integer>> res = new ArrayList<>();

        Helper20200720(int[] nums) {
            this.nums = nums;
            this.n = nums.length;
            dfs(new ArrayList<>(), new HashSet<>());
        }

        private void dfs(List<Integer> tmp, Set<Integer> usedIdx) {
            if (usedIdx.size() == n) {
                res.add(new ArrayList<>(tmp));
                return;
            }

            for (int i = 0; i < n; i++) {
                if (usedIdx.contains(i)) continue;

                Set<Integer> newUsedIdx = new HashSet<>(usedIdx);
                newUsedIdx.add(i);
                List<Integer> newTmp = new ArrayList<>(tmp);
                newTmp.add(nums[i]);
                dfs(newTmp, newUsedIdx);
            }
        }
    }

    @Test
    public void case1() {
        assertEquals(new LinkedList<>(asList(
                asList(1,2,3),
                asList(1,3,2),
                asList(2,1,3),
                asList(2,3,1),
                asList(3,1,2),
                asList(3,2,1)
        )), permute_review20200720(new int[]{1,2,3}));
    }
}
