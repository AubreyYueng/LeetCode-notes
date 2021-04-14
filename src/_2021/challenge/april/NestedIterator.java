package _2021.challenge.april;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yiyun On 2021/4/13 08:57
 * 341. Flatten Nested List Iterator
 */
public class NestedIterator implements Iterator<Integer> {

    LinkedList<Iterator<NestedInteger>> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.stack = new LinkedList<>();
        // 忽略非integer时空的list
        revise(nestedList);
        System.out.println(nestedList.size());
        if (!nestedList.isEmpty())
            stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        clear();
        // push 嵌套的 iterators
        Iterator<NestedInteger> currIter = stack.peek();
        NestedInteger curr = currIter.next();
        while (!curr.isInteger()) {
            List<NestedInteger> list = curr.getList();
            stack.push(curr.getList().iterator());
            currIter = stack.peek();
            curr = currIter.next();
        }
        return curr.getInteger();
    }

    @Override
    public boolean hasNext() {
        clear();
        // System.out.println(stack.isEmpty());
        return !stack.isEmpty();
    }

    // 清除栈顶已经遍历完的iterators
    private void clear() {
        Iterator<NestedInteger> currIter;
        while (!stack.isEmpty() && !(currIter = stack.peek()).hasNext()) {
            stack.pop();
        }
    }

    private void revise(List<NestedInteger> nestedList) {
        for (NestedInteger ni: nestedList) {
            if (!ni.isInteger()) {
                revise(ni.getList());
            }
        }
        nestedList.removeIf(n -> !n.isInteger() && n.getList().isEmpty());
    }

    private static class NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return false;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return 0;
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return null;
        }

    }



}
