package dynamicprogramming.medium;

/**
 * Created by Yiyun On 2020/6/10 02:58
 *
 * 96. Unique Binary Search Trees
 */
public class UniqueBinarySearchTrees {

    // State: dp[i], numTrees(i)
    // init: [0]=[1]=1, according to the func
    // func: \sum_{j=0}^{n-1} [j] * [n-j]
    // res: [n]
    public int numTrees(int n) {
        if (n < 1)
            return 0;
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;

        // i for store values 1...i
        for (int i = 2; i <= n; i++) {
            // j for head of [left] child in the tree with n=i
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i-j-1];
            }
        }
        return dp[n];
    }

}
