package bytedance;

/**
 * Created by Yiyun On 2020/3/25 18:52
 *
 * 440. K-th Smallest in Lexicographical Order
 * https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/
 */
public class KthSmallestLexicographicalOrder {

    // Actually this is a denary tree (each node has 10 children).
    // Find the kth element is to do a k steps preorder traverse of the tree.
    public int findKthNumber(int n, int k) {
        int rest = k-1;     // watch out the initialization
        int curr = 1;
        while (rest > 0) {
            int steps = calSteps(n, curr, curr+1);
            if (steps <= rest) {
                curr++;
                rest-=steps;
            } else {
                curr*=10;
                rest--;
            }
        }
        return curr;
    }

    // calculate steps between curr & curr+1
    private int calSteps(int n, long n1, long n2) {
        int steps = 0;
        while (n >= n1) {
            if (n >= n2)
                steps += n2-n1;
            else
                steps += n+1-n1;
            n1 *= 10;
            n2 *= 10;
        }
        return steps;
    }

}
