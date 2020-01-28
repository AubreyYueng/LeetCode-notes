package dynamicprogramming.easy;

/**
 * Created by Yiyun On 2020/1/27 20:36
 *
 * 746. Min Cost Climbing Stairs
 * https://leetcode.com/problems/min-cost-climbing-stairs/
 */
public class MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0)
            return 0;
        int res = cost[0];
        int total = cost.length;

        // initialize result array
        int[] arr = new int[total];
        arr[0] = cost[0];
        if (total > 1)
            arr[1] = cost[1];
        else
            return arr[0];

        for (int i = 2; i < total; i++) {
            arr[i] = Math.min(arr[i-2], arr[i-1]) + cost[i];
        }

        return Math.min(arr[total-1], arr[total-2]);
    }

}
