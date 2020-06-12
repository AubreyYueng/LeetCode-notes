package graph.medium;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by Yiyun On 2020/6/11 23:51
 *
 * 207. Course Schedule
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0)
            return true;

        Map<Integer, Integer> preCnt = new HashMap<>();     // course -> pre courses
        Map<Integer, List<Integer>> nextList = new HashMap<>();     // course -> next courses
        for (int[] pre : prerequisites) {
            preCnt.put(pre[0], preCnt.getOrDefault(pre[0], 0) + 1);
            preCnt.putIfAbsent(pre[1], 0);
            nextList.putIfAbsent(pre[0], new ArrayList<>());
            nextList.putIfAbsent(pre[1], new ArrayList<>());
            nextList.get(pre[1]).add(pre[0]);
        }

        LinkedList<Integer> queue = new LinkedList<>();
        preCnt.forEach((k, v) -> {
            if (v == 0)
                queue.offer(k);
        });

        while (!queue.isEmpty()) {
            Integer pre = queue.poll();
            for (Integer next : nextList.get(pre)) {
                preCnt.put(next, preCnt.get(next)-1);
                if (preCnt.get(next) == 0)
                    queue.offer(next);
            }
        }

        return preCnt.values().stream().allMatch(cnt -> cnt==0);
    }

    @Test
    public void case1() {
        assertTrue(canFinish(2, new int[][]{{1, 0}}));
    }

}
