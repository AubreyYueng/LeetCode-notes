package binary.search.hard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/5/21 20:50
 * 4. Median of Two Sorted Arrays
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n)
            // don't forget about "return"
            // if not, j could be illegal because j = half - i,
            // while half could be large and i could be too small or too large
            return findMedianSortedArrays(nums2, nums1);

        int l = 0;
        int r = m;
        int half = (m+n+1)/2;
        while (l <= r) {
            int i = (l+r+1) / 2;
            int j = half-i;
            if (j > 0 && i < m && nums1[i] < nums2[j-1])
                l = i;
            else if (i > 0 && j < n && nums1[i-1] > nums2[j])
                r = i-1;
            else {
                int leftMax = Integer.MIN_VALUE;
                if (i > 0) leftMax = nums1[i-1];
                if (j > 0) leftMax = Math.max(leftMax, nums2[j-1]);

                int rightMin = Integer.MAX_VALUE;
                if (i < m) rightMin = nums1[i];
                if (j < n) rightMin = Math.min(rightMin, nums2[j]);

                if ((n+m) % 2 == 1)
                    // Be aware of this condition: median not simply lie in odd/even part
                    return (i+j) > (n+m)/2 ? leftMax : rightMin;
                else
                    return (leftMax + rightMin) / 2.0;
            }
        }
        return 0d;
    }

    @Test
    public void case1() {
        assertEquals(2, findMedianSortedArrays(new int[]{1, 3}, new int[]{2}), 0.01);
    }

    @Test
    public void case2() {
        assertEquals(2.5, findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}), 0.01);
    }

    @Test
    public void case3() {
        assertEquals(1, findMedianSortedArrays(new int[]{}, new int[]{1}), 0.01);
    }

    @Test
    public void case4() {
        assertEquals(3, findMedianSortedArrays(new int[]{}, new int[]{1, 2, 3, 4, 5}), 0.01);
    }

    @Test
    public void case5() {
        assertEquals(3.5, findMedianSortedArrays(new int[]{1}, new int[]{2, 3, 4, 5, 6}), 0.01);
    }

}
