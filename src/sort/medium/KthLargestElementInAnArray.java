package sort.medium;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/6/7 09:01
 *
 * 215. Kth Largest Element in an Array
 */
public class KthLargestElementInAnArray {

    // TODO: other ways for implementation

    // Quick Select through transforming to evaluate the k-th smallest
    public int findKthLargest(int[] nums, int k) {
        return new QuickSelectHelper(nums).findKthLargest(k);
    }

    private static class QuickSelectHelper {
        private int[] nums;

        public QuickSelectHelper(int[] nums) {
            this.nums = nums;
        }

        private int findKthLargest(int kLargest) {
            return quickSelect(nums.length-kLargest);
        }

        private int quickSelect(int kSmallest) {
            int st = 0;
            int ed = nums.length-1;

            while (st < ed) {
                int pivot = st + new Random().nextInt(ed-st);
                pivot = partition(st, ed, pivot);

                if (pivot == kSmallest) {
                    return nums[pivot];
                }
                if (pivot > kSmallest) {
                    ed = pivot - 1;
                } else {
                    st = pivot + 1;
                }
            }

            return nums[st];
        }

        private int partition(int st, int ed, int pivot) {
            // put pivot to the end
            int pVal = nums[pivot];
            swap(ed, pivot);
            int idx = st;

            // put smaller value to left (TODO: Don't know if descending still works)
            for (int i = st; i <= ed; i++) {
                if (nums[i] < pVal) {
                    swap(i, idx++);
                }
            }

            // move pivot to its right place
            swap(ed, idx);

            return idx;
        }

        private void swap(int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }


    }

    @Test
    public void case1() {
        assertEquals(5, findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    }

    @Test
    public void case2() {
        assertEquals(4, findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    }

}
