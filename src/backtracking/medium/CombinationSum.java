package backtracking.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yiyun On 2020/6/9 18:39
 *
 * 39. Combination Sum
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        DfsHelper helper = new DfsHelper(candidates);
        helper.dfs(new LinkedList<>(), 0, target);
        return helper.res;
    }

    private static class DfsHelper {

        private List<List<Integer>> res = new LinkedList<>();
        private int[] candidates;

        private int len;

        private DfsHelper(int[] candidates) {
            this.candidates = candidates;
            Arrays.sort(this.candidates);
            this.len = this.candidates.length;
        }

        private void dfs(List<Integer> state, int st, int rest) {
            if (rest == 0 && !state.isEmpty())
                this.res.add(state);

            if (st >= len)
                return;

            for (int i = st; i < len; i++) {
                int curr = candidates[i];
                if (curr > rest)
                    break;

                List<Integer> newState = new LinkedList<>(state);
                newState.add(curr);
                dfs(newState, i, rest-curr);
            }
        }
    }

}
