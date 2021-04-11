package _2021.challenge.march;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Yiyun On 2021/4/10 23:36
 */
public class MinSidewayJumps {

    public int minSideJumps(int[] obstacles) {
        int[] dp = new int[]{-1, 0, -1};

        for (int i = 1; i < obstacles.length; i++) {
            int[] curr = new int[3];

            int obs = obstacles[i];
            if (obs != 0)
                curr[obs-1] = -1;

            int lastMin = Integer.MAX_VALUE;
            for (int j = 0; j < 3; j++) {
                if (dp[j] == -1)
                    continue;
                lastMin = Math.min(dp[j], lastMin);
            }

            for (int j = 0; j < 3; j++) {
                if (curr[j] == -1)
                    continue;
                if (dp[j] != -1)
                    curr[j] = Math.min(dp[j], lastMin+1);
                else
                    curr[j] = lastMin + 1;
            }

            dp = curr;
            System.out.println(Arrays.toString(dp));
        }

        int res = Integer.MAX_VALUE;
        for (int n: dp) {
            if (n == -1) continue;

            res = Math.min(res, n);
        }
        return res;
    }

    @Test
    public void case1() {
        System.out.println(minSideJumps(new int[]{0,1,2,3,0}));
    }
}
