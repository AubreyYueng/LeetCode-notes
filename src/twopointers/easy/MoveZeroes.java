package twopointers.easy;

/**
 * Created by Yiyun On 2020/6/9 19:34
 *
 * 283. Move Zeroes
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        int storeIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (cur != 0) {
                swap(nums, i, storeIdx++);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
