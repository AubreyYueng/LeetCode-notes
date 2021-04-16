package _2021.challenge.april;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Created by Yiyun On 2021/4/15 15:53
 * 895. Maximum Frequency Stack
 */
public class FreqStack {

    Map<Integer, Integer> freqMap;
    TreeMap<Integer, Stack<Integer>> freqStack;

    public FreqStack() {
        freqMap = new HashMap<>();
        freqStack = new TreeMap<>();
    }

    public void push(int val) {
        int freq = freqMap.getOrDefault(val, 0) + 1;
        freqMap.put(val, freq);

        Stack<Integer> s = freqStack.getOrDefault(freq, new Stack<>());
        s.push(val);
        freqStack.put(freq, s);
    }

    public int pop() {
        Stack<Integer> stack = freqStack.lastEntry().getValue();
        int val = stack.pop();
        freqMap.put(val, freqMap.get(val)-1);

        // 注意栈为空时，要把这个freq的key drop 掉
        if(stack.isEmpty()) {
            freqStack.pollLastEntry();
        }

        return val;
    }

}
