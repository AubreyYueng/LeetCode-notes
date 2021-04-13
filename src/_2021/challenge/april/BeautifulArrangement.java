package _2021.challenge.april;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Yiyun On 2021/4/12 22:52
 * 526. Beautiful Arrangement
 */
public class BeautifulArrangement {

    public int countArrangement(int n) {
        List<Set<Integer>> hardcoded = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            hardcoded.add(new HashSet<>());
        }

        for (int i = 1; i <= n; i++) {
            int factor = 1;
            int num;
            while ((num = (factor++) * i) <= n) {
                hardcoded.get(i-1).add(num);
                hardcoded.get(num-1).add(i);
            }
        }
        for (int i = 0; i < hardcoded.size(); i++) {
            System.out.println("i: " + i + ": " + hardcoded.get(i));
        }

        int[] res = new int[]{0};
        dfs(new HashSet<>(), hardcoded, 0, res);
        return res[0];

    }

    private void dfs(Set<Integer> curr, List<Set<Integer>> hardcoded, int i, int[] res) {
        if (i == hardcoded.size()) {
            // System.out.println(curr);
            res[0]++;
            return;
        }

        for(int n: hardcoded.get(i)) {
            if (!curr.contains(n)) {
                curr.add(n);
                dfs(curr, hardcoded, i+1, res);
                curr.remove(n);
            }
        }
    }

}
