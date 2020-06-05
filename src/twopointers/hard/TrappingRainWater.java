package twopointers.hard;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/2/20 22:00
 *
 * 42. Trapping Rain Water
 * https://leetcode.com/problems/trapping-rain-water/
 */
public class TrappingRainWater {

    // Using stack(refer to 84. Largest Rectangle in Histogram)
    // one misunderstanding: Time complexity is O(n) NOT O(n^2)
    // Here we use a monotonically decreasing stack.
    public int trap(int[] height) {
        if (height == null || height.length == 0)
            return 0;

        LinkedList<Integer> stack = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            int curHeight = height[i];
            while (!stack.isEmpty() && curHeight > height[stack.peek()]) {
                int boundedIdx = stack.pop();
                int boundedHeight = height[boundedIdx];

                if (stack.isEmpty())
                    break;

                int prevBoundIdx = stack.peek();
                int previousBoundHeight = height[prevBoundIdx];
                int wid = i - 1 - prevBoundIdx;
                int h = Math.min(curHeight, previousBoundHeight) - boundedHeight;
                int area = wid * h;
                res += area;
            }

            stack.push(i);
        }

        return res;
    }

    public int trap_twopointers(int[] height) {
        if (height == null || height.length == 0)
            return 0;

        // this two pointer way is tricky. There're also stack and dp solutions
        int l = 0;
        int r = height.length-1;
        int lMx = height[l];
        int rMx = height[r];
        int res = 0;
        while (l < r) {
            int lH = height[l];
            int rH = height[r];
            if (lH < rH) {
                if (lH > lMx) lMx = lH;
                res += lMx-lH;
                l++;
            } else {
                if (rH > rMx) rMx = rH;
                res += rMx-rH;
                r--;
            }
        }
        return res;
    }

    @Test
    public void case1() {
        assertEquals(6, trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }

}
