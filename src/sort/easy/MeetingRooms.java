package sort.easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Yiyun On 2020/6/4 15:00
 *
 * 252. Meeting Rooms
 */
public class MeetingRooms {

    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparing(inter -> inter[0]));
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i-1][1])
                return false;
        }
        return true;
    }

}
