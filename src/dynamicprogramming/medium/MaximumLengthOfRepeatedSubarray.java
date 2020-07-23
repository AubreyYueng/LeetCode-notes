package dynamicprogramming.medium;

/**
 * Created by Yiyun On 2020/7/22 23:02
 *
 * 718. Maximum Length of Repeated Subarray
 */
public class MaximumLengthOfRepeatedSubarray {

    // state: dp[i][j] longest common prefix of A[i:] and B[j:]
    // fun: when A[i]==B[j], dp[i][j] = dp[i+1][j+1]+1
    public int findLength(int[] A, int[] B) {
        int aLen = A.length, bLen = B.length;
        int res = 0;
        int[][] dp = new int[aLen+1][bLen+1];
        for (int i = aLen-1; i >= 0; --i) {
            for (int j = bLen-1; j >= 0; --j) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i+1][j+1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }

}
