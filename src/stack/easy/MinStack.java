package stack.easy;

import java.util.LinkedList;

/**
 * Created by Yiyun On 2020/6/9 17:57
 *
 * 155. Min Stack
 */
public class MinStack {

    private LinkedList<Integer> mainStack = new LinkedList<>();
    private LinkedList<int[]> minStack = new LinkedList<>();    // [current_min, count_in_main_stack]

    /** initialize your data structure here. */
    // Here we use an improved two-stack
    public MinStack() {
    }

    public void push(int x) {
        mainStack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(new int[]{x, 1});
            return;
        }

        int currMin = minStack.peek()[0];
        if (currMin == x)
            minStack.peek()[1]++;
        else if (currMin > x)
            minStack.push(new int[]{x, 1});
    }

    public void pop() {
        int top = mainStack.pop();
        int[] currMin = minStack.peek();
        if (top == currMin[0]) {
            if (currMin[1] == 1)
                minStack.pop();
            else
                minStack.peek()[1]--;
        }
    }

    public int top() {
        return mainStack.peek();
    }

    public int getMin() {
        return minStack.peek()[0];
    }

}
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
