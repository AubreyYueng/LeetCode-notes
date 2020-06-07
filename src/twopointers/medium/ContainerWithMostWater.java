package twopointers.medium;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/6/6 19:50
 *
 * 11. Container With Most Water
 */
public class ContainerWithMostWater {

    // We can't use monotonic stack, reason refers to 84
    // We use two pointers instead
    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }

    @Test
    public void case1() {
        assertEquals(49, maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }

}
