package backtracking.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
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
 */
public class Subsets {

    // The following codes are mostly copied from the LC solution.
    // Note the following way of solving zero left padding
    public List<List<Integer>> subsets_bitmanipulation(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;

        int nthBit = 1 << n;
        for (int i = 0; i < (int)Math.pow(2, n); i++) {
            // generate bitmask, from 0..00 to 1..11
            String bitmask = Integer.toBinaryString(i | nthBit).substring(1);

            // append subset corresponding to that bitmask
            List<Integer> curr = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (bitmask.charAt(j) == '1')
                    curr.add(nums[j]);
            }
            res.add(curr);
        }
        return res;
    }

    public List<List<Integer>> subsets_review20200705(int[] nums) {
        if (nums == null)
            return Collections.emptyList();
        Helper_review20200705 h = new Helper_review20200705(nums);
        h.dfs(0, new ArrayList<>());
        return h.res;
    }

    private static class Helper_review20200705 {
        private int[] nums;
        private int n;
        private List<List<Integer>> res;

        private Helper_review20200705(int[] nums) {
            this.nums = nums;
            this.n = nums.length;
            this.res = new ArrayList<>();
        }

        private void dfs(int st, List<Integer> tmp) {
            res.add(new ArrayList<>(tmp));

            for (int i = st; i < n; i++) {
                List<Integer> nextTmp = new ArrayList<>(tmp);
                nextTmp.add(nums[i]);
                dfs(i+1, nextTmp);
            }
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) return res;

        dfs(0, nums, new LinkedList<>(), res);
        return res;
    }

    /**
     *       1 -> 1,2 -> 1,2,3
     *            1,2 ___/pop3
     *      1 ___/pop2
     *      1 ----------->1,3
     *    _______________/pop1,pop3
     *            2  ->  2,3
     */
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
        System.out.println(subsets_review20200705(new int[]{1, 2, 3}));
    }

}
