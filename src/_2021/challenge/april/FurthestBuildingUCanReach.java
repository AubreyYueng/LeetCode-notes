package _2021.challenge.april;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Yiyun On 2021/4/26 19:42
 * 1642. Furthest Building You Can Reach
 */
public class FurthestBuildingUCanReach {

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        // tip1: 肯定是最大的jump用梯子🪜
        // tip2: 因此我们keep track最大jump和remaining sum就可以了

        // 创建min heap的priority queue，这样可以使最大的jump用的是梯子
        Queue<Integer> q = new PriorityQueue<>();
        for (int i = 1; i < heights.length; i++) {
            int jump = heights[i] - heights[i-1];
            if (jump <= 0) {
                continue;
            }
            q.add(jump);    // O(logN)
            if (q.size() <= ladders) continue;

            bricks -= q.remove();   // O(logN)
            // If this caused bricks to go negative, we can't get to i + 1
            if (bricks < 0) {
                return i-1;
            }
        }
        // If we got to here, this means we had enough materials to cover every climb.
        return heights.length - 1;
    }

}
