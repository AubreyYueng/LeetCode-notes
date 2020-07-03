package twopointers.easy;

/**
 * Created by Yiyun On 2020/7/3 17:57
 *
 * 26. Remove Duplicates from Sorted Array
 */
public class RemoveDuplicatesFromSortedArray {

    // Require: in-place
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j];
            }
        }
        return i+1;
    }

}
