package queue.priority.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Yiyun On 2020/6/4 16:53
 *
 * 253. Meeting Rooms II
 */
public class MeetingRoomsII {

    // Equivalent to finding at most how many people are in a single room simultaneously
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;

        int len = intervals.length;
        int[] st = new int[len];
        int[] ed = new int[len];
        for (int i = 0; i < intervals.length; i++) {
            st[i] = intervals[i][0];
            ed[i] = intervals[i][1];
        }
        Arrays.sort(st);
        Arrays.sort(ed);

        int edIdx = 0;
        int res = 0;
        for (int s : st) {
            if (s < ed[edIdx])
                res++;
            else
                edIdx++;
        }

        return res;
    }


    // using Priority Queue
    public int minMeetingRooms_PQ(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;

        // sort by start time
        Arrays.sort(intervals, Comparator.comparing(i -> i[0]));

        // its comparator is by integer, so we don't need to define one
        PriorityQueue<Integer> allocator = new PriorityQueue<>();
        allocator.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            int[] inter = intervals[i];
            if (inter[0] >= allocator.peek())
                allocator.poll();

            // cases are: updating the peak, or arrange a new meeting room
            allocator.add(inter[1]);
        }

        return allocator.size();
    }

}
