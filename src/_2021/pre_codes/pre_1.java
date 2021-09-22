package _2021.pre_codes;

import java.util.*;

/**
 * Created by Yiyun On 2021/6/1 23:44
 */
public class pre_1 {

    public void topKFrequent(int[] nums, int k) {
        // 1. build hash map : character and how often it appears
        Map<Integer, Integer> count = new HashMap<>();
        for (int n: nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // 2. init min-heap
        Queue<Integer> heap = new PriorityQueue<>(
                Comparator.comparing((Integer i) -> count.get(i))
                        .thenComparing((Integer i) -> -i)
        );

        // 2. keep k top frequent elements in the heap
        for (int n: count.keySet()) {
            heap.add(n);
            if (heap.size() > k) heap.poll();
        }

        // 3. print output
        for(int i = k - 1; i >= 0; --i) {
            System.out.println(heap.poll());
        }
    }
}
