package twopointers.easy;

/**
 * Created by Yiyun On 2020/7/1 20:32
 *
 * 88. Merge Sorted Array
 */
public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] tmp = new int[m];
        System.arraycopy(nums1, 0, tmp, 0, m);

        int p1 = 0;
        int p2 = 0;
        int pTmp = 0;

        while (pTmp < m && p2 < n) {
            nums1[p1++] = tmp[pTmp] < nums2[p2] ? tmp[pTmp++] : nums2[p2++];
        }

        if (pTmp < m)
            System.arraycopy(tmp, pTmp, nums1, pTmp+p2, m+n-pTmp-p2);
        if (p2 < n)
            System.arraycopy(nums2, p2, nums1, pTmp+p2, m+n-pTmp-p2);
    }

}
