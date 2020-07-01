package array.medium;

/**
 * Created by Yiyun On 2020/6/25 03:55
 *
 * 31. Next Permutation
 * lexicographically: in dictionary order
 */
public class NextPermutation {

    // permutations are in ascending order
    // eg. given 1324, then next should be 1342
    // eg. given 1342, because 42 is in descending order so 1342 is the largest permutation starts with "13",
    //     so we need to adjust "3", i.e. find a larger digit to replace "3" which is 1423 (the smallest permutation
    //     starts with "14" means numbers on its right are in ascending order.
    // Require: in-place
    public void nextPermutation(int[] nums) {
        // find the number that needs increasing
        int i = nums.length-2;
        while (i >= 0 && nums[i+1] <= nums[i])
            i--;

        // replace nums[i] with next bigger numbs[j] on its right side(which are already in descending order)
        // don't forget i >= 0 !!! (i < 0 means nums is already in descending order)
        if (i >= 0) {
            int j = nums.length - 1;
            while (j > i && nums[j] <= nums[i])
                j--;
            swap(nums, i, j);
        }

        // reverse numbers right off nums[i] in ascending order
        reverse(nums, i+1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int st) {
        int ed = nums.length-1;
        while (st < ed) {
            swap(nums, st, ed);
            st++;
            ed--;
        }
    }

}
