package dynamicprogramming.medium;

/**
 * Created by Yiyun On 2020/2/4 21:13
 *
 * 416. Partition Equal Subset Sum
 * https://leetcode.com/problems/partition-equal-subset-sum/
 *
 * 0-1 knapsack problem
 */
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n: nums) sum+=n;
        int half = sum / 2;
        if (half * 2 != sum) return false;

        // f(i, j): boolean, whether specific sum j can be gotten from the first i numbers;
        // f(i, j) = f(i-1, j) || f(i-1, j-nums[i])

        int len = nums.length;
        boolean[][] res = new boolean[len+1][half+1];
        for(int n: nums) {
            if (n <= half)
                res[1][n] = true;
        }
        res[0][0] = true;

        for (int i = 1; i <= len; i++) {
            int curr = nums[i-1];
            for (int j = 1; j <= half; j++) {
                int rest = j-curr;
                res[i][j] = rest < 0 ? res[i-1][j] : res[i-1][j]||res[i-1][rest];
            }
        }

        return res[len][half];
    }


}
