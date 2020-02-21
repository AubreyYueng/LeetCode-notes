package twopointers.hard;

/**
 * Created by Yiyun On 2020/2/20 22:00
 *
 * 42. Trapping Rain Water
 * https://leetcode.com/problems/trapping-rain-water/
 */
public class TrappingRainWater {

    public int trap(int[] height) {
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

}
