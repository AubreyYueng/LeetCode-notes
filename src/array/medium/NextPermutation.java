package array.medium;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/6/25 03:55
 *
 * 31. Next Permutation
 * lexicographically: in dictionary order
 */
public class NextPermutation {

    public void nextPermutation_review20200706(int[] nums) {
        if (nums == null || nums.length <= 1)
            return;
        int n = nums.length;

        // 1. find the start of descending ordered sequence
        // Note: 1) should query reversely
        //       2) i-- even when nums[i]==nums[i+1](eg. input[5,1,1])
        int i = n-2;
        while (i >= 0 && nums[i] >= nums[i+1]) i--;

        // 2. swap nums[i] with the next larger number(querying reversely)
        // Note: don't forget this 'if' condition
        if (i >= 0) swapWithNextLarger(nums, i);

        // 3. reverse numbers from the peak
        int l = i+1;
        int r = n-1;
        while (l < r) {
            swap(nums, l++, r--);
        }
    }

    private void swapWithNextLarger(int[] nums, int i) {
        int j = nums.length;
        while (--j > i) {
            if (nums[j] > nums[i]) {
                swap(nums, i, j);
                break;
            }
        }
    }

    // permutations are in ascending order
    // eg. given 1324, then next should be 1342
    // eg. given 1342, because 42 is in descending order so 1342 is the largest permutation starts with "13",
    //     so we need to adjust "3", i.e. find a larger digit to replace "3" which is 1423 (the smallest permutation
    //     starts with "14" means numbers on its right are in ascending order).
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

    @Test
    public void case1() {
        int nums[] = new int[]{2, 1, 3};
        nextPermutation_review20200706(nums);
        assertEquals("[2, 3, 1]", Arrays.toString(nums));
    }

    @Test
    public void case2() {
        int nums[] = new int[]{1, 2, 3};
        nextPermutation_review20200706(nums);
        assertEquals("[1, 3, 2]", Arrays.toString(nums));
    }

    @Test
    public void case3() {
        int nums[] = new int[]{1, 1, 5};
        nextPermutation_review20200706(nums);
        assertEquals("[1, 5, 1]", Arrays.toString(nums));
    }

    @Test
    public void case4() {
        int nums[] = new int[]{2, 3, 1};
        nextPermutation_review20200706(nums);
        assertEquals("[3, 1, 2]", Arrays.toString(nums));
    }

    @Test
    public void case5() {
        int nums[] = new int[]{1, 3, 2};
        nextPermutation_review20200706(nums);
        assertEquals("[2, 1, 3]", Arrays.toString(nums));
    }

    @Test
    public void case6() {
        int nums[] = new int[]{3, 2, 1};
        nextPermutation_review20200706(nums);
        assertEquals("[1, 2, 3]", Arrays.toString(nums));
    }

    @Test
    public void case7() {
        int nums[] = new int[]{5, 1, 1};
        nextPermutation_review20200706(nums);
        assertEquals("[1, 1, 5]", Arrays.toString(nums));
    }

}
