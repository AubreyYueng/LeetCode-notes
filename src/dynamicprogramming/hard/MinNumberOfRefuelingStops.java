package dynamicprogramming.hard;

/**
 * Created by Yiyun On 2020/7/23 20:29
 *
 * 871. Minimum Number of Refueling Stops
 */
public class MinNumberOfRefuelingStops {

    // dp[i]: the farthest location we can get to using i refueling stops,
    // in order to find the smallest i for which dp[i] >= target
    // init: dp[0] = startFuel
    // func: dp[t]=location_t, one can get to stations[i>=t]=(location_i, gas_i) if location_i > location_t,
    // then dp[t+1] = max(dp[t+1], location_t+gas_i).
    // Time: O(N^2)
    // The following codes are mostly learned from LC Solution
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        long[] dp = new long[n+1];
        dp[0] = startFuel;
        for (int i = 0; i < n; i++) {
            for (int t = i; t >= 0; t--) {
                if (dp[t] >= stations[i][0]) {
                    dp[t+1] = Math.max(dp[t+1], dp[t] + (long)stations[i][1]);
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            if (dp[i] >= target) return i;
        }
        return -1;
    }

}
