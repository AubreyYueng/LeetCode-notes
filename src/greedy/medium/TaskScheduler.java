package greedy.medium;

import java.util.*;

/**
 * Created by Yiyun On 2020/6/10 23:35
 *
 * 621. Task Scheduler
 */
public class TaskScheduler {

    // Use Greedy method to run the most frequently recurrent test, by placing the task
    // with largest count first, thus we need PQ in order to update the count while
    // retrieving the largest count task in O(1) time(and we don't need to store the task name)
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> cntMap = new HashMap<>();
        for (char t : tasks) {
            cntMap.put(t, cntMap.getOrDefault(t, 0) + 1);
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.addAll(cntMap.values());

        // Fact: the task order in every n interval stays the same
        int res = 0;
        while (!maxHeap.isEmpty()) {
            List<Integer> subTasks = new LinkedList<>();    // tasks that can run in n intervals
            for (int i = 0; i <= n; i++) {
                if (!maxHeap.isEmpty()) {
                    subTasks.add(maxHeap.poll()-1);
                }
            }

            for (Integer subT : subTasks) {
                if (subT > 0)
                    maxHeap.add(subT);
            }


            res += maxHeap.isEmpty() ? subTasks.size() : n+1;
        }
        return res;
    }

}
