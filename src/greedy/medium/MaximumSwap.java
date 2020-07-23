package greedy.medium;

/**
 * Created by Yiyun On 2020/7/22 20:32
 *
 * 670. Maximum Swap
 */
public class MaximumSwap {

    // Time: O(N)
    // Codes are mostly copied from LC Solution due to not plenty of time left for interview
    public int maximumSwap(int num) {
        char[] array = Integer.toString(num).toCharArray();
        int n = array.length;
        // last[d] = i, i is the index of digit d's last occurrence (if it exists).
        int[] last = new int[10];
        for (int i = 0; i < n; i++) {
            last[array[i] - '0'] = i;
        }

        // scan the number from left to right, swap with a larger(latest) digit in the future if exists
        for (int i = 0; i < n; i++) {
            for (int d = 9; d > array[i]-'0'; d--) {
                if (last[d] > i) {
                    char tmp = array[i];
                    array[i] = array[last[d]];
                    array[last[d]] = tmp;
                    return Integer.valueOf(new String(array));
                }
            }
        }
        return num;
    }

}
