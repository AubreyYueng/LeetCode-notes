package greedy.medium;

import java.util.LinkedList;

/**
 * Created by Yiyun On 2020/7/23 22:59
 *
 * 946. Validate Stack Sequences
 */
public class ValidateStackSequences {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        LinkedList<Integer> stack = new LinkedList<>();

        int j = 0;
        for (int p : pushed) {
            stack.push(p);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }

        return j == n;
    }

}
