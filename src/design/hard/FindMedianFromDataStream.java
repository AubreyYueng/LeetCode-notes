package design.hard;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/18 17:16
 *
 * 295. Find Median from Data Stream
 */
public class FindMedianFromDataStream {

    // The following codes are mostly copied from LC Solution
    // Here we use insertion sort. Time: O(N), Space: O(N)
    // Other approaches: 1. Two Heaps. 2. Multiset and Two Pointers(Self-balancing BST) TODO
    private static class MedianFinder {
        /** initialize your data structure here. */

        List<Integer> list;

        public MedianFinder() {
            this.list = new ArrayList<>();
        }

        public void addNum(int num) {
            int pos = binarySearch(num);
            list.add(pos, num);
        }

        public double findMedian() {
            int len = list.size();
            int mid = len / 2;
            if (len %2 == 1)
                return list.get(mid);
            else
                return (list.get(mid-1) + list.get(mid)) / 2.0;
        }

        // return inserted position
        private int binarySearch(int num) {
            int l = 0;
            int r = list.size();    // Notice: max r is len, don't minus 1 here!!!
            while (l < r) {
                int mid = (l + r) / 2;
                int midVal = list.get(mid);
                if (num == midVal)
                    return mid;
                if (num < midVal) r = mid;
                else l = mid + 1;
            }
            return l;
        }

    }

    @Test
    public void case1() {
        MedianFinder m = new MedianFinder();
        m.addNum(1);
        m.addNum(2);
        m.addNum(3);
        assertEquals(2, m.findMedian(), 0.01);
    }

}
/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */