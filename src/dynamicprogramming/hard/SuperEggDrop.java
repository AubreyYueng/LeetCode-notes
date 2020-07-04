package dynamicprogramming.hard;

/**
 * Created by Yiyun On 2020/7/3 21:08
 *
 * 887. Super Egg Drop
 */
public class SuperEggDrop {

    // Solution 1: Brute Force
    //  1. status: (K, N): K eggs and N floors left
    //  2. func:
    //  suppose drop an egg on X floor
    //     1) breaks:    (K, N) = 1 + (K-1, N-1)
    //     2) survives:  (K, N) = 1 + (K, N-X)
    //  which gives us:
    //     (N, K) = min { max{(K-1, N-1), (K, N-1)} | 1 <= X <= N}
    //
    // Solution 2:
    //  1. status: (m, k): given k eggs and m moves, what is the maximum number of floors we can check.
    //  2. func: suppose we take a move to a floor, then there are (m-1) moves left.
    //     1) breaks:    we can check (m-1, k-1) floors.
    //     2) survives:  we can check (m-1, k) floors
    //  "the number of floors we can check" should include the two conditions above, thus we have:
    //  (m, k) = 1 + (m-1, k-1) + (m-1, k)
    //  3. returns m, once (m, k) >= N
    // Time: 1. running: O(KlogN), because (m, k) is the number of combinations(according to the func: (m) ≈ 2(m-1)),
    //       2. declaring array: O(K) if 1D, O(NK) if 2D.
    //       overall: O(KlogN) if 1D, O(NK) if 2D.
    // Space: O(K) if 1D, O(NK) if 2D.

    // Copied from the most voted discussion.
    public int superEggDrop_2D(int K, int N) {
        int[][] dp = new int[N + 1][K + 1];
        int m = 0;
        while (dp[m][K] < N) {
            ++m;
            for (int k = 1; k <= K; ++k)
                dp[m][k] = dp[m - 1][k - 1] + dp[m - 1][k] + 1;
        }
        return m;
    }

    // Optimized 1D solution, also copied from the most voted discussion
    public int superEggDrop_1D(int K, int N) {
        int dp[] = new int[K + 1], m = 0;
        for (m = 0; dp[K] < N; ++m)
            for (int k = K; k > 0; --k)
                dp[k] += dp[k - 1] + 1;
        return m;
    }

}
