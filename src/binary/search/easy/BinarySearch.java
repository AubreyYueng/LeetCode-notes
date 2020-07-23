package binary.search.easy;

/**
 * Created by Yiyun On 2020/7/22 23:16
 *
 * 704. Binary Search
 */
public class BinarySearch {

    public int search(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        while (l < r) {
            int mid = (l+r) / 2;
            if (nums[mid] == target) return mid;
            if (target < nums[mid])
                r = mid;
            else
                l = mid + 1;
        }
        return nums[l] == target ? l : -1;
    }

}
