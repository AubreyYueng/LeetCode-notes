package sort.medium;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/6/12 21:16
 *
 * 75. Sort Colors
 */
public class SortColors {

    // Require in-place: ring a bell of "Two Pointer"!
    public void sortColors(int[] nums) {
        new Helper(nums).sort();
    }

    private static class Helper {
        private int[] nums;     // contains only repeated 0, 1, 2

        private int leftMostZero;
        private int rightMostTwo;

        private Helper(int[] nums) {
            this.nums = nums;
            this.leftMostZero = 0;
            this.rightMostTwo = nums.length-1;
        }

        // sort in order of 0-1-2
        private void sort() {
            int i = 0;
            while (i <= rightMostTwo) {
                int curr = nums[i];
                if (curr == 2)   // DON'T i++; because nums[i] could still be 2,1,0...
                    swap(i, rightMostTwo--);
                else {
                    if (curr == 0)
                        swap(i, leftMostZero++);
                    i++;
                }
            }
        }

        private void swap(int i, int j) {
            int tmp = nums[j];
            nums[j] = nums[i];
            nums[i] = tmp;
        }
    }

    @Test
    public void case1() {
        int[] arr = new int[]{2,0,2,1,1,0};
        sortColors(arr);
        assertEquals("[0, 0, 1, 1, 2, 2]", Arrays.toString(arr));
    }

    @Test
    public void case2() {
        int[] arr = new int[]{2,0,1};
        sortColors(arr);
        assertEquals("[0, 1, 2]", Arrays.toString(arr));
    }

    @Test
    public void case3() {
        int[] arr = new int[]{1,2,0};
        sortColors(arr);
        assertEquals("[0, 1, 2]", Arrays.toString(arr));
    }

    @Test
    public void case4() {
        int[] arr = new int[]{2,1,2};
        sortColors(arr);
        assertEquals("[1, 2, 2]", Arrays.toString(arr));
    }
}
