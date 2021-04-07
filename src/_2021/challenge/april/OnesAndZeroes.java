package _2021.challenge.april;

/**
 * Created by Yiyun On 2021/4/7 08:18
 *
 * Ones and Zeroes
 */
public class OnesAndZeroes {

    public int findMaxForm(String[] strs, int m, int n) {
        // [i][j] = max([i][j], [i-0's][j-0's]+1)
        // 用小的更新大的是 up to bottom, 否则这个小值不是上一个epoch的值，而是在这一个epoch被更新过了
        int[][] dp = new int[m+1][n+1];

        for (String s: strs) {
            int a = 0;  // 0
            int b = 0;  // 1
            for (char c: s.toCharArray()) {
                if (c == '0') a++;
                else b++;
            }

            if (a > m || b > n) continue;
            for (int i = m; i >= a; i--) {
                for (int j = n; j >= b; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-a][j-b]+1);
                }
            }
        }

        return dp[m][n];
    }

}
