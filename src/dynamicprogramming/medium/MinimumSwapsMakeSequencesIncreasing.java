package dynamicprogramming.medium;

/**
 * Created by Yiyun On 2020/2/5 23:28
 *
 * 801. Minimum Swaps To Make Sequences Increasing
 * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/
 *
 */
public class MinimumSwapsMakeSequencesIncreasing {

    /**
     * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/discuss/119835/Java-O(n)-DP-Solution:
     *
     * "Remember that question limits only to swap the same indices, that leaves you with only 2 options at each index
     * - either you swap(2 possibilities) or you don't swap(2 possibilities). And that is what exactly is conditioned
     * with here with if statements."
     *
     * "Notice that every ith swapRecord and fixRecord is only relevant with the previous one. So the algorithm should
     * be optimized to an O(1) space version."
     */
    public int minSwap(int[] A, int[] B) {
        // s for swa[]: min swaps if we swap i-th elements;
        // f for fix: min swaps if we don't swap i-th elements;
        int s = 1;
        int f = 0 ;
        int len = A.length;
        for (int i = 1; i < len; i++) {
            int tmpS = len+1;
            int tmpF = len+1;

            int ap = A[i-1];
            int bp = B[i-1];
            int a = A[i];
            int b = B[i];
            if (ap < a && bp < b) {
                tmpS = Math.min(tmpS, s+1);
                tmpF = Math.min(tmpF, f);
            }
            if (ap < b && bp < a) {
                tmpS = Math.min(tmpS, f+1);
                tmpF = Math.min(tmpF, s);
            }

            s = tmpS;
            f = tmpF;
        }

        return Math.min(s, f);
    }

}
