package dynamicprogramming.easy;

/**
 * Created by Yiyun On 2020/2/2 16:40
 * 70. Climbing Stairs
 * https://leetcode.com/problems/climbing-stairs/
 */
public class ClimbingStairs {

    public int climbStairs(int n) {
        // f(n) = f(n-1)+f(n-2)
        int[] res = new int[n+1];
        res[0] = 1;
        res[1] = 1;
        for (int i = 2; i <= n; i++) {
            res[i] = res[i-1]+res[i-2];
        }
        return res[n];
    }

}
