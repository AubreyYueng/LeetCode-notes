package backtracking.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yiyun On 2020/7/4 13:18
 *
 * 40. Combination Sum II
 */
public class CombinationSumII {

    private int[] candidate;
    private int len;
    private List<List<Integer>> res;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidate = candidates;
        this.len = candidates.length;
        this.res = new ArrayList<>();

        dfs(target, 0, new ArrayList<>());
        return res;
    }

    private void dfs(int target, int st, List<Integer> tmp) {
        if (target == 0 && !tmp.isEmpty()) {
            res.add(tmp);
            return;
        }

        for (int i = st; i < len; i++) {
            int curr = candidate[i];
            if (i > st && curr == candidate[i-1])
                continue;

            if (curr != target && curr >= target/2 + 1) {
                continue;
            }

            List<Integer> newTmp = new ArrayList<>(tmp);
            newTmp.add(curr);
            dfs(target-curr, i+1, newTmp);
        }
    }

    @Test
    public void case1() {
        System.out.println(combinationSum2(new int[]{2,5,2,1,2}, 5));
    }

}
