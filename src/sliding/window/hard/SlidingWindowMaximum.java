package sliding.window.hard;

/**
 * Created by Yiyun On 2020/2/17 23:32
 * 239. Sliding Window Maximum
 * https://leetcode.com/problems/sliding-window-maximum/
 */
public class SlidingWindowMaximum {

    // Below is an intricate dp problem
    // Time complexity : O(N), since all we do is 3 passes along the array of length N.
    // Space complexity : O(N) to keep left and right arrays of length N, and output array of length N - k + 1.

    // If we use Deque,
    // Time complexity : O(N), since each element is processed exactly twice - it's index added and then removed from the deque.
    // Space complexity : O(N), since O(N−k+1) is used for an output array and O(k) for a deque.
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        // res[i] = max(down[i], on[i+j-1])
        int [] left = new int[n];
        left[0] = nums[0];
        int [] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            if (i % k == 0)
                left[i] = nums[i];
            else
                left[i] = Math.max(left[i - 1], nums[i]);

            int j = n - i - 1;
            if ((j + 1) % k == 0)
                right[j] = nums[j];
            else
                right[j] = Math.max(right[j + 1], nums[j]);
        }

        int [] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++)
            output[i] = Math.max(left[i + k - 1], right[i]);

        return output;
    }

}
