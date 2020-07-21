package design.medium;

import java.util.Random;

/**
 * Created by Yiyun On 2020/7/20 19:53
 *
 * 384. Shuffle an Array
 */
public class ShuffleAnArray {

    // Q: Shuffle a set of numbers without duplicates.
    // Approach 1: Fisher-Yates Algorithm
    // The following codes are basically copied from LC Solution
    private static class Solution {
        private int[] array;
        private int[] original;
        private int n;

        Random rand = new Random();

        public Solution(int[] nums) {
            array = nums;
            original = nums.clone();
            this.n = array.length;
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            array = original;
            original = original.clone();
            return original;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            for (int i = 0; i < n; i++) {
                swapAt(i, randRange(i, n));
            }
            return array;
        }

        private void swapAt(int i, int j) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }

        private int randRange(int min, int max) {
            return rand.nextInt(max-min) + min;
        }

    }

}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
