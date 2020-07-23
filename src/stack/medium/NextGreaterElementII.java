package stack.medium;

import java.util.Stack;

/**
 * Created by Yiyun On 2020/7/22 20:06
 *
 * 503. Next Greater Element II
 */
public class NextGreaterElementII {

    // It's a circular array, to find the next greater, we use stack. Time: O(N)
    // The following codes are learned from LC Solution
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * n - 1; i >= 0; i--) {
            int idx = i % n;
            // numbers in stack are previously pushed.
            // we can pop numbers in stack which are smaller than curr because they're useless.
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[idx]) {
                stack.pop();
            }
            res[idx] = stack.isEmpty() ? -1 : nums[stack.peek()];
            stack.push(idx);
        }
        return res;
    }

}
