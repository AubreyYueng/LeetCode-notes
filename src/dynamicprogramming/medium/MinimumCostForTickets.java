package dynamicprogramming.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yiyun On 2020/1/28 22:39
 *
 * 983. Minimum Cost For Tickets
 * https://leetcode.com/problems/minimum-cost-for-tickets/
 */
public class MinimumCostForTickets {

    public int mincostTickets(int[] days, int[] costs) {
        // f(n)表示第n天到最后一天的花费
        //(这里要打消从前面开始的惯性思维, 即第一天到第n天这种惯性思路)
        // f(d(n)) = min(f(d(n)+1)+c(0), f(d(n)+7)+c(1), f(d(n)+30)+c(2))
        // f(x not in days) = f(x+1)
        Set<Integer> dSet = new HashSet<>();
        for (int d : days) dSet.add(d);
        // day 从 1 开始, 366可以避免要f[day-1]这样取值
        Integer[] f = new Integer[366];
        return dp(1, dSet, costs, f);
    }

    private int dp(int day, Set<Integer> days, int[] costs, Integer[] f) {
        if (day > 365)
            return 0;
        if (f[day] != null) return f[day];
        if (!days.contains(day))
            return dp(day+1, days, costs, f);
        int res = Math.min(
                dp(day+1, days, costs, f) + costs[0],
                Math.min(
                        dp(day+7, days, costs, f) + costs[1],
                        dp(day+30, days, costs, f) + costs[2]
                )
        );
        f[day] = res;
        return res;
    }

}
