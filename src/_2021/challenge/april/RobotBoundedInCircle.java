package _2021.challenge.april;

import java.util.function.Consumer;

/**
 * Created by Yiyun On 2021/4/12 21:39
 *
 * 1041. Robot Bounded In Circle
 */
public class RobotBoundedInCircle {

    public boolean isRobotBounded(String instructions) {
        int[][] dirs = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}};
        int i = 0;      // curr dir index
        int[] pos = {0, 0};     // curr position
        Consumer<Integer> move = id -> {
            int[] dir = dirs[id];
            pos[0] += dir[0];
            pos[1] += pos[1];
        };

        for (char ch: instructions.toCharArray()) {
            if (ch == 'G') {
                move.accept(i);
            } else {
                if (ch == 'R') i += 1;
                else i -= 1;

                if (i == -1) i = 4;
                else if (i == 5) i = 0;

                move.accept(i);
            }
        }

        return pos[0] == 0 && pos[1] == 0;
    }
}
